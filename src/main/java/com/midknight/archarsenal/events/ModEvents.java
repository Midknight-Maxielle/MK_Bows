package com.midknight.archarsenal.events;

import com.midknight.archarsenal.ArchArsenal;
import com.midknight.archarsenal.item.BowyeryBowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArchArsenal.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void handleBowDrawEvent(LivingEntityUseItemEvent.Tick event) {

        int drawMod = 0;
        ItemStack item = event.getItem();

        if(item.getItem() instanceof BowyeryBowItem bowItem) {
            drawMod = bowItem.drawModifier;
        }
        if(drawMod != 0 && event.getDuration() > event.getItem().getUseDuration() - 20) {
            event.setDuration(event.getDuration() - drawMod);
        }
    }
}
