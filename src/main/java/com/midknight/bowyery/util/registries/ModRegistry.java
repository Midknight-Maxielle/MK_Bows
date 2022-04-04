package com.midknight.bowyery.util.registries;

import com.midknight.bowyery.Bowyery;
import com.midknight.bowyery.entity.BarbedArrow;
import com.midknight.bowyery.entity.VillagerArrow;
import com.midknight.bowyery.events.ModLootInjector;
import com.midknight.bowyery.item.BowyeryBowItem;
import com.midknight.bowyery.item.BowyeryQuiverItem;
import com.midknight.bowyery.item.arrows.BarbedArrowItem;
import com.midknight.bowyery.item.arrows.VillagerArrowItem;
import com.midknight.bowyery.util.BowyeryTab;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
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
    public static final String VILLAGER_ARROW_NAME = "villager_arrow";
    public static final String BARBED_ARROW_NAME = "barbed_arrow";

    // Deferred Registers

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(ForgeRegistries.ENTITIES, Bowyery.MOD_ID);

    public static DeferredRegister<GlobalLootModifierSerializer<?>> GLMS =
        DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, Bowyery.MOD_ID);

    public static final DeferredRegister<Item> ITEM =
    DeferredRegister.create(ForgeRegistries.ITEMS, Bowyery.MOD_ID);

    // - - - - - - - - - //
    // Registry Objects  //
    // - - - - - - - - - //

    // Arrow & Quivers Item //

    public static final RegistryObject<VillagerArrowItem> VILLAGER_ARROW =
            ITEM.register("villager_arrow",
                () -> new VillagerArrowItem(new Item.Properties().tab(BowyeryTab.BOWYER
            )));

    public static final RegistryObject<BarbedArrowItem> BARBED_ARROW =
            ITEM.register("barbed_arrow",
                () -> new BarbedArrowItem(new Item.Properties().tab(BowyeryTab.BOWYER
            )));

    public static final RegistryObject<BowyeryQuiverItem> QUIVER =
            ITEM.register("quiver", () -> new BowyeryQuiverItem(new Item.Properties()
                    .durability(128)
                    .tab(BowyeryTab.BOWYER)
            ){

                @Override @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.bowyery.quiver"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    // Bow Items //

    public static final RegistryObject<BowyeryBowItem> BOW_LEATHER =
            ITEM.register("bow_leather", () -> new BowyeryBowItem(new Item.Properties()
                    .durability(512)
                    .tab(BowyeryTab.BOWYER),
                    0.0D,
                    false,
                    false,
                    1
            ));

    public static final RegistryObject<BowyeryBowItem> BOW_IRON =
            ITEM.register("bow_iron", () -> new BowyeryBowItem(new Item.Properties()
                    .durability(768)
                    .tab(BowyeryTab.BOWYER),
                    1.0D,
                    false,
                    false,
                    0
            ));

    public static final RegistryObject<BowyeryBowItem> BOW_GOLD =
            ITEM.register("bow_gold", () -> new BowyeryBowItem(new Item.Properties()
                    .durability(384)
                    .tab(BowyeryTab.BOWYER),
                    -0.5D,
                    true,
                    false,
                    1
            ) {

                @Override @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.bowyery.bow_gold"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject <BowyeryBowItem> BOW_DIAMOND =
            ITEM.register("bow_diamond", () -> new BowyeryBowItem(new Item.Properties()
                    .durability(1024)
                    .tab(BowyeryTab.BOWYER),
                    2.0D,
                    false,
                    false,
                    0
            ));

    public static final RegistryObject <BowyeryBowItem> BOW_NETHERITE =
            ITEM.register("bow_netherite", () -> new BowyeryBowItem(new Item.Properties()
                            .durability(2048)
                            .tab(BowyeryTab.BOWYER),
                            4.0D,
                            false,
                            true,
                            0
                    ) {
                        @Override @ParametersAreNonnullByDefault
                        public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                            tooltip.add(new TranslatableComponent("tooltip.bowyery.bow_netherite"));
                            super.appendHoverText(stack, worldIn, tooltip, flagIn);
                        }
                    }
            );

    // Arrow Entities //
    public static final RegistryObject<EntityType<VillagerArrow>> VILLAGER_ARROW_ENTITY =
            ENTITY_TYPES.register("villager_arrow",
                    () -> EntityType.Builder
                            .<VillagerArrow>of(VillagerArrow::new, MobCategory.MISC)
                            .setCustomClientFactory(VillagerArrow::new)
                            .sized(0.5F, 0.5F)
                            .setTrackingRange(4)
                            .updateInterval(20)
                            .build(VILLAGER_ARROW_NAME)
            );

    public static final RegistryObject<EntityType<BarbedArrow>> BARBED_ARROW_ENTITY =
            ENTITY_TYPES.register("barbed_arrow",
                    () -> EntityType.Builder
                            .<BarbedArrow>of(BarbedArrow::new, MobCategory.MISC)
                            .setCustomClientFactory(BarbedArrow::new)
                            .sized(0.5F,0.5F)
                            .setTrackingRange(4)
                            .updateInterval(20)
                            .build(BARBED_ARROW_NAME)
            );

    // Loot Injector //

    public static RegistryObject<GlobalLootModifierSerializer<ModLootInjector.LootInjectionModifier>> LOOT_INJECTION =
        GLMS.register("loot_injection", ModLootInjector.InjectionSerializer::new);

    // Register Methods

    public static void registerENTITY() {ENTITY_TYPES.register(eventBus);}
    public static void registerGLMS() {GLMS.register(eventBus);}
    public static void registerITEM() {ITEM.register(eventBus);}
}
