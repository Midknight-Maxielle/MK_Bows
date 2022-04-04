package net.midknight.bowyery.util;

import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.midknight.bowyery.item.BowyeryBowItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class BowyeryModels {

    public static void registerModels() {
        registerBow(ModRegistry.BOW_LEATHER);
        registerBow(ModRegistry.BOW_IRON);
        registerBow(ModRegistry.BOW_GOLD);
        registerBow(ModRegistry.BOW_DIAMOND);
        registerBow(ModRegistry.BOW_NETHERITE);
    }

    private static void registerBow(Item bow) {
        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pull"),
                (stack, world, entity, seed) -> {
                    if (entity == null) {
                        return 0.0F;
                    }
                    if (entity.getActiveItem() != stack) {
                        return 0.0F;
                    }
                    BowyeryBowItem bowyerBow = (BowyeryBowItem) bow;
                    return (float) (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / bowyerBow.getMaxDraw();
                });
        FabricModelPredicateProviderRegistry.register(bow, new Identifier("pulling"),
                (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack ? 1.0F : 0.0F);
    }

}
