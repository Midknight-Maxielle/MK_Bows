package com.midknight.bowyery.item;


import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;


import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class BowyeryBowItem extends BowItem {

    // Fields

    double damageMod;
    boolean autoInfinite;
    boolean autoFlame;
    public int drawModifier;
    boolean creativeMode;

    // Constructor Methods

    public BowyeryBowItem(Properties properties, double dmgMod, boolean inf, boolean fire, int drawModifier) {
        super(properties);
        this.damageMod = dmgMod;
        this.autoInfinite = inf;
        this.autoFlame = fire;
        this.drawModifier = drawModifier;
    }

    // Methods //

    public boolean canDraw(ItemStack arrows, Player player) {
        creativeMode = player.getAbilities().instabuild;
        return
                creativeMode ||
                EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, arrows) > 0 ||
                !player.getProjectile(arrows).isEmpty() ||
                autoInfinite;
    }

    // Override Methods

   @Nonnull @ParametersAreNonnullByDefault
    public InteractionResultHolder<ItemStack> use (Level world, Player player, InteractionHand hand) {
        ItemStack bow = player.getItemInHand(hand);
        boolean drawableFlag = canDraw(bow, player);

        InteractionResultHolder<ItemStack> intResult = net.minecraftforge.event.ForgeEventFactory.onArrowNock(bow, world, player, hand, drawableFlag);
        if (intResult != null) return intResult;

        if (!creativeMode && !drawableFlag) {
            return InteractionResultHolder.fail(bow);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(bow);
        }
    }

    @Override @ParametersAreNonnullByDefault
    public void releaseUsing(ItemStack bow, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player playerEntity) {
            boolean drawableFlag = canDraw(bow, playerEntity);
            creativeMode = playerEntity.getAbilities().instabuild;
            ItemStack arrowStack = playerEntity.getProjectile(bow);

            int i = this.getUseDuration(bow) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(bow, worldIn, playerEntity, i, !arrowStack.isEmpty() || drawableFlag);
            if (i < 0) return;

            if (!arrowStack.isEmpty() || drawableFlag) {
                if (arrowStack.isEmpty()) {
                    arrowStack = new ItemStack(Items.ARROW);
                }

                float f = getPowerForTime(i);
                if (!((double)f < 0.1D)) {
                    boolean infiniteFlag = creativeMode || autoInfinite || (arrowStack.getItem() instanceof ArrowItem && ((ArrowItem)arrowStack.getItem()).isInfinite(arrowStack, bow, playerEntity));
                    if (!worldIn.isClientSide()) {
                        ArrowItem arrowitem = (ArrowItem)(arrowStack.getItem() instanceof ArrowItem ? arrowStack.getItem() : Items.ARROW);
                        AbstractArrow abArrowEntity = arrowitem.createArrow(worldIn, arrowStack, playerEntity);
                        abArrowEntity = customArrow(abArrowEntity);
                        abArrowEntity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) {
                            abArrowEntity.setCritArrow(true);
                        }

                        int j = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bow);
                        if (j > 0) {
                            abArrowEntity.setBaseDamage(abArrowEntity.getBaseDamage() + (double)j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, bow);
                        if (k > 0) {
                            abArrowEntity.setKnockback(k);
                        }

                        if (EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, bow) > 0 || autoFlame) {
                            abArrowEntity.setSecondsOnFire(100);
                        }

                        bow.hurtAndBreak(1, playerEntity, (player) -> player.broadcastBreakEvent(playerEntity.getUsedItemHand()));
                        if (infiniteFlag || creativeMode && (arrowStack.getItem() == Items.SPECTRAL_ARROW || arrowStack.getItem() == Items.TIPPED_ARROW)) {
                            abArrowEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }

                        worldIn.addFreshEntity(abArrowEntity);
                    }

                    worldIn.playSound(null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (worldIn.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!infiniteFlag && !creativeMode) {
                        ItemStack offhandItem = playerEntity.getOffhandItem();
                        boolean preserveArrow = false;
                        if (offhandItem.getItem() instanceof BowyeryQuiverItem quiverItem) {
                            preserveArrow = quiverItem.rollArrowPreservation(worldIn);
                        }
                        if (!preserveArrow) {
                            arrowStack.shrink(1);
                            if (arrowStack.isEmpty()) {
                                playerEntity.getInventory().removeItem(arrowStack);
                            }
                            offhandItem.hurtAndBreak(1, playerEntity, (player) -> player.broadcastBreakEvent(playerEntity.getUsedItemHand()));
                        }
                    }

                    playerEntity.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }
}

