package com.mk.archarsenal.items;

import com.mk.archarsenal.entities.BarbedArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class BarbedArrowItem extends ArrowItem {

    // Constructor

    public BarbedArrowItem(Properties properties) { super(properties); }

    @Override @Nonnull @ParametersAreNonnullByDefault
    public AbstractArrow createArrow(Level world, ItemStack item, LivingEntity shooter) {
        return new BarbedArrowEntity(world, shooter);
    }
}
