package net.mk.archers_arsenal.items;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.lwjgl.system.NonnullDefault;


public class ModBowItem extends BowItem {

    // Public fields - used to set custom damage and draw-time modifiers + manage bow use and arrow pickup for infinity and creative mode.

    public int DMG_MOD;
    public boolean HAS_NATURAL_INFINITY;

    // Constructor

    public ModBowItem(Settings settings, int dmgMod, boolean inf) {

        super(settings);
        DMG_MOD = dmgMod;
        HAS_NATURAL_INFINITY = inf;

    }

    // canDraw method - called to determine if the bow can be used.

    public boolean canDraw(ItemStack bow, PlayerEntity player) {

        boolean IS_CREATIVE = player.getAbilities().creativeMode;
        return
                IS_CREATIVE ||
                        EnchantmentHelper.getLevel(Enchantments.INFINITY, bow) > 0 ||
                        !player.getArrowType(bow).isEmpty() ||
                        HAS_NATURAL_INFINITY;

    }

    // Override of the use method that forces it to call canDraw to determine if the item can be used.

    @Override
    @NonnullDefault
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {

        ItemStack bow = player.getStackInHand(hand);
        boolean CAN_DRAW = canDraw(bow, player);

        if (!CAN_DRAW) {
            return TypedActionResult.fail(bow);
        } else {
            player.setCurrentHand(hand);
            return TypedActionResult.consume(bow);
        }
    }

    @Override
    @NonnullDefault
    public void onStoppedUsing(ItemStack bow, World world, LivingEntity entity, int timeLeft) {

        if (entity instanceof PlayerEntity player) {
            boolean IS_CREATIVE = player.getAbilities().creativeMode;
            boolean HAS_INFINITY = HAS_NATURAL_INFINITY || IS_CREATIVE || EnchantmentHelper.getLevel(Enchantments.INFINITY, bow) > 0;
            ItemStack arrows = player.getArrowType(bow);

            if (!arrows.isEmpty() || HAS_INFINITY) {
                if (arrows.isEmpty()) {
                    arrows = new ItemStack(Items.ARROW);
                }

                int i = this.getMaxUseTime(bow) - timeLeft;
                float f = getPullProgress(i);

                if (!((double) f < 0.1)) {

                    if (!world.isClient) {

                        // ensures that the ammo being used is an arrow type
                        ArrowItem arrow = (ArrowItem) (arrows.getItem() instanceof ArrowItem ? arrows.getItem() : Items.ARROW);

                        // generates a new arrow entity based on the type used
                        PersistentProjectileEntity ppEntity = arrow.createArrow(world, arrows, player);
                        ppEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, f * 3.0F, 1.0F);

                        // Sets the arrow to critical if it was loosed at exactly max pull time.
                        if (f == 1.0F) {
                            ppEntity.setCritical(true);
                        }

                        // Sets effects for various bow enchantments

                        int powerLVL = EnchantmentHelper.getLevel(Enchantments.PUNCH, bow);
                        if (powerLVL > 0) {
                            ppEntity.setDamage(ppEntity.getDamage() + (double) powerLVL * 0.5 + 0.5);
                        }

                        int punchLVL = EnchantmentHelper.getLevel(Enchantments.PUNCH, bow);
                        if (punchLVL > 0) {
                            ppEntity.setPunch(punchLVL);
                        }

                        int flameLVL = EnchantmentHelper.getLevel(Enchantments.FLAME, bow);
                        if (flameLVL > 0) {
                            ppEntity.setOnFireFor(100);

                        }

                        bow.damage(1, player, (p) -> {
                            p.sendToolBreakStatus(player.getActiveHand());
                        });
                        if (HAS_INFINITY || IS_CREATIVE && (arrows.isOf(Items.SPECTRAL_ARROW) || arrows.isOf(Items.TIPPED_ARROW))) {
                            ppEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                        }

                        world.spawnEntity(ppEntity);
                    }

                    world.playSound(
                            (PlayerEntity) null, player.getX(), player.getY(), player.getZ(),
                            SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS,
                            1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!HAS_INFINITY && !IS_CREATIVE) {

                        arrows.decrement(1);
                        if (arrows.isEmpty()) {
                            player.getInventory().removeOne(arrows);
                        }
                    }

                    // marks one use of the bow in the player's statistics.

                    player.incrementStat(Stats.USED.getOrCreateStat(this));
                }
            }
        }
    }
}
