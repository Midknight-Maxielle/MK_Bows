package com.mk.archarsenal.entity;

import com.mk.archarsenal.util.registries.ModRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

@MethodsReturnNonnullByDefault
public class BarbedArrow extends AbstractArrow {

    // Fields

    public static double baseDamage = 3.0D;
    public static EntityType<BarbedArrow> BARBED_ARROW_ENTITY = ModRegistry.BARBED_ARROW_ENTITY.get();

    // Constructor Methods

    public BarbedArrow(EntityType<? extends BarbedArrow> entity, Level world) {
        super(entity, world);
        this.setBaseDamage(baseDamage);
    }

    public BarbedArrow(Level world, LivingEntity shooter) {
        super(BARBED_ARROW_ENTITY, shooter, world);
        this.setBaseDamage(baseDamage);
    }

    public BarbedArrow(PlayMessages.SpawnEntity packet, Level world) {
        super(BARBED_ARROW_ENTITY, world);
        this.setBaseDamage(baseDamage);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModRegistry.BARBED_ARROW.get());
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}