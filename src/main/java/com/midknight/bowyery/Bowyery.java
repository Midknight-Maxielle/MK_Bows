package com.midknight.bowyery;

import com.midknight.bowyery.entity.renderers.BarbedArrowRenderer;
import com.midknight.bowyery.entity.renderers.VillagerArrowRenderer;
import com.midknight.bowyery.util.BowyeryItemModelProperties;
import com.midknight.bowyery.util.registries.ModRegistry;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Bowyery.MOD_ID)
public class Bowyery
{
    public static final String MOD_ID = "bowyery";

    public Bowyery() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::bowSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::arrowSetup);
        ModRegistry.registerENTITY();
        ModRegistry.registerGLMS();
        ModRegistry.registerITEM();
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void arrowSetup(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModRegistry.VILLAGER_ARROW_ENTITY.get(), VillagerArrowRenderer::new);
        event.registerEntityRenderer(ModRegistry.BARBED_ARROW_ENTITY.get(), BarbedArrowRenderer::new);
    }

    private void bowSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            BowyeryItemModelProperties.makeBow(ModRegistry.BOW_LEATHER.get());
            BowyeryItemModelProperties.makeBow(ModRegistry.BOW_IRON.get());
            BowyeryItemModelProperties.makeBow(ModRegistry.BOW_GOLD.get());
            BowyeryItemModelProperties.makeBow(ModRegistry.BOW_DIAMOND.get());
            BowyeryItemModelProperties.makeBow(ModRegistry.BOW_NETHERITE.get());
        });
    }
}
