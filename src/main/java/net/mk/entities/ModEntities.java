package net.mk.entities;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.mk.ArchersArsenal;

public class ModEntities {

    public static final EntityType<BarbedArrowEntity> BARBED_ARROW_TYPE;
    public static final EntityType<VillagerArrowEntity> VILLAGER_ARROW_TYPE;

    public static void registerEntities() {

        Registry.register(Registry.ENTITY_TYPE, new Identifier(ArchersArsenal.MOD_ID, "barbed_arrow"), BARBED_ARROW_TYPE);
        Registry.register(Registry.ENTITY_TYPE, new Identifier(ArchersArsenal.MOD_ID, "villager_arrow"), VILLAGER_ARROW_TYPE);

    }

    static {

        BARBED_ARROW_TYPE = FabricEntityTypeBuilder.<BarbedArrowEntity>create(SpawnGroup.MISC, BarbedArrowEntity::new)
                .trackRangeBlocks(4)
                .trackedUpdateRate(10)
                .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                .build();

        VILLAGER_ARROW_TYPE = FabricEntityTypeBuilder.<VillagerArrowEntity>create(SpawnGroup.MISC, VillagerArrowEntity::new)
                .trackRangeBlocks(4)
                .trackedUpdateRate(10)
                .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                .build();

    }
}