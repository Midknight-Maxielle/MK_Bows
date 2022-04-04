package net.midknight.bowyery.util;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.midknight.bowyery.Bowyery;
import net.midknight.bowyery.entity.BarbedArrowEntity;
import net.midknight.bowyery.entity.VillagerArrowEntity;
import net.midknight.bowyery.item.BarbedArrowItem;
import net.midknight.bowyery.item.BowyeryBowItem;
import net.midknight.bowyery.item.BowyeryQuiverItem;
import net.midknight.bowyery.item.VillagerArrowItem;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.List;

public class ModRegistry {

// Bow Items

    public static final Item BOW_LEATHER = registerItem("bow_leather",
        new BowyeryBowItem(new FabricItemSettings().group(BowyeryTab.BOWYER).maxDamage(512),
            0.0D,
            false,
            false,
            8
            )
    );

    public static final Item BOW_IRON = registerItem("bow_iron",
        new BowyeryBowItem(new FabricItemSettings().group(BowyeryTab.BOWYER).maxDamage(768),
        1.0D,
        false,
        false,
        0
        )
    );

    public static final Item BOW_GOLD = registerItem("bow_gold",
        new BowyeryBowItem(new FabricItemSettings().group(BowyeryTab.BOWYER).maxDamage(384),
        -0.5D,
        true,
        false,
        4
        )
        {
            @Override
            public void appendTooltip(ItemStack stack, World worldIn, List<Text> tooltip, TooltipContext flagIn) {
                tooltip.add(new TranslatableText("tooltip.bowyery.bow_gold"));
                super.appendTooltip(stack, worldIn, tooltip, flagIn);
            }
        }
    );

    public static final Item BOW_DIAMOND = registerItem("bow_diamond",
        new BowyeryBowItem(new FabricItemSettings().group(BowyeryTab.BOWYER).maxDamage(1024),
        2.0D,
        false,
        false,
        0
        )
    );

    public static final Item BOW_NETHERITE = registerItem("bow_netherite",
        new BowyeryBowItem(new FabricItemSettings().group(BowyeryTab.BOWYER).maxDamage(2048),
        4.0D,
        false,
        true,
        0
        )
        {
            @Override
            public void appendTooltip(ItemStack stack, World worldIn, List<Text> tooltip, TooltipContext flagIn) {
                tooltip.add(new TranslatableText("tooltip.bowyery.bow_netherite"));
                super.appendTooltip(stack, worldIn, tooltip, flagIn);
            }
        }
    );

// Quiver & Arrow Items & Entities

    public static final Item QUIVER = registerItem("quiver",
        new BowyeryQuiverItem(new FabricItemSettings().group(BowyeryTab.BOWYER).maxDamage(128))
    );

    public static final Item VILLAGER_ARROW =  registerItem("villager_arrow",
        new VillagerArrowItem(new FabricItemSettings().group(BowyeryTab.BOWYER))
    );

    public static final Item BARBED_ARROW =  registerItem("barbed_arrow",
            new BarbedArrowItem(new FabricItemSettings().group(BowyeryTab.BOWYER))
    );

    public static final EntityType<VillagerArrowEntity> VILLAGER_ARROW_TYPE = Registry.register(Registry.ENTITY_TYPE,
        new Identifier(Bowyery.MOD_ID, "villager_arrow"),
            FabricEntityTypeBuilder.<VillagerArrowEntity>create(SpawnGroup.MISC, VillagerArrowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F,0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );

    public static final EntityType<BarbedArrowEntity> BARBED_ARROW_TYPE = Registry.register(Registry.ENTITY_TYPE,
            new Identifier(Bowyery.MOD_ID, "barbed_arrow"),
            FabricEntityTypeBuilder.<BarbedArrowEntity>create(SpawnGroup.MISC, BarbedArrowEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25F,0.25F))
                    .trackRangeBlocks(4).trackedUpdateRate(10)
                    .build()
    );



// Arrow Entities

// Methods

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(Bowyery.MOD_ID, name), item);
    }

    public static void registerLogs() {
        Bowyery.LOGGER.info("Registry starting for " + Bowyery.MOD_ID);

    }
}
