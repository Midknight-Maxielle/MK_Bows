package com.midknight.archarsenal.item.arrows;

import com.midknight.archarsenal.entity.VillagerArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class VillagerArrowItem extends ArrowItem {

    // Constructor //

    public VillagerArrowItem(Properties properties) {
        super(properties);
    }

    @Override @Nonnull @ParametersAreNonnullByDefault
    public AbstractArrow createArrow(Level worldIn, ItemStack stack, LivingEntity shooter) {
        return new VillagerArrow(worldIn, shooter);
    }
}
