package com.mk.archarsenal;

import com.mk.archarsenal.items.ModBowItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRegistry {

    protected static IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

    // Registers

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, ArchArsenal.MODID);

    // Registered Objects

    public static final RegistryObject<ModBowItem> BOW_LEATHER =
            ITEM.register("bow_leather", () -> new ModBowItem(new Item.Properties()
                    .durability(384),
                    false
            ));


    // Registration Methods

    public static void registerITEM() {

        ITEM.register(eventBus);
    }
}