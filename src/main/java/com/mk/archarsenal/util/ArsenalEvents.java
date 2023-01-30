package com.mk.archarsenal.util;

import com.mk.archarsenal.ArchArsenal;
import com.mk.archarsenal.item.QuiverItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod.EventBusSubscriber(modid = ArchArsenal.MOD_ID)
public class ArsenalEvents {

    @SubscribeEvent
    public static void handleQuiver(ArrowLooseEvent event) {

        LivingEntity player = event.getEntity();
        ItemStack offhand = player.getOffhandItem();
        ItemStack bow = event.getBow();
        Level world = event.getLevel();

        ItemStack arrowStack = player.getProjectile(bow);

        if(offhand.getItem() instanceof QuiverItem quiver) {

            boolean roll = quiver.rollArrowPreservation(world);
            if(roll) {
                arrowStack.grow(1);
                offhand.hurtAndBreak(1, player, (entity) -> entity.broadcastBreakEvent(player.getUsedItemHand()));
            }
        }
    }
}
