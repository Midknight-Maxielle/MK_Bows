package net.mk.renderers;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.util.Identifier;
import net.mk.entities.BarbedArrowEntity;

@Environment(EnvType.CLIENT)
public class BarbedArrowRenderer extends ProjectileEntityRenderer<BarbedArrowEntity> {

    public BarbedArrowRenderer(EntityRendererFactory.Context context) {

        super(context);
    }
    @Override
    public Identifier getTexture(BarbedArrowEntity entity) {

        return new Identifier("archers_arsenal","textures/entity/barbed_arrow.png");
    }
}