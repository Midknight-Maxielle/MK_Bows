package net.mk.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.mk.ArchersArsenal;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.NonnullDefault;

import java.util.List;

public class ModItems {

    // Bow Items

    public static final Item BOW_LEATHER = registerItem("bow_leather",
            new ModBowItem(new FabricItemSettings()
                    .maxDamage(384),
                    0,
                    false)
    );

    public static final Item BOW_IRON = registerItem("bow_iron",
            new ModBowItem(new FabricItemSettings()
                    .maxDamage(576),
                    1,
                    false)
    );

    public static final Item BOW_GOLD = registerItem("bow_gold",
            new ModBowItem(new FabricItemSettings()
                    .maxDamage(384),
                    0,
                    true
            ) {
                @Override @NonnullDefault
                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                    tooltip.add(Text.literal("This bow has a natural Infinity effect."));
                    super.appendTooltip(stack, world, tooltip, context);
                }
            }
    );

    public static final Item BOW_DIAMOND = registerItem("bow_diamond",
            new ModBowItem(new FabricItemSettings()
                    .maxDamage(768),
                    1,
                    false)
    );

    public static final Item BOW_NETHERITE = registerItem("bow_netherite",
            new ModBowItem(new FabricItemSettings()
                    .maxDamage(1152)
                    .fireproof(),
                    2,
                    false
            ) {
                @Override @NonnullDefault
                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                    tooltip.add(Text.literal("This bow is fireproof."));
                    super.appendTooltip(stack, world, tooltip, context);
                }
            }
    );

    // Arrow Items //

    public static final Item BARBED_ARROW = registerItem("barbed_arrow", new BarbedArrowItem(new FabricItemSettings()));
    public static final Item VILLAGER_ARROW = registerItem("villager_arrow", new VillagerArrowItem(new FabricItemSettings()));

    // Quiver Item //

    public static final Item QUIVER = registerItem("quiver", new QuiverItem(new FabricItemSettings().maxDamage(64)));

    // Register Methods //

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(ArchersArsenal.MOD_ID, name), item);
    }

    public static void addItemsToItemGroup() {
        addToItemGroup(ModItemGroup.ARCHERS_ARSENAL, BOW_LEATHER);
        addToItemGroup(ModItemGroup.ARCHERS_ARSENAL, BOW_IRON);
        addToItemGroup(ModItemGroup.ARCHERS_ARSENAL, BOW_GOLD);
        addToItemGroup(ModItemGroup.ARCHERS_ARSENAL, BOW_DIAMOND);
        addToItemGroup(ModItemGroup.ARCHERS_ARSENAL, BOW_NETHERITE);
        addToItemGroup(ModItemGroup.ARCHERS_ARSENAL, BARBED_ARROW);
        addToItemGroup(ModItemGroup.ARCHERS_ARSENAL, VILLAGER_ARROW);
        addToItemGroup(ModItemGroup.ARCHERS_ARSENAL, QUIVER);

    }

    private static void addToItemGroup(ItemGroup group, Item item) {
        ItemGroupEvents.modifyEntriesEvent(group).register(entries -> entries.add(item));
    }

    public static void registerModItems() {
        ArchersArsenal.LOGGER.debug("Registering modded items for " + ArchersArsenal.MOD_ID);
    }
}