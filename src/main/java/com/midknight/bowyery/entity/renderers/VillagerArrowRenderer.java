package com.midknight.bowyery.entity.renderers;

import com.midknight.bowyery.entity.VillagerArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class VillagerArrowRenderer extends ArrowRenderer<VillagerArrowEntity> {

    public static final ResourceLocation ARROW_TEXTURE = new ResourceLocation("bowyery:textures/entity/villager_arrow.png");

    public VillagerArrowRenderer(EntityRendererManager manager) { super(manager); }

    @Override
    public ResourceLocation getEntityTexture(VillagerArrowEntity entity) {
        return ARROW_TEXTURE;
    }
}
