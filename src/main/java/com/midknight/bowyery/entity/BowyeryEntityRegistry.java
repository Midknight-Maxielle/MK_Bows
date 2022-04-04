package com.midknight.bowyery.entity;

import com.midknight.bowyery.Bowyery;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BowyeryEntityRegistry {

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, Bowyery.MOD_ID);

    public static final String VILLAGER_ARROW_NAME = "villager_arrow";
    public static final String BARBED_ARROW_NAME = "barbed_arrow";

    public static final RegistryObject<EntityType<VillagerArrowEntity>> VILLAGER_ARROW =
            ENTITY_TYPES.register("villager_arrow",
                    () -> EntityType.Builder
                            .<VillagerArrowEntity>create(VillagerArrowEntity::new, EntityClassification.MISC)
                            .setCustomClientFactory(VillagerArrowEntity::new)
                            .size(0.5F, 0.5F)
                            .trackingRange(4)
                            .updateInterval(20)
                            .build(VILLAGER_ARROW_NAME)
            );

    public static final RegistryObject<EntityType<BarbedArrowEntity>> BARBED_ARROW =
            ENTITY_TYPES.register("barbed_arrow",
                    () -> EntityType.Builder
                            .<BarbedArrowEntity>create(BarbedArrowEntity::new, EntityClassification.MISC)
                            .setCustomClientFactory(BarbedArrowEntity::new)
                            .size(0.5F,0.5F)
                            .trackingRange(4)
                            .updateInterval(20)
                            .build(BARBED_ARROW_NAME)
            );

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }



}
