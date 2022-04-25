package com.midknight.archarsenal.util;

import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FletcherAdditionModifier extends LootModifier {

    private final Item addition;

    protected FletcherAdditionModifier(LootItemCondition[] conditions, Item addition) {
        super(conditions);
        this.addition = addition;
    }
    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        if(context.getRandom().nextFloat() > 0.3F) {
            generatedLoot.add(new ItemStack(addition, 4));
        }
        return generatedLoot;
    }

    public static class Serializer extends GlobalLootModifierSerializer<FletcherAdditionModifier> {

        @Override
        public FletcherAdditionModifier read(ResourceLocation name, JsonObject object, LootItemCondition[] conditions) {
            Item addition = ForgeRegistries.ITEMS.getValue(
                    new ResourceLocation(GsonHelper.getAsString(object, "addition")));
            return new FletcherAdditionModifier(conditions, addition);
        }

        @Override
        public JsonObject write(FletcherAdditionModifier instance) {
            JsonObject json = makeConditions(instance.conditions);
            json.addProperty("addition", ForgeRegistries.ITEMS.getKey(instance.addition).toString());
            return json;
        }
    }
}
