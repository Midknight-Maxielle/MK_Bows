package net.mk.archers_arsenal;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.mk.archers_arsenal.entities.ModEntities;
import net.mk.archers_arsenal.events.QuiverLogicHandler;
import net.mk.archers_arsenal.items.ModItems;
import net.mk.archers_arsenal.items.ModItemGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArchersArsenal implements ModInitializer {

	public static final String MOD_ID = "archers_arsenal";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	// public static final Identifier PacketID = new Identifier(ArchersArsenal.MOD_ID, "spawn_packet");

	@Override
	public void onInitialize() {

		ModItems.registerModItems();
		ModEntities.registerEntities();
		ModItemGroup.registerItemGroups();

		UseItemCallback.EVENT.register(new QuiverLogicHandler());



	}
}
