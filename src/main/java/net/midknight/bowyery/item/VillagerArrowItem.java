package net.midknight.bowyery.item;

import net.midknight.bowyery.entity.VillagerArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VillagerArrowItem extends ArrowItem {

    public VillagerArrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        return new VillagerArrowEntity(world, shooter);
    }
}
