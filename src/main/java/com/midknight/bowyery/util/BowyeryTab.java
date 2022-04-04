package com.midknight.bowyery.util;

import com.midknight.bowyery.item.BowyeryItemRegistry;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class BowyeryTab {

    public static final ItemGroup BOWYER = new ItemGroup("bowyeryTab") {


        @Override
        public ItemStack createIcon() {
            return new ItemStack(BowyeryItemRegistry.BOW_NETHERITE.get());
        }
    };
}
