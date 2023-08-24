package net.mk.items;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.mk.ArchersArsenal;

public class ModItemGroup {

    public static final ItemGroup ARCHERS_ARSENAL = FabricItemGroupBuilder.build(
            new Identifier(ArchersArsenal.MOD_ID, "tab"), () -> new ItemStack(ModItems.BOW_NETHERITE));
}