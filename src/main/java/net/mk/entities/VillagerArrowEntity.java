package net.mk.entities;

import com.google.common.collect.Sets;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.potion.Potions;
import net.minecraft.registry.Registries;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.mk.items.ModItems;

import java.util.Collection;
import java.util.Set;

public class VillagerArrowEntity extends PersistentProjectileEntity {

    // Fields //

    private Potion potion = Potions.EMPTY;
    private static final TrackedData<Integer> COLOR = DataTracker.registerData(VillagerArrowEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private final Set<StatusEffectInstance> effects = Sets.newHashSet();
    private boolean colorSet;

    public static double BASE_DMG = 1.0d;
    public static EntityType<VillagerArrowEntity> VILLAGER_ARROW_ENTITY = ModEntities.VILLAGER_ARROW_TYPE;

    // Constructor Methods //

    public VillagerArrowEntity(EntityType<VillagerArrowEntity> entity, World world) {
        super(entity, world);
        this.setDamage(BASE_DMG);

    }
    public VillagerArrowEntity(World world, LivingEntity shooter) {
        super(VILLAGER_ARROW_ENTITY, shooter, world);
        this.setDamage(BASE_DMG);

    }
    public VillagerArrowEntity(World world, double x, double y, double z) {
        super(VILLAGER_ARROW_ENTITY, x, y, z, world);
        this.setDamage(BASE_DMG);
    }

    // Methods //

    public void initFromStack(ItemStack stack) {
        if (stack.isOf(Items.TIPPED_ARROW)) {
            this.potion = PotionUtil.getPotion(stack);
            Collection<StatusEffectInstance> collection = PotionUtil.getCustomPotionEffects(stack);
            if (!collection.isEmpty()) {
                for (StatusEffectInstance statusEffectInstance : collection) {
                    this.effects.add(new StatusEffectInstance(statusEffectInstance));
                }
            }

            int i = ArrowEntity.getCustomPotionColor(stack);
            if (i == -1) {
                this.initColor();
            } else {
                this.setColor(i);
            }
        } else if (stack.isOf(ModItems.VILLAGER_ARROW)) {
            this.potion = Potions.EMPTY;
            this.effects.clear();
            this.dataTracker.set(COLOR, -1);
        }
    }

    protected void initColor() {
        this.colorSet = false;
        if (this.potion == Potions.EMPTY && this.effects.isEmpty()) {
            this.dataTracker.set(COLOR, -1);
        } else {
            this.dataTracker.set(COLOR, PotionUtil.getColor(PotionUtil.getPotionEffects(this.potion, this.effects)));
        }

    }

    protected void setColor(int color) {
        colorSet = true;
        this.dataTracker.set(COLOR, color);
    }


    @Override
    protected ItemStack asItemStack() {
        if (this.effects.isEmpty() && this.potion == Potions.EMPTY) {
            return new ItemStack(ModItems.VILLAGER_ARROW);
        } else {
            ItemStack itemStack = new ItemStack(Items.TIPPED_ARROW);
            PotionUtil.setPotion(itemStack, this.potion);
            PotionUtil.setCustomPotionEffects(itemStack, this.effects);
            if (this.colorSet) {
                itemStack.getOrCreateNbt().putInt("CustomPotionColor", this.getColor());
            }

            return itemStack;
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        if (this.potion != Potions.EMPTY) {
            nbt.putString("Potion", Registries.POTION.getId(this.potion).toString());
        }

        if (this.colorSet) {
            nbt.putInt("Color", this.getColor());
        }

        if (!this.effects.isEmpty()) {
            NbtList nbtList = new NbtList();

            for (StatusEffectInstance statusEffectInstance : this.effects) {
                nbtList.add(statusEffectInstance.writeNbt(new NbtCompound()));
            }

            nbt.put("CustomPotionEffects", nbtList);
        }

    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Potion", NbtElement.STRING_TYPE)) {
            this.potion = PotionUtil.getPotion(nbt);
        }

        for (StatusEffectInstance statusEffectInstance : PotionUtil.getCustomPotionEffects(nbt)) {
            addEffect(statusEffectInstance);
        }

        if (nbt.contains("Color", NbtElement.NUMBER_TYPE)) {
            this.setColor(nbt.getInt("Color"));
        } else {
            this.initColor();
        }

    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        Entity entity = this.getEffectCause();

        for (StatusEffectInstance statusEffectInstance : this.potion.getEffects()) {
            target.addStatusEffect(
                    new StatusEffectInstance(
                            statusEffectInstance.getEffectType(),
                            Math.max(statusEffectInstance.getDuration() / 8, 1),
                            statusEffectInstance.getAmplifier(),
                            statusEffectInstance.isAmbient(),
                            statusEffectInstance.shouldShowParticles()
                    ),
                    entity
            );
        }

        if (!this.effects.isEmpty()) {
            for (StatusEffectInstance statusEffectInstance : this.effects) {
                target.addStatusEffect(statusEffectInstance, entity);
            }
        }

    }

    public void addEffect(StatusEffectInstance effect) {
        this.effects.add(effect);
        this.getDataTracker().set(COLOR, PotionUtil.getColor(PotionUtil.getPotionEffects(this.potion, this.effects)));
    }

    public int getColor() {
        return this.dataTracker.get(COLOR);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(COLOR, -1);
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    this.spawnParticles(1);
                }
            } else {
                this.spawnParticles(2);
            }
        } else if (this.inGround && this.inGroundTime != 0 && !this.effects.isEmpty() && this.inGroundTime >= 600) {
            this.getWorld().sendEntityStatus(this, (byte) 0);
            this.potion = Potions.EMPTY;
            this.effects.clear();
            this.dataTracker.set(COLOR, -1);
        }

    }

    private void spawnParticles(int amount) {
        int i = this.getColor();
        if (i != -1 && amount > 0) {
            double d = (double) (i >> 16 & 0xFF) / 255.0;
            double e = (double) (i >> 8 & 0xFF) / 255.0;
            double f = (double) (i >> 0 & 0xFF) / 255.0;

            for (int j = 0; j < amount; ++j) {
                this.getWorld().addParticle(ParticleTypes.ENTITY_EFFECT, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
            }

        }
    }

    @Override
    public void handleStatus(byte status) {
        if (status == 0) {
            int i = this.getColor();
            if (i != -1) {
                double d = (double) (i >> 16 & 0xFF) / 255.0;
                double e = (double) (i >> 8 & 0xFF) / 255.0;
                double f = (double) (i >> 0 & 0xFF) / 255.0;

                for (int j = 0; j < 20; ++j) {
                    this.getWorld().addParticle(ParticleTypes.ENTITY_EFFECT, this.getParticleX(0.5), this.getRandomBodyY(), this.getParticleZ(0.5), d, e, f);
                }
            }
        } else {
            super.handleStatus(status);
        }

    }
}