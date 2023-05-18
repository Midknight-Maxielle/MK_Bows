package com.mk.archarsenal.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class QuiverItem extends Item {

    // Constructor

    public QuiverItem(Properties properties) {
        super(properties);
    }

    // Methods

    public boolean rollArrowSave(Level world) {
        float roll = world.getRandom().nextFloat();
        return roll > 0.75F;
    }
}