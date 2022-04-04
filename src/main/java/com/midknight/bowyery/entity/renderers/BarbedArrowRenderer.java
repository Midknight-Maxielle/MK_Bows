package com.midknight.bowyery.entity.renderers;

import com.midknight.bowyery.entity.BarbedArrowEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class BarbedArrowRenderer extends ArrowRenderer<BarbedArrowEntity> {

    public static final ResourceLocation ARROW_TEXTURE = new ResourceLocation("bowyery:textures/entity/barbed_arrow.png");

    public BarbedArrowRenderer(EntityRendererManager manager) { super(manager); }

    @Override
    public ResourceLocation getEntityTexture(BarbedArrowEntity entity) {
        return ARROW_TEXTURE;
    }
}
