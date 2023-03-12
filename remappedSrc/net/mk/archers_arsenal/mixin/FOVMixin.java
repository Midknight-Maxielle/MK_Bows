package net.mk.archers_arsenal.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.mk.archers_arsenal.items.ModBowItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(AbstractClientPlayerEntity.class)
public class FOVMixin {
	@Redirect(method = "getFovMultiplier", at =@At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
	private boolean init(ItemStack stack, Item item) {
		return stack.getItem() instanceof ModBowItem;
	}
}
