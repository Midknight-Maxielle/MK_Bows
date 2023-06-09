package net.mk.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import net.mk.entities.VillagerArrowEntity;

public final class VillagerArrowRenderer extends ProjectileEntityRenderer<VillagerArrowEntity> {

    public VillagerArrowRenderer(EntityRendererFactory.Context context) {

        super(context);
    }
    @Override
    public Identifier getTexture(VillagerArrowEntity entity) {

        return new Identifier("archers_arsenal","textures/entity/villager_arrow.png");
    }
}