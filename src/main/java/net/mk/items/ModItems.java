package net.mk.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.mk.ArchersArsenal;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.NonnullDefault;

import java.util.List;

public class ModItems {

    // Bow Items

    public static final Item BOW_LEATHER = registerItem("bow_leather",
            new ModBowItem(new FabricItemSettings()
                    .group(ModItemGroup.ARCHERS_ARSENAL)
                    .maxDamage(578),
                    false)
    );

    public static final Item BOW_IRON = registerItem("bow_iron",
            new ModBowItem(new FabricItemSettings()
                    .group(ModItemGroup.ARCHERS_ARSENAL)
                    .maxDamage(770),
                    false)
    );

    public static final Item BOW_GOLD = registerItem("bow_gold",
            new ModBowItem(new FabricItemSettings()
                    .group(ModItemGroup.ARCHERS_ARSENAL)
                    .maxDamage(289),
                    true
            ) {
                @Override @NonnullDefault
                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                    tooltip.add(Text.literal("Arrows shot from this bow are always critical."));
                    super.appendTooltip(stack, world, tooltip, context);
                }
            }
    );

    public static final Item BOW_DIAMOND = registerItem("bow_diamond",
            new ModBowItem(new FabricItemSettings()
                    .group(ModItemGroup.ARCHERS_ARSENAL)
                    .maxDamage(1540),
                    false)
    );

    public static final Item BOW_NETHERITE = registerItem("bow_netherite",
            new ModBowItem(new FabricItemSettings()
                    .group(ModItemGroup.ARCHERS_ARSENAL)
                    .maxDamage(2310)
                    .fireproof(),
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

    public static final Item BARBED_ARROW = registerItem("barbed_arrow", new BarbedArrowItem(new FabricItemSettings().group(ModItemGroup.ARCHERS_ARSENAL)));
    public static final Item VILLAGER_ARROW = registerItem("villager_arrow", new VillagerArrowItem(new FabricItemSettings().group(ModItemGroup.ARCHERS_ARSENAL)));

    // Quiver Item //

    public static final Item QUIVER = registerItem("quiver",
            new QuiverItem(new FabricItemSettings()
                    .group(ModItemGroup.ARCHERS_ARSENAL)
                    .maxDamage(64)
            ) {
                @Override
                @NonnullDefault
                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                    tooltip.add(Text.literal("Has a 25% of saving shot arrows when held in your off-hand."));
                    super.appendTooltip(stack, world, tooltip, context);
                }
            }
    );

    // Register Methods //

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ArchersArsenal.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ArchersArsenal.LOGGER.debug("Registering modded items for " + ArchersArsenal.MOD_ID);
    }
}