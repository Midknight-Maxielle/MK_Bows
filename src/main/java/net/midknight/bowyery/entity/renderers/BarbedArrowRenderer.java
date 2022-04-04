package net.midknight.bowyery.entity.renderers;

import net.midknight.bowyery.entity.BarbedArrowEntity;
import net.midknight.bowyery.entity.VillagerArrowEntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.util.Identifier;

public class BarbedArrowRenderer extends ProjectileEntityRenderer<BarbedArrowEntity> {

    private final ItemRenderer itemRenderer;

    public static final Identifier ARROW_TEXTURE = new Identifier("bowyery:textures/entity/barbed_arrow.png");

    public BarbedArrowRenderer(EntityRendererFactory.Context context, ItemRenderer itemRenderer) {

        super(context);
        this.itemRenderer = itemRenderer;
    }

    @Override
    public Identifier getTexture(BarbedArrowEntity entity) { return ARROW_TEXTURE; }

}
