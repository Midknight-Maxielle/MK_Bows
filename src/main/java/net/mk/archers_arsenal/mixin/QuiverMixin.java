package net.mk.archers_arsenal.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.mk.archers_arsenal.items.QuiverItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BowItem.class)
public abstract class QuiverMixin {

    @Inject(method = "onStoppedUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;decrement(I)V"))
    protected void injectRoll(ItemStack stack, World world, LivingEntity player, int remainingDuration, CallbackInfo callback) {

        ItemStack offHand = player.getOffHandStack();
        ItemStack arrows = player.getArrowType(stack);
        boolean quiverRoll = false;

        if(offHand.getItem() instanceof QuiverItem quiver) {
            quiverRoll = quiver.rollArrowSave(world);
        }
        if(quiverRoll) {
            offHand.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(player.getActiveHand()));
            arrows.increment(1);
        }
    }
}
