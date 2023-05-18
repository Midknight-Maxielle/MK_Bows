package com.mk.archarsenal.utility;

import com.mk.archarsenal.ArchArsenal;
import com.mk.archarsenal.ModRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ArchArsenal.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeTab {
    public static CreativeModeTab ARCHARSENAL_TAB;

    @SubscribeEvent
    public static void registerCreativeTab(CreativeModeTabEvent.Register event) {
        ARCHARSENAL_TAB = event.registerCreativeModeTab(new ResourceLocation(ArchArsenal.MODID, "archarsenal_tab"),
                builder -> builder.icon(() -> new ItemStack(ModRegistry.BOW_DIAMOND.get()))
                        .title(Component.translatable("creativemodetab.archarsenal_tab")));
    }
}
