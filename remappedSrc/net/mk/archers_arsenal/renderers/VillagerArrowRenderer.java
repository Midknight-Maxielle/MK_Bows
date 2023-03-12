package net.mk.archers_arsenal.renderers;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import net.mk.archers_arsenal.entities.VillagerArrowEntity;

public class VillagerArrowRenderer extends ProjectileEntityRenderer<VillagerArrowEntity> {

    public VillagerArrowRenderer(EntityRendererFactory.Context context) {

        super(context);
    }
    @Override
    public Identifier getTexture(VillagerArrowEntity entity) {

        return new Identifier("archers_arsenal","textures/entity/villager_arrow.png");
    }
}