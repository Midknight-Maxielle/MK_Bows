package com.midknight.bowyery;

import com.midknight.bowyery.entity.renderers.BarbedArrowRenderer;
import com.midknight.bowyery.entity.BowyeryEntityRegistry;
import com.midknight.bowyery.entity.renderers.VillagerArrowRenderer;
import com.midknight.bowyery.item.BowyeryItemRegistry;
import com.midknight.bowyery.util.BowyeryItemModelProperties;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

@Mod(Bowyery.MOD_ID)
public class Bowyery
{
    public static final String MOD_ID = "bowyery";
    private static final Logger LOGGER = LogManager.getLogger();

    public Bowyery() {

        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        BowyeryItemRegistry.register(eventBus);
        BowyeryEntityRegistry.register(eventBus);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }


    private void doClientSetup(final FMLClientSetupEvent event) {

        event.enqueueWork(() -> {

            BowyeryItemModelProperties.makeBow(BowyeryItemRegistry.BOW_LEATHER.get());
            BowyeryItemModelProperties.makeBow(BowyeryItemRegistry.BOW_IRON.get());
            BowyeryItemModelProperties.makeBow(BowyeryItemRegistry.BOW_GOLD.get());
            BowyeryItemModelProperties.makeBow(BowyeryItemRegistry.BOW_DIAMOND.get());
            BowyeryItemModelProperties.makeBow(BowyeryItemRegistry.BOW_NETHERITE.get());
        });

        RenderingRegistry.registerEntityRenderingHandler(BowyeryEntityRegistry.VILLAGER_ARROW.get(), VillagerArrowRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(BowyeryEntityRegistry.BARBED_ARROW.get(), BarbedArrowRenderer::new);
    }
}
