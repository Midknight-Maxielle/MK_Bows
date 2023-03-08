package net.mk.archers_arsenal;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.mk.archers_arsenal.entities.ModEntities;
import net.mk.archers_arsenal.events.QuiverLogicHandler;
import net.mk.archers_arsenal.items.ModItems;
import net.mk.archers_arsenal.renderers.BarbedArrowRenderer;
import net.mk.archers_arsenal.utility.EntitySpawnPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class ArchersArsenal implements ModInitializer {

	public static final String MOD_ID = "archers_arsenal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Identifier PacketID = new Identifier(ArchersArsenal.MOD_ID, "spawn_packet");

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModEntities.registerEntities();
		UseItemCallback.EVENT.register(new QuiverLogicHandler());



	}
}
