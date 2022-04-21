package com.midknight.archarsenal.util;

import com.midknight.archarsenal.util.registries.ModRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class CreativeTab {

    public static final CreativeModeTab ARCHERS_ARSENAL = new CreativeModeTab("archarsenalTab") {

        @Override @Nonnull
        public ItemStack makeIcon() {
            return new ItemStack(ModRegistry.BOW_NETHERITE.get());
        }
    };
}
