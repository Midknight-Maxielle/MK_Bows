package net.mk.archers_arsenal.utility;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.mk.archers_arsenal.ArchersArsenal;
import net.mk.archers_arsenal.items.ModItems;

public class ModItemGroup {

    public static final ItemGroup ARCHERS_ARSENAL = FabricItemGroupBuilder.build(
            new Identifier(ArchersArsenal.MOD_ID, "tab"), () -> new ItemStack(ModItems.BOW_NETHERITE));
}
