package net.mk.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.mk.entities.BarbedArrowEntity;

public class BarbedArrowItem extends ArrowItem {

    public BarbedArrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {

        var arrow = new BarbedArrowEntity(world, shooter);
        arrow.initFromStack(stack);
        return arrow;
    }
}