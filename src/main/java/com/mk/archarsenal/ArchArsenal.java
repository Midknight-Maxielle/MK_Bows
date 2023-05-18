package com.mk.archarsenal;

import com.mk.archarsenal.entities.renderers.BarbedArrowRenderer;
import com.mk.archarsenal.entities.renderers.VillagerArrowRenderer;
import com.mk.archarsenal.utility.ModBowModelProperties;
import com.mk.archarsenal.utility.ModCreativeTab;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ArchArsenal.MODID)
public class ArchArsenal {

    public static final String MODID = "archarsenal";

    public ArchArsenal() {

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::modelBows);
        modEventBus.addListener(this::setupArrows);
        modEventBus.addListener(this::addCreative);

        MinecraftForge.EVENT_BUS.register(this);

        ModRegistry.registerITEM();
        ModRegistry.registerENTITY();



        // Register the item to a creative tab
        // modEventBus.addListener(this::addCreative);
    }

    private void modelBows(final FMLClientSetupEvent event) {

        event.enqueueWork(() -> {
            ModBowModelProperties.modelBow(ModRegistry.BOW_LEATHER.get());
            ModBowModelProperties.modelBow(ModRegistry.BOW_IRON.get());
            ModBowModelProperties.modelBow(ModRegistry.BOW_GOLD.get());
            ModBowModelProperties.modelBow(ModRegistry.BOW_DIAMOND.get());
            ModBowModelProperties.modelBow(ModRegistry.BOW_NETHERITE.get());
        });
    }

    @SubscribeEvent
    public void setupArrows(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModRegistry.BARBED_ARROW_ENTITY.get(), BarbedArrowRenderer::new);
        event.registerEntityRenderer(ModRegistry.VILLAGER_ARROW_ENTITY.get(), VillagerArrowRenderer::new);
    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == ModCreativeTab.ARCHARSENAL_TAB) {
            event.accept(ModRegistry.BOW_LEATHER);
            event.accept(ModRegistry.BOW_IRON);
            event.accept(ModRegistry.BOW_GOLD);
            event.accept(ModRegistry.BOW_DIAMOND);
            event.accept(ModRegistry.BOW_NETHERITE);
            event.accept(ModRegistry.BARBED_ARROW);
            event.accept(ModRegistry.VILLAGER_ARROW);
            event.accept(ModRegistry.QUIVER);
        }
    }
}
