package com.midknight.archarsenal.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class BowyeryQuiverItem extends Item {

    // Constructor Method

    public BowyeryQuiverItem(Properties properties) {
        super(properties);
    }

    // Methods

    public boolean rollArrowPreservation(Level worldIn) {
        float roll = worldIn.getRandom().nextFloat();
        return roll > 0.50F;
    }
}

