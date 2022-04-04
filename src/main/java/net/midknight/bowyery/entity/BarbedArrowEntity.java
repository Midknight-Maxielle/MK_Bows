package net.midknight.bowyery.entity;

import net.midknight.bowyery.Bowyery;
import net.midknight.bowyery.util.EntitySpawnPacket;
import net.midknight.bowyery.util.ModRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.world.World;

public class BarbedArrowEntity extends PersistentProjectileEntity {

    public static double baseDamage = 1.5D;


    public BarbedArrowEntity(EntityType<? extends BarbedArrowEntity> entityType, World world) {
        super(entityType, world);
        this.setDamage(baseDamage);
    }

    public BarbedArrowEntity(World world, LivingEntity shooter) {
        super(ModRegistry.BARBED_ARROW_TYPE, shooter, world);
        this.setDamage(baseDamage);
    }

    public BarbedArrowEntity(World world, double x, double y, double z) {
        super(ModRegistry.BARBED_ARROW_TYPE, x, y, z, world);
        this.setDamage(baseDamage);
    }

    @Override
    protected ItemStack asItemStack() {
        return ModRegistry.BARBED_ARROW.getDefaultStack();
    }

    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, Bowyery.PacketID);
    }
}
