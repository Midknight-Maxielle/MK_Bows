package com.midknight.bowyery.item.arrows;

import com.midknight.bowyery.entity.BarbedArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class BarbedArrowItem extends ArrowItem {


    // Constructor //

    public BarbedArrowItem(Item.Properties properties) {
        super(properties);
    }

    @Override @Nonnull
    @ParametersAreNonnullByDefault
    public AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
        return new BarbedArrow(worldIn, shooter);
    }
}
