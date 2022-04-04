package com.midknight.bowyery.entity;

import com.midknight.bowyery.item.BowyeryItemRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class VillagerArrowEntity extends AbstractArrowEntity {

    public static double baseDamage = 1.5D;
    public static EntityType<VillagerArrowEntity> VILLAGER_ARROW_ENTITY = BowyeryEntityRegistry.VILLAGER_ARROW.get();

    // | | | CONSTRUCTORS | | | //
    // V V V  V VV  VV V  V V V //

    public VillagerArrowEntity(EntityType<? extends VillagerArrowEntity> entity, World world) {

        super(entity, world);
        this.setDamage(baseDamage);
    }

    public VillagerArrowEntity(World world, LivingEntity shooter) {

        super(VILLAGER_ARROW_ENTITY, shooter, world);
        this.setDamage(baseDamage);
    }

    public VillagerArrowEntity(World world, double posX, double posY, double posZ) {

        super(VILLAGER_ARROW_ENTITY, posX, posY, posZ, world);
        this.setDamage(baseDamage);
    }

    public VillagerArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(VILLAGER_ARROW_ENTITY, world);
        this.setDamage(baseDamage);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(BowyeryItemRegistry.VILLAGER_ARROW.get());
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}