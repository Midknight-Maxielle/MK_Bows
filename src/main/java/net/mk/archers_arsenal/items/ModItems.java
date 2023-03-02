package net.mk.archers_arsenal.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.mk.archers_arsenal.ArchersArsenal;
import net.mk.archers_arsenal.utility.ModItemGroup;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.NonnullDefault;

import java.util.List;

public class ModItems {

    // Bow Items

    public static final Item BOW_LEATHER = registerItem("bow_leather",
            new ModBowItem(new FabricItemSettings().group(ModItemGroup.ARCHERS_ARSENAL)
                    .maxDamage(384),
                    0,
                    false)
    );

    public static final Item BOW_IRON = registerItem("bow_iron",
            new ModBowItem(new FabricItemSettings().group(ModItemGroup.ARCHERS_ARSENAL)
                    .maxDamage(576),
                    1,
                    false)
    );

    public static final Item BOW_GOLD = registerItem("bow_gold",
            new ModBowItem(new FabricItemSettings().group(ModItemGroup.ARCHERS_ARSENAL)
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
            new ModBowItem(new FabricItemSettings().group(ModItemGroup.ARCHERS_ARSENAL)
                    .maxDamage(768),
                    1,
                    false)
    );

    public static final Item BOW_NETHERITE = registerItem("bow_netherite",
            new ModBowItem(new FabricItemSettings().group(ModItemGroup.ARCHERS_ARSENAL)
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

    // Arrow Items

    public static final Item BARBED_ARROW = registerItem("barbed_arrow", new BarbedArrowItem(new FabricItemSettings().group(ModItemGroup.ARCHERS_ARSENAL))
    );

    // Register Methods

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ArchersArsenal.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ArchersArsenal.LOGGER.debug("Registering modded items for " + ArchersArsenal.MOD_ID);
    }
}
