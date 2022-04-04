package net.midknight.bowyery.mixin;

import net.midknight.bowyery.item.BowyeryQuiverItem;
import net.midknight.bowyery.util.IQuiverMixin;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowItem.class)
public abstract class QuiverMixin implements IQuiverMixin {
	@Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
	protected void injectPreservationRoll(ItemStack stack, World world, LivingEntity player, int remainingDuration, CallbackInfo callback) {

		ItemStack offHand = player.getOffHandStack();
		ItemStack arrowStack = player.getArrowType(stack);
		boolean quiverRoll = false;
		if(offHand.getItem() instanceof BowyeryQuiverItem quiver) {
			quiverRoll = quiver.rollArrowPreservation(world);
		}
		if(quiverRoll) {
			offHand.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(player.getActiveHand()));
			arrowStack.increment(1);
		}
	}
}
