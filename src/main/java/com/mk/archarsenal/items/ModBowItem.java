package com.mk.archarsenal.items;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.system.NonnullDefault;

public class ModBowItem extends BowItem {

    // Fields

    public boolean NAT_INFINITY;

    // Constructor Methods

    public ModBowItem(Properties properties, boolean inf) {
        super(properties);
        NAT_INFINITY = inf;
    }

    // Methods //

    @NonnullDefault
    public boolean canDraw(ItemStack bow, Player player) {

        boolean IS_CREATIVE = player.getAbilities().instabuild;
        return
                IS_CREATIVE || EnchantmentHelper.getTagEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow) > 0 || !(player.getProjectile(bow).isEmpty()) || NAT_INFINITY;
    }

    @Override
    @NotNull @NonnullDefault
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {

        ItemStack bow = player.getItemInHand(hand);
        boolean CAN_DRAW = canDraw(bow, player);

        if (!CAN_DRAW) {
            return InteractionResultHolder.fail(bow);
        } else {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(bow);
        }
    }

    @Override
    @NonnullDefault
    public void releaseUsing(ItemStack bow, Level world, LivingEntity entity, int timeLeft) {

        if (entity instanceof Player player) {
            boolean IS_CREATIVE = player.getAbilities().instabuild;
            boolean IS_INFINITE = NAT_INFINITY || EnchantmentHelper.getTagEnchantmentLevel(Enchantments.INFINITY_ARROWS, bow) > 0;
            ItemStack arrows = player.getProjectile(bow);

            if (!arrows.isEmpty() || IS_INFINITE) {
                if (arrows.isEmpty()) {
                    arrows = new ItemStack(Items.ARROW);
                }

                int i = this.getUseDuration(bow) - timeLeft;
                float f = getPowerForTime(i);

                if (!((double) f < 0.1)) {

                    if (!world.isClientSide) {

                        ArrowItem arrow = (ArrowItem) (arrows.getItem() instanceof ArrowItem ? arrows.getItem() : Items.ARROW);

                        AbstractArrow arrowEntity = arrow.createArrow(world, arrows, player);
                        arrowEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, f * 3.0F, 1.0F);

                        if (f == 1.0F) {
                            arrowEntity.setCritArrow(true);
                        }

                        int powerLVL = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.POWER_ARROWS, bow);
                        if (powerLVL > 0) {
                            arrowEntity.setBaseDamage(arrowEntity.getBaseDamage() + (double) powerLVL * 0.5 + 0.5);
                        }

                        int punchLVL = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PUNCH_ARROWS, bow);
                        if (punchLVL > 0) {
                            arrowEntity.setKnockback(punchLVL);
                        }

                        int flameLVL = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FLAMING_ARROWS, bow);
                        if (flameLVL > 0) {
                            arrowEntity.setSecondsOnFire(100);
                        }

                        bow.hurtAndBreak(1, player, (p) -> p.broadcastBreakEvent(player.getUsedItemHand()));

                        if (IS_INFINITE || IS_CREATIVE || (arrows.is(Items.SPECTRAL_ARROW) || arrows.is(Items.TIPPED_ARROW))) {
                            arrowEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                        }

                        world.addFreshEntity(arrowEntity);
                    }

                    world.playSound(
                            (Player) null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS,
                            1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);

                    if (!IS_INFINITE && !IS_CREATIVE) {

                        arrows.shrink(1);
                        if (arrows.isEmpty()) {
                            player.getInventory().removeItem(arrows);
                        }
                    }

                    player.awardStat(Stats.ITEM_USED.get(this));

                }
            }
        }
    }
}
