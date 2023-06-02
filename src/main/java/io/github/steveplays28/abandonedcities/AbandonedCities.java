package io.github.steveplays28.abandonedcities;

import io.github.steveplays28.abandonedcities.structure.ModStructure;
import net.fabricmc.api.ModInitializer;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.structure.StructureType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AbandonedCities implements ModInitializer {
	public static final String MOD_ID = "abandonedcities";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final StructureProcessorType<BuildingProcessor> BUILDING_PROCESSOR = StructureProcessorType.register(
			"abandonedcities:building_processor", BuildingProcessor.CODEC);

	public static final Identifier EXAMPLE_FEATURE_ID = new Identifier(MOD_ID, "zombie_feature");
	public static ZombieFeature EXAMPLE_FEATURE = new ZombieFeature(ZombieFeature.ZombieFeatureConfig.CODEC);

	public static final StructureType<ModStructure> GENERIC = () -> ModStructure.CODEC;
	public static final StructureType<BuildingStructure> BUILDING_STRUCTURE = () -> BuildingStructure.CODEC;

	public static Identifier id(String name) {
		return new Identifier(MOD_ID, name);
	}

	@Override
	public void onInitialize() {
		Registry.register(Registries.FEATURE, EXAMPLE_FEATURE_ID, EXAMPLE_FEATURE);
		Registry.register(Registries.STRUCTURE_TYPE, AbandonedCities.id("building_structure"), BUILDING_STRUCTURE);
	}
}
