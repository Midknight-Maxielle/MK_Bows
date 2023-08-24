package net.mk;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.mk.entities.ModEntities;
import net.mk.events.QuiverLogicHandler;
import net.mk.items.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArchersArsenal implements ModInitializer {

    public static final String MOD_ID = "archers_arsenal";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {

        ModItems.registerModItems();
        ModEntities.registerEntities();

        UseItemCallback.EVENT.register(new QuiverLogicHandler());



    }
}