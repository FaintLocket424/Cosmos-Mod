package space.faintlocket.cosmosmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

public class CosmosMod implements ModInitializer {
    private static final String MOD_ID = "cosmosmod";
    private static final Block SILICON_ORE_BLOCK = new Block(FabricBlockSettings.create().strength(3.0f));
    private static final Block DEEPSLATE_SILICON_ORE_BLOCK = new Block(FabricBlockSettings.create().strength(4.5f));
    private static final ItemGroup COSMOS_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(SILICON_ORE_BLOCK))
            .displayName(Text.translatable(String.format("itemGroup.%s.cosmos_item_group", MOD_ID)))
            .entries((context, entries) -> {
                entries.add(SILICON_ORE_BLOCK);
                entries.add(DEEPSLATE_SILICON_ORE_BLOCK);
            })
            .build();
    private static final RegistryKey<PlacedFeature> SILICON_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(MOD_ID, "silicon_ore"));

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "cosmos_item_group"), COSMOS_ITEM_GROUP);

        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "silicon_ore"), SILICON_ORE_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "silicon_ore"), new BlockItem(SILICON_ORE_BLOCK, new FabricItemSettings()));
        Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "deepslate_silicon_ore"), DEEPSLATE_SILICON_ORE_BLOCK);
        Registry.register(Registries.ITEM, new Identifier(MOD_ID, "deepslate_silicon_ore"), new BlockItem(DEEPSLATE_SILICON_ORE_BLOCK, new FabricItemSettings()));

        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, SILICON_ORE_PLACED_KEY);
    }
}
