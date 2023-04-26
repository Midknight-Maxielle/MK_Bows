package com.mk.archarsenal;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRegistry {

    protected static IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

    // Registers

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, ArchArsenal.MODID);

    // Registration Methods

    public static void registerITEM() {

        ITEM.register(eventBus);
    }
}