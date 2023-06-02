package io.github.steveplays28.abandonedcities;

import com.mojang.serialization.Codec;
import net.minecraft.entity.EntityType;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.structure.StructureTemplate;
import net.minecraft.structure.processor.StructureProcessor;
import net.minecraft.structure.processor.StructureProcessorType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class BuildingProcessor extends StructureProcessor {
	public static Codec<BuildingProcessor> CODEC = Codec.unit(BuildingProcessor::new);

	public BuildingProcessor() {
	}

	@Nullable
	@Override
	public StructureTemplate.StructureBlockInfo process(WorldView world, BlockPos pos, BlockPos pivot, StructureTemplate.StructureBlockInfo originalBlockInfo, StructureTemplate.StructureBlockInfo currentBlockInfo, StructurePlacementData data) {
		if (world.isClient()) return currentBlockInfo;

		for (int i = 0; i < 10; i++) {
			var zombie = EntityType.ZOMBIE.create((World) world);
			if (zombie == null) return currentBlockInfo;
			zombie.setPosition(pos.toCenterPos());
			((World) world).spawnEntity(zombie);

			AbandonedCities.LOGGER.info("spawned zombie at {}", pos);
		}

		return currentBlockInfo;
	}

	@Override
	protected StructureProcessorType<?> getType() {
		return AbandonedCities.BUILDING_PROCESSOR;
	}
}
