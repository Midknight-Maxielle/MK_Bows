package com.mk.archarsenal.items;

import com.mk.archarsenal.entities.VillagerArrowEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

public class VillagerArrowItem extends ArrowItem {

    // Constructor

    public VillagerArrowItem(Properties properties) { super(properties); }

    @Override @Nonnull @ParametersAreNonnullByDefault
    public AbstractArrow createArrow(Level world, ItemStack item, LivingEntity shooter) {
        return new VillagerArrowEntity(world, shooter);
    }
}
