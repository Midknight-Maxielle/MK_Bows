package com.mk.archarsenal;

import com.mk.archarsenal.entities.BarbedArrowEntity;
import com.mk.archarsenal.entities.VillagerArrowEntity;
import com.mk.archarsenal.items.BarbedArrowItem;
import com.mk.archarsenal.items.ModBowItem;
import com.mk.archarsenal.items.QuiverItem;
import com.mk.archarsenal.items.VillagerArrowItem;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.List;

public class ModRegistry {

    protected static IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

    // Registers

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, ArchArsenal.MODID);
    public static DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ArchArsenal.MODID);

    // Registered Objects

    public static final RegistryObject<ModBowItem> BOW_LEATHER =
            ITEM.register("bow_leather", () -> new ModBowItem(new Item.Properties()
                    .durability(578),
                    false
            ));

    public static final RegistryObject<ModBowItem> BOW_IRON =
            ITEM.register("bow_iron", () -> new ModBowItem(new Item.Properties()
                    .durability(770),
                    false
            ));

    public static final RegistryObject<ModBowItem> BOW_GOLD =
            ITEM.register("bow_gold", () -> new ModBowItem(new Item.Properties()
                    .durability(289),
                    true
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack item, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.archarsenal.bow_gold"));
                    super.appendHoverText(item, world, tooltip, flag);
                }
            });

    public static final RegistryObject<ModBowItem> BOW_DIAMOND =
            ITEM.register("bow_diamond", () -> new ModBowItem(new Item.Properties()
                    .durability(1540),
                    false
            ));

    public static final RegistryObject<ModBowItem> BOW_NETHERITE =
            ITEM.register("bow_netherite", () -> new ModBowItem(new Item.Properties()
                    .durability(2310)
                    .fireResistant(),
                    false
            ) {
                @Override
                @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack item, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
                    tooltip.add(Component.translatable("tooltip.archarsenal.bow_netherite"));
                    super.appendHoverText(item, world, tooltip, flag);
                }
            });

    public static final RegistryObject<BarbedArrowItem> BARBED_ARROW =
            ITEM.register("barbed_arrow", () -> new BarbedArrowItem(new Item.Properties()));

    public static final RegistryObject<VillagerArrowItem> VILLAGER_ARROW =
            ITEM.register("villager_arrow", () -> new VillagerArrowItem(new Item.Properties()));

    public static final RegistryObject<QuiverItem> QUIVER =
            ITEM.register("quiver", () -> new QuiverItem(new Item.Properties()
                            .durability(32)
                    ){
                        @Override @ParametersAreNonnullByDefault
                        public void appendHoverText(ItemStack item, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
                            tooltip.add(Component.translatable("tooltip.archarsenal.quiver"));
                            super.appendHoverText(item, world, tooltip, flag);
                        }
                    });

    // Registered Entities

    public static final RegistryObject<EntityType<BarbedArrowEntity>> BARBED_ARROW_ENTITY =
            ENTITY_TYPE.register("barbed_arrow", () -> EntityType.Builder
                    .<BarbedArrowEntity>of(BarbedArrowEntity::new, MobCategory.MISC)
                    .setCustomClientFactory(BarbedArrowEntity::new)
                    .sized(0.5F, 0.5F)
                    .setTrackingRange(4)
                    .updateInterval(20)
                    .build("barbed_arrow")
            );

    public static final RegistryObject<EntityType<VillagerArrowEntity>> VILLAGER_ARROW_ENTITY =
            ENTITY_TYPE.register("villager_arrow", () -> EntityType.Builder
                    .<VillagerArrowEntity>of(VillagerArrowEntity::new, MobCategory.MISC)
                    .setCustomClientFactory(VillagerArrowEntity::new)
                    .sized(0.5F, 0.5F)
                    .setTrackingRange(4)
                    .updateInterval(20)
                    .build("villager_arrow")
            );


    // Registration Methods

    public static void registerITEM() {
        ITEM.register(eventBus);
    }

    public static void registerENTITY() {
        ENTITY_TYPE.register(eventBus);
    }
}