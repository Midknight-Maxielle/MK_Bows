package net.mk.archers_arsenal;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.mixin.object.builder.client.ModelPredicateProviderRegistrySpecificAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.item.UnclampedModelPredicateProvider;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.mk.archers_arsenal.entities.ModEntities;
import net.mk.archers_arsenal.items.ModBowItem;
import net.mk.archers_arsenal.items.ModItems;
import net.mk.archers_arsenal.renderers.BarbedArrowRenderer;
import net.mk.archers_arsenal.renderers.VillagerArrowRenderer;
import net.mk.archers_arsenal.utility.EntitySpawnPacket;

import java.util.UUID;

public class ArchersArsenalClient implements ClientModInitializer {

    public static final Identifier PacketID = new Identifier(ArchersArsenal.MOD_ID, "spawn_packet");

    private static final Identifier PULL = new Identifier("pull");
    private static final Identifier PULLING = new Identifier("pulling");

    private static final UnclampedModelPredicateProvider PULL_PROVIDER = (stack, world, entity, seed) ->
            (entity == null ? 0.0F : entity.getActiveItem() != stack ? 0.0F :
                    (stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / 20.F);

    private static final UnclampedModelPredicateProvider PULLING_PROVIDER = (stack, world, entity, seed) ->
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
