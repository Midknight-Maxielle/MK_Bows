package net.midknight.bowyery.entity.renderers;

import net.midknight.bowyery.entity.VillagerArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.util.Identifier;

public class VillagerArrowRenderer extends ProjectileEntityRenderer<VillagerArrowEntity> {

    private final ItemRenderer itemRenderer;

    public static final Identifier ARROW_TEXTURE = new Identifier("bowyery:textures/entity/villager_arrow.png");

    public VillagerArrowRenderer(EntityRendererFactory.Context context, ItemRenderer itemRenderer) {

        super(context);
        this.itemRenderer = itemRenderer;
    }

    @Override
    public Identifier getTexture(VillagerArrowEntity entity) { return ARROW_TEXTURE; }
}
