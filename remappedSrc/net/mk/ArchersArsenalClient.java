package net.mk;

import ClampedModelPredicateProvider;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.mk.entities.ModEntities;
import net.mk.items.ModItems;
import net.mk.renderers.*;

public class ArchersArsenalClient implements ClientModInitializer {

    private static final Identifier PULL = new Identifier("pull");
    private static final Identifier PULLING = new Identifier("pulling");

    private static final ClampedModelPredicateProvider PULL_PROVIDER = (stack, world, entity, seed) ->
            (entity == null ? 0.0F : entity.getActiveItem() != stack ? 0.0F :
                    (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.F);

    private static final ClampedModelPredicateProvider PULLING_PROVIDER = (stack, world, entity, seed) ->
            ((entity != null) && entity.isUsingItem() && (entity.getActiveItem() == stack) ? 1.0F : 0.0F);

    private void registerBow(Item bow) {
        ModelPredicateProviderRegistry.register(bow, PULL, PULL_PROVIDER);
        ModelPredicateProviderRegistry.register(bow, PULLING, PULLING_PROVIDER);
    }

    @Override
    public void onInitializeClient() {

        EntityRendererRegistry.register(ModEntities.BARBED_ARROW_TYPE, BarbedArrowRenderer::new);
        EntityRendererRegistry.register(ModEntities.VILLAGER_ARROW_TYPE, VillagerArrowRenderer::new);

        this.registerBow(ModItems.BOW_LEATHER);
        this.registerBow(ModItems.BOW_IRON);
        this.registerBow(ModItems.BOW_GOLD);
        this.registerBow(ModItems.BOW_DIAMOND);
        this.registerBow(ModItems.BOW_NETHERITE);

    }

}