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

public class BarbedArrowEntity extends AbstractArrowEntity {

    public static double baseDamage = 3.0D;
    public static EntityType<BarbedArrowEntity> BARBED_ARROW_ENTITY = BowyeryEntityRegistry.BARBED_ARROW.get();

    //  CONSTRUCTORS //
    //  | | | | | |  //
    //  V V V V V V  //

    public BarbedArrowEntity(EntityType<? extends BarbedArrowEntity> entity, World world) {

        super(entity, world);
        this.setDamage(baseDamage);
    }

    public BarbedArrowEntity(World world, LivingEntity shooter) {

        super(BARBED_ARROW_ENTITY, shooter, world);
        this.setDamage(baseDamage);
    }

    public BarbedArrowEntity(World world, double posX, double posY, double posZ) {

        super(BARBED_ARROW_ENTITY, posX, posY, posZ, world);
        this.setDamage(baseDamage);
    }

    public BarbedArrowEntity(FMLPlayMessages.SpawnEntity packet, World world) {
        super(BARBED_ARROW_ENTITY, world);
        this.setDamage(baseDamage);
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(BowyeryItemRegistry.BARBED_ARROW.get());
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}