package com.mk.archarsenal.utility;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class ModBowModelProperties {

    public static void modelBow(Item item) {

        ItemProperties.register(item, new ResourceLocation("pull"), (bow, world, player, i) -> {
            if (player == null) {
                return 0.0F;
            } else {
                return player.getUseItem() != bow ? 0.0F : (float)(bow.getUseDuration() - player.getUseItemRemainingTicks()) / 20.F;
            }
        });
        ItemProperties.register(item, new ResourceLocation("pulling"), (bow, clientWorld, entity, i) ->
                entity != null && entity.isUsingItem() && entity.getUseItem() == bow ? 1.0F : 0.0F);
    }
}
