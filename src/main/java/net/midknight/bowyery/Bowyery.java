package net.midknight.bowyery;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.midknight.bowyery.entity.renderers.BarbedArrowRenderer;
import net.midknight.bowyery.entity.renderers.VillagerArrowRenderer;
import net.midknight.bowyery.util.BowyeryModels;
import net.midknight.bowyery.util.EntitySpawnPacket;
import net.midknight.bowyery.util.ModRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Bowyery implements ModInitializer {

	public static final String MOD_ID = "bowyery";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final Identifier PacketID = new Identifier(Bowyery.MOD_ID, "spawn_packet");

	@Override
	public void onInitialize() {

		LOGGER.info("MK Bowyery spooling up!");

		// UseItemCallback.EVENT.register((player, world, hand) -> {});

		ModRegistry.registerLogs();
		BowyeryModels.registerModels();
		EntityRendererRegistry.register(ModRegistry.VILLAGER_ARROW_TYPE, dispatcher -> new VillagerArrowRenderer(dispatcher, MinecraftClient.getInstance().getItemRenderer()));
		EntityRendererRegistry.register(ModRegistry.BARBED_ARROW_TYPE, dispatcher -> new BarbedArrowRenderer(dispatcher, MinecraftClient.getInstance().getItemRenderer()));
		receiveEntityPacket();
	}

	public void receiveEntityPacket() {
		ClientSidePacketRegistry.INSTANCE.register(PacketID, (ctx, byteBuf) -> {
			EntityType<?> et = Registry.ENTITY_TYPE.get(byteBuf.readVarInt());
			UUID uuid = byteBuf.readUuid();
			int entityId = byteBuf.readVarInt();
			Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
			float pitch = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
			float yaw = EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf);
			ctx.getTaskQueue().execute(() -> {
				if (MinecraftClient.getInstance().world == null)
					throw new IllegalStateException("Tried to spawn entity in a null world!");
				Entity e = et.create(MinecraftClient.getInstance().world);
				if (e == null)
					throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(et) + "\"!");
				e.updateTrackedPosition(pos);
				e.setPos(pos.x, pos.y, pos.z);
				e.setPitch(pitch);
				e.setYaw(yaw);
				e.setId(entityId);
				e.setUuid(uuid);
				MinecraftClient.getInstance().world.addEntity(entityId, e);
			});
		});
	}
}
