package com.midknight.bowyery.item.arrows;

import com.midknight.bowyery.entity.VillagerArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class VillagerArrowItem extends ArrowItem {


    // Constructor //

    public VillagerArrowItem(Properties properties) {

        super(properties);

    }

    @Override
    public AbstractArrowEntity createArrow(World worldIn, ItemStack stack, LivingEntity shooter) {

        return new VillagerArrowEntity(worldIn, shooter);
    }
}
