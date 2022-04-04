package com.midknight.bowyery.events;

import com.google.gson.JsonObject;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ModLootInjector {

    public static class LootInjectionModifier extends LootModifier {

        // Fields

        private final ResourceLocation injectionTable;

        // Constructor Methods

        protected LootInjectionModifier(LootItemCondition[] conditionsIn, ResourceLocation injection) {
            super(conditionsIn);
            this.injectionTable = injection;
        }

        @Override
        protected @NotNull List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
            LootContext.Builder builder = (new LootContext.Builder(context.getLevel()).withRandom(context.getRandom()));
            LootTable lootTable = context.getLevel().getServer().getLootTables().get(injectionTable);
            generatedLoot.addAll(lootTable.getRandomItems(builder.create(LootContextParamSets.EMPTY)));
            return generatedLoot;
        }
    }

    public static class InjectionSerializer extends GlobalLootModifierSerializer<LootInjectionModifier> {

        @Override
        public LootInjectionModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] lootCondition) {
            return new LootInjectionModifier(lootCondition, new ResourceLocation(GsonHelper.getAsString(object, "injection")));
        }

        @Override
        public JsonObject write(LootInjectionModifier instance) {
            return null;
        }
    }
}
