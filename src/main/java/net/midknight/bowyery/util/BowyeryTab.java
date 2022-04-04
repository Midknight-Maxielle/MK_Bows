package net.midknight.bowyery.util;



import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;

public class BowyeryTab {

    public static final ItemGroup BOWYER = new ItemGroup(0, "bowyeryTab") {

        @Override @Nonnull
        public ItemStack createIcon() {
            return new ItemStack(ModRegistry.BOW_NETHERITE.getDefaultStack().getItem());
        }
    };
}
