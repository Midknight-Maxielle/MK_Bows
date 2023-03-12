package net.mk.archers_arsenal.items;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.mk.archers_arsenal.ArchersArsenal;
import net.mk.archers_arsenal.items.ModItems;

public class ModItemGroup {

    public static ItemGroup ARCHERS_ARSENAL;

    public static void registerItemGroups() {
        ARCHERS_ARSENAL = FabricItemGroup.builder(new Identifier(ArchersArsenal.MOD_ID, "archers_arsenal"))
                .displayName(Text.translatable("itemgroup.archers_arsenal"))
                .icon(() -> new ItemStack(ModItems.BOW_NETHERITE)).build();
    }
}
