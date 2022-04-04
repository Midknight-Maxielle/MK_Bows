package net.midknight.bowyery.item;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

import java.util.function.Predicate;

public class BowyeryBowItem extends BowItem {

    // Constructor Fields

    double dmgMod;
    boolean inf;
    boolean flame;
    float maxDraw;


    // Constructor Method

    public BowyeryBowItem(Item.Settings settings, double dmgMod, boolean inf, boolean flame, int drawMod) {
        super(settings);
        this.dmgMod = dmgMod;
        this.inf = inf;
        this.flame = flame;
        this.maxDraw = (20.0F - drawMod);
    }

    // Override Methods

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingDuration) {

        boolean infFlag2;
        int i;
        float pullFloat;
        int pullDuration = this.getMaxUseTime(stack);

        // If the user is not a player, end the method.
        if (!(user instanceof PlayerEntity)) {
            return;
        }

        PlayerEntity player = (PlayerEntity)user;
        boolean infFlag = player.getAbilities().creativeMode || inf || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
        ItemStack arrowStack = player.getArrowType(stack);

        // If the player has no arrows and does not have Infinity, the bow does nothing.
        if (arrowStack.isEmpty() && !infFlag) {
            return;
        }

        // If the player has no arrows but does have Infinity, generate a new Arrow item.

        if (arrowStack.isEmpty()) {
            arrowStack = new ItemStack(Items.ARROW);
        }

        // If the bow has not been pulled for long enough (at least 1 tick), do nothing.

        if ((double)(pullFloat = BowyeryBowItem.getPullProgress(i = pullDuration - remainingDuration, this.maxDraw)) < 0.1) {
            return;
        }

        boolean infFlag3 = infFlag2 = infFlag && arrowStack.isOf(Items.ARROW);

        // Ensures that the players is not desynced from the server.

        if (!world.isClient) {
            int punchLevel;
            int powerLevel;

            // Ensures that the arrow item is always an arrow.

            ArrowItem arrowItem = (ArrowItem) (arrowStack.getItem() instanceof ArrowItem ? arrowStack.getItem() : Items.ARROW);

            // Creates the arrow entity based on the arrow item.

            PersistentProjectileEntity projectileEntity = arrowItem.createArrow(world, arrowStack, player);
            projectileEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, pullFloat * 3.0F, 1.0F);

            // If the player releases the bow at the exact moment it is fully pulled, make the arrow a critical arrow.

            if (pullFloat == 1.0F) {
                projectileEntity.setCritical(true);
            }
            // Sets the additional damage, knockback, and flame time for arrows shot from enchanted bows.

            if ((powerLevel = EnchantmentHelper.getLevel(Enchantments.POWER, stack)) > 0) {
                projectileEntity.setDamage(projectileEntity.getDamage() + (double) powerLevel * 0.5 + 0.5);
            }
            if ((punchLevel = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack)) > 0) {
                projectileEntity.setPunch(punchLevel);
            }
            if ((EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) || flame ) {
                projectileEntity.setOnFireFor(100);
            }

            // Damage the bow's durability by 1 after the arrow is shot.
            stack.damage(1, player, p -> p.sendToolBreakStatus(player.getActiveHand()));

            // Determines if the arrow entity will be able to be picked up as an item by the player.

            if (infFlag2 || player.getAbilities().creativeMode && (arrowStack.isOf(Items.SPECTRAL_ARROW) || arrowStack.isOf(Items.TIPPED_ARROW))) {
                projectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
            }
            world.spawnEntity(projectileEntity);
        }

        // Plays the arrow shooting sound and sets a semi-random volume and pitch.

        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F /
                (world.getRandom().nextFloat() * 0.4F + 1.2F) + pullFloat * 0.5F);

        // Removes one arrow from the stack of arrows if appropriate, and removes an empty arrow stack if needed.

        if (!infFlag2 && !player.getAbilities().creativeMode) {

            ItemStack offHand = player.getOffHandStack();
            boolean quiverRoll = false;
            if(offHand.getItem() instanceof BowyeryQuiverItem quiver) {
                quiverRoll = quiver.rollArrowPreservation(world);
            }
            if(quiverRoll) {
                offHand.damage(1, player, playerEntity -> playerEntity.sendToolBreakStatus(player.getActiveHand()));
                return;
            }
            arrowStack.decrement(1);
            if (arrowStack.isEmpty()) {
                player.getInventory().removeOne(arrowStack);
            }
        }
        // Marks one use of the bow in the player statistics.

        player.incrementStat(Stats.USED.getOrCreateStat(this));
    }

    // Calculates the percentage towards fully drawn the bow is at release time, represented as a float between 0.0 and 1.0.

    public static float getPullProgress(int useDuration, float maxDraw) {
        float f = (float) useDuration / maxDraw;
        if ((f = (f * f * f * 2.0F) / 3.0F) > 1.0F) {
            f = 1.0F;
        }
        return f;
    }

    // Gets the amount of time needed for a full draw, by default.

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000;
    }

    // Fetches the appropriate code for the use action from the index.

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        boolean infFlag;
        ItemStack itemStack = player.getStackInHand(hand);
        boolean infFlag2 = infFlag = !player.getArrowType(itemStack).isEmpty();
        if (player.getAbilities().creativeMode || infFlag) {
            player.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }

    @Override
    public Predicate<ItemStack> getProjectiles() {
        return BOW_PROJECTILES;
    }

    @Override
    public int getRange() {
        return 15;
    }

    public float getMaxDraw() {

        System.out.println(this.maxDraw);
        return this.maxDraw;
    }
}








