package net.mk.archers_arsenal.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.mk.archers_arsenal.ArchersArsenal;

public class ModItemRegistry {

    public static final Item TEST_BOW = registerItem("test_bow",
            new ModBowItem(new FabricItemSettings().group(ItemGroup.COMBAT).maxDamage(512),
                    0,
                    8,
                    false)
    );

    public static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(ArchersArsenal.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ArchersArsenal.LOGGER.debug("Registering modded items for " + ArchersArsenal.MOD_ID);
    }
}
