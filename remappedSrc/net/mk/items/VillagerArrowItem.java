package net.mk.items;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.mk.archers_arsenal.entities.VillagerArrowEntity;

public class VillagerArrowItem extends ArrowItem {

    public VillagerArrowItem(Settings settings) {
        super(settings);
    }

    @Override
    public PersistentProjectileEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {

        var arrow = new VillagerArrowEntity(world, shooter);
        arrow.initFromStack(stack);
        return arrow;
    }
}