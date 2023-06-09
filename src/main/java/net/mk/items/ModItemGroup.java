package net.mk.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mk.ArchersArsenal;

public class ModItemGroup {

    public static final RegistryKey<ItemGroup> ARCHERS_ARSENAL = RegistryKey.of(RegistryKeys.ITEM_GROUP,
            new Identifier(ArchersArsenal.MOD_ID, "archers_arsenal"));

    public static void registerItemGroups() {
        Registry.register(Registries.ITEM_GROUP, ARCHERS_ARSENAL, FabricItemGroup.builder()
                .icon(() -> new ItemStack(ModItems.BOW_NETHERITE))
                .displayName(Text.translatable("itemGroup.archers_arsenal.tab"))
                .build());
    }

}