package net.midknight.bowyery.item;

import net.minecraft.item.Item;
import net.minecraft.world.World;


public class BowyeryQuiverItem extends Item {

    // Constructor Method

    public BowyeryQuiverItem(Settings properties) {
        super(properties);
    }

    // Methods

    public boolean rollArrowPreservation(World worldIn) {
        float roll = worldIn.getRandom().nextFloat();
        return roll > 0.50F;
    }
}

