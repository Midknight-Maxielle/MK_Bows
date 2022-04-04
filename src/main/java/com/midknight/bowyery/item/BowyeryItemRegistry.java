package com.midknight.bowyery.item;

import com.midknight.bowyery.Bowyery;
import com.midknight.bowyery.item.arrows.BarbedArrowItem;
import com.midknight.bowyery.item.arrows.VillagerArrowItem;
import com.midknight.bowyery.util.BowyeryTab;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

public class BowyeryItemRegistry {


    // REGISTRY CREATION //
    // | | | | | | | | | //
    // V V V V V V V V V //

    public static final DeferredRegister<Item> ITEM = DeferredRegister.create(ForgeRegistries.ITEMS, Bowyery.MOD_ID);

    // BOW ITEM REGISTRATION //
    // | | | | | | | | | | | //
    // V V V V V V V V V V V //

    public static final RegistryObject<BowyeryBowItem> BOW_LEATHER =
            ITEM.register("bow_leather", () -> new BowyeryBowItem(new Item.Properties()
                    .maxDamage(512)
                    .group(BowyeryTab.BOWYER),
                    0.3D,
                    false,
                    false
            ));

    public static final RegistryObject<BowyeryBowItem> BOW_IRON =
            ITEM.register("bow_iron", () -> new BowyeryBowItem(new Item.Properties()
                    .maxDamage(768)
                    .group(BowyeryTab.BOWYER),
                    0.6D,
                    false,
                    false
            ));

    public static final RegistryObject<BowyeryBowItem> BOW_GOLD =
            ITEM.register("bow_gold", () -> new BowyeryBowItem(new Item.Properties()
                    .maxDamage(384)
                    .group(BowyeryTab.BOWYER),
                    0.0D,
                    true,
                    false
            ) {
                @Override
                public void addInformation(ItemStack stack, @Nullable World worldIn,
                                           List<ITextComponent> tooltip, ITooltipFlag flagIn) {

                    tooltip.add(new TranslationTextComponent("tooltip.bowyery.bow_gold"));
                    super.addInformation(stack, worldIn, tooltip, flagIn);
                }
            });

    public static final RegistryObject <BowyeryBowItem> BOW_DIAMOND =
            ITEM.register("bow_diamond", () -> new BowyeryBowItem(new Item.Properties()
                    .maxDamage(1024)
                    .group(BowyeryTab.BOWYER),
                    1.0D,
                    false,
                    false
            ));

    public static final RegistryObject <BowyeryBowItem> BOW_NETHERITE =
            ITEM.register("bow_netherite", () -> new BowyeryBowItem(new Item.Properties()
                    .maxDamage(2048)
                    .group(BowyeryTab.BOWYER),
                    1.3D,
                    false,
                    true
            ) {
                        @Override
                        public void addInformation(ItemStack stack, @Nullable World worldIn,
                                                   List<ITextComponent> tooltip, ITooltipFlag flagIn) {

                            tooltip.add(new TranslationTextComponent("tooltip.bowyery.bow_netherite"));
                            super.addInformation(stack, worldIn, tooltip, flagIn);
                        }
                    }
            );

    // ARROW ITEM REGISTRATION //
    // | | | | | | | | | | | | //
    // V V V V V V V V V V V V //

    public static final RegistryObject<VillagerArrowItem> VILLAGER_ARROW =
            ITEM.register("villager_arrow", () -> new VillagerArrowItem(new Item.Properties().group(BowyeryTab.BOWYER))
            );

    public static final RegistryObject<BarbedArrowItem> BARBED_ARROW =
            ITEM.register("barbed_arrow", () -> new BarbedArrowItem(new Item.Properties().group(BowyeryTab.BOWYER))
            );

    public static void register (IEventBus eventBus) { ITEM.register(eventBus); }
}
