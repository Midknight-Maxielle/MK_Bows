package net.mk.archers_arsenal.items;

import net.minecraft.item.Item;
import net.minecraft.world.World;

public class QuiverItem extends Item {

    // Constructor Method //

    public QuiverItem(Settings settings) { super(settings); }

    // Functional Method //

    public boolean rollArrowSave(World world) {

        float roll = world.getRandom().nextFloat();
        return roll > 0.8f;

    }
}