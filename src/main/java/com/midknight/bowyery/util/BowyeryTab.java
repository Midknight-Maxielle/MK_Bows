package com.midknight.bowyery.util;

import com.midknight.bowyery.util.registries.ModRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class BowyeryTab {

    public static final CreativeModeTab BOWYER = new CreativeModeTab("bowyeryTab") {

        @Override @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(ModRegistry.BOW_NETHERITE.get());
        }
    };
}
