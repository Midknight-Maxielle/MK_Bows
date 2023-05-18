package com.mk.archarsenal.entities.renderers;

import com.mk.archarsenal.entities.VillagerArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class VillagerArrowRenderer extends ArrowRenderer<VillagerArrowEntity> {

    public static final ResourceLocation ARROW_TEXTURE = new ResourceLocation("archarsenal:textures/entity/villager_arrow.png");

    public VillagerArrowRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override @Nonnull @ParametersAreNonnullByDefault
    public ResourceLocation getTextureLocation(VillagerArrowEntity entity) {
        return ARROW_TEXTURE;
    }
}
