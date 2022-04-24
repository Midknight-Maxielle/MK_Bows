package com.midknight.archarsenal.util.registries;

import com.midknight.archarsenal.ArchArsenal;
import com.midknight.archarsenal.entity.BarbedArrow;
import com.midknight.archarsenal.entity.VillagerArrow;
import com.midknight.archarsenal.item.QuiverItem;
import com.midknight.archarsenal.item.arrows.BarbedArrowItem;
import com.midknight.archarsenal.item.arrows.VillagerArrowItem;
import com.midknight.archarsenal.util.CreativeTab;
import com.midknight.knightcore.item.CoreBowItem;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
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
    public static final String VILLAGER_ARROW_NAME = "villager_arrow";
    public static final String BARBED_ARROW_NAME = "barbed_arrow";

    // Deferred Registers

    public static DeferredRegister<EntityType<?>> ENTITY_TYPES =
        DeferredRegister.create(ForgeRegistries.ENTITIES, ArchArsenal.MOD_ID);

    public static DeferredRegister<Item> ITEM =
    DeferredRegister.create(ForgeRegistries.ITEMS, ArchArsenal.MOD_ID);

    // - - - - - - - - - //
    // Registry Objects  //
    // - - - - - - - - - //

    // Arrow & Quivers Item //

    public static final RegistryObject<VillagerArrowItem> VILLAGER_ARROW =
            ITEM.register("villager_arrow",
                () -> new VillagerArrowItem(new Item.Properties().tab(CreativeTab.ARCHERS_ARSENAL
            )));

    public static final RegistryObject<BarbedArrowItem> BARBED_ARROW =
            ITEM.register("barbed_arrow",
                () -> new BarbedArrowItem(new Item.Properties().tab(CreativeTab.ARCHERS_ARSENAL
            )));

    public static final RegistryObject<QuiverItem> QUIVER =
            ITEM.register("quiver", () -> new QuiverItem(new Item.Properties()
                    .durability(64)
                    .tab(CreativeTab.ARCHERS_ARSENAL)
            ){

                @Override @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.archarsenal.quiver"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    // Bow Items //

    public static final RegistryObject<CoreBowItem> BOW_LEATHER =
            ITEM.register("bow_leather", () -> new CoreBowItem(new Item.Properties()
                    .durability(384)
                    .tab(CreativeTab.ARCHERS_ARSENAL),
                    0.0D,
                    false,
                    1
            ));

    public static final RegistryObject<CoreBowItem> BOW_IRON =
            ITEM.register("bow_iron", () -> new CoreBowItem(new Item.Properties()
                    .durability(576)
                    .tab(CreativeTab.ARCHERS_ARSENAL),
                    1.0D,
                    false,
                    0
            ));

    public static final RegistryObject<CoreBowItem> BOW_GOLD =
            ITEM.register("bow_gold", () -> new CoreBowItem(new Item.Properties()
                    .durability(384)
                    .tab(CreativeTab.ARCHERS_ARSENAL),
                    0D,
                    true,
                    1
            ) {

                @Override @ParametersAreNonnullByDefault
                public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                    tooltip.add(new TranslatableComponent("tooltip.archarsenal.bow_gold"));
                    super.appendHoverText(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject <CoreBowItem> BOW_DIAMOND =
            ITEM.register("bow_diamond", () -> new CoreBowItem(new Item.Properties()
                    .durability(768)
                    .tab(CreativeTab.ARCHERS_ARSENAL),
                    2.0D,
                    false,
                    0
            ));

    public static final RegistryObject <CoreBowItem> BOW_NETHERITE =
            ITEM.register("bow_netherite", () -> new CoreBowItem(new Item.Properties()
                            .durability(1152)
                            .fireResistant()
                            .tab(CreativeTab.ARCHERS_ARSENAL),
                            4.0D,
                            false,
                            0
                    ) {
                        @Override @ParametersAreNonnullByDefault
                        public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
                            tooltip.add(new TranslatableComponent("tooltip.archarsenal.bow_netherite"));
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

    // Register Methods

    public static void registerENTITY() {ENTITY_TYPES.register(eventBus);}
    public static void registerITEM() {ITEM.register(eventBus);}
}
