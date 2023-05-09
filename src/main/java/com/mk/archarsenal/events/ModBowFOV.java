package com.mk.archarsenal.events;

import com.mk.archarsenal.ArchArsenal;
import net.minecraft.world.item.BowItem;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArchArsenal.MODID)

public class ModBowFOV {

    @SubscribeEvent
    public static void handleBowFOV(ComputeFovModifierEvent event) {
        if (event.getPlayer().isUsingItem() && event.getPlayer().getMainHandItem().getItem() instanceof BowItem) {

            float fovModifier = event.getPlayer().getTicksUsingItem() / 20.0F;
            if (fovModifier > 1.0F) { fovModifier = 1.0F; }
            else {fovModifier *= fovModifier; }
            event.setNewFovModifier(event.getFovModifier() * (1.0F - fovModifier * 0.2F));

        }
    }
}
