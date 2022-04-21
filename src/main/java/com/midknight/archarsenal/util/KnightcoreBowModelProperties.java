package com.midknight.archarsenal.util;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class KnightcoreBowModelProperties {

    public static void makeBow(Item item) {
        ItemProperties.register(item, new ResourceLocation("pull"), (bowItem, level, playerEntity, integer) -> {
            if (playerEntity == null) {
                return 0.0F;
            } else {
                return playerEntity.getUseItem() != bowItem ? 0.0F : (float)(bowItem.getUseDuration() - playerEntity.getUseItemRemainingTicks()) / 20.0F;
            }
        });
        ItemProperties.register(item, new ResourceLocation("pulling"), (itemStack, clientLevel, livingEntity, integer) -> livingEntity != null && livingEntity.isUsingItem() && livingEntity.getUseItem() == itemStack ? 1.0F : 0.0F);
    }
}

