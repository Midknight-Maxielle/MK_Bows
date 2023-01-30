package com.mk.archarsenal.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class QuiverItem extends Item {

    // Constructor Method

    public QuiverItem(Properties properties) {
        super(properties);
    }

    // Methods

    public boolean rollArrowPreservation(Level worldIn) {
        float roll = worldIn.getRandom().nextFloat();
        return roll > 0.80F;
    }
}

