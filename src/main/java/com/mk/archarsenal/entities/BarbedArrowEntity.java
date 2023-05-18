package com.mk.archarsenal.entities;

import com.mk.archarsenal.ModRegistry;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;

@MethodsReturnNonnullByDefault
public class BarbedArrowEntity extends AbstractArrow {

    // Fields

    public static double baseDMG = 3.0D;
    public static EntityType<BarbedArrowEntity> BARBED_ARROW_ENTITY= ModRegistry.BARBED_ARROW_ENTITY.get();

    // Constructors

    public BarbedArrowEntity(EntityType<? extends BarbedArrowEntity> entity, Level world) {
        super(entity, world);
        this.setBaseDamage(baseDMG);
    }

    public BarbedArrowEntity(Level world, LivingEntity shooter) {
        super(BARBED_ARROW_ENTITY, shooter, world);
        this.setBaseDamage(baseDMG);
    }

    public BarbedArrowEntity(PlayMessages.SpawnEntity packet, Level world) {
        super(BARBED_ARROW_ENTITY, world);
        this.setBaseDamage(baseDMG);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(ModRegistry.BARBED_ARROW.get());
    }

    @Override
    public Packet<ClientGamePacketListener> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
