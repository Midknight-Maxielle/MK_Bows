package net.mk.events;

import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.mk.items.QuiverItem;

public class QuiverLogicHandler implements UseItemCallback {

    @Override
    public TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand) {

        ItemStack offhand = player.getOffHandStack();
        ItemStack mainhand = player.getMainHandStack();
        ItemStack arrows = player.getProjectileType(mainhand);
        boolean roll = false;

        if(offhand.getItem() instanceof QuiverItem quiver && mainhand.getItem() instanceof BowItem) {

            int infinityLVL = EnchantmentHelper.getLevel(Enchantments.INFINITY, mainhand);
            boolean isInfinity = (infinityLVL > 0 || player.getAbilities().creativeMode);

            if (!isInfinity) {

                roll = quiver.rollArrowSave(world);

                if (roll) {
                    offhand.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(player.getActiveHand()));
                    arrows.increment(1);
                }
            }
        }

        return TypedActionResult.pass(ItemStack.EMPTY);
    }
}