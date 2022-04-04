package com.midknight.bowyery.entity.renderers;

import com.midknight.bowyery.entity.VillagerArrow;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@OnlyIn(Dist.CLIENT)
public class VillagerArrowRenderer extends ArrowRenderer<VillagerArrow> {

    public static final ResourceLocation ARROW_TEXTURE = new ResourceLocation("bowyery:textures/entity/villager_arrow.png");

    public VillagerArrowRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override @Nonnull @ParametersAreNonnullByDefault
    public ResourceLocation getTextureLocation(VillagerArrow entity) {
        return ARROW_TEXTURE;
    }
}
