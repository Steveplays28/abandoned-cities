package io.github.steveplays28.abandonedcities;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.steveplays28.abandonedcities.structure.BuildingGenerator;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.structure.StructurePiecesCollector;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.HeightContext;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.structure.Structure;
import net.minecraft.world.gen.structure.StructureType;

import java.util.Optional;

public class BuildingStructure extends Structure {
	public static final Codec<BuildingStructure> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(Config.CODEC.forGetter(feature -> feature.config),
					StructurePool.REGISTRY_CODEC.fieldOf("start_pool").forGetter((structure) -> structure.startPool),
					Identifier.CODEC.optionalFieldOf("start_jigsaw_name").forGetter(
							(structure) -> structure.startJigsawName),
					Codec.intRange(0, 7).fieldOf("size").forGetter((structure) -> structure.size),
					HeightProvider.CODEC.fieldOf("start_height").forGetter((structure) -> structure.startHeight),
					Codec.BOOL.fieldOf("use_expansion_hack").forGetter((structure) -> structure.useExpansionHack),
					Heightmap.Type.CODEC.optionalFieldOf("project_start_to_heightmap").forGetter(
							(structure) -> structure.projectStartToHeightmap),
					Codec.intRange(1, 128).fieldOf("max_distance_from_center").forGetter(
							(structure) -> structure.maxDistanceFromCenter),
					Codec.intRange(-1, 100).fieldOf("heightRange").orElse(-1).forGetter(
							structure -> structure.heightRange)
			).apply(instance, BuildingStructure::new));

	public final RegistryEntry<StructurePool> startPool;
	public final int heightRange;
	private final Optional<Identifier> startJigsawName;
	private final int size;
	private final HeightProvider startHeight;
	private final boolean useExpansionHack;
	private final Optional<Heightmap.Type> projectStartToHeightmap;
	private final int maxDistanceFromCenter;

	public BuildingStructure(Structure.Config config, RegistryEntry<StructurePool> startPool, Optional<Identifier> startJigsawName, int size, HeightProvider startHeight, boolean useExpansionHack, Optional<Heightmap.Type> projectStartToHeightmap, int maxDistanceFromCenter, int heightRange) {
		super(config);
		this.startPool = startPool;
		this.startJigsawName = startJigsawName;
		this.size = size;
		this.startHeight = startHeight;
		this.useExpansionHack = useExpansionHack;
		this.projectStartToHeightmap = projectStartToHeightmap;
		this.maxDistanceFromCenter = maxDistanceFromCenter;
		this.heightRange = heightRange;
	}

	@Override
	public Optional<Structure.StructurePosition> getStructurePosition(Structure.Context context) {
		if (canGenerate(context.chunkGenerator(), context.chunkPos(), context.world(), context.noiseConfig())) {
			ChunkPos chunkPos = context.chunkPos();
			int y = this.startHeight.get(context.random(),
					new HeightContext(context.chunkGenerator(), context.world())
			);
			BlockPos blockPos = new BlockPos(chunkPos.getStartX(), y, chunkPos.getStartZ());
			return getStructurePosition(
					context, Heightmap.Type.WORLD_SURFACE_WG, (collector) -> addPieces(collector, context));
//			return StructurePoolBasedGenerator.generate(context, this.startPool, this.startJigsawName, this.size,
//					blockPos, this.useExpansionHack, this.projectStartToHeightmap, this.maxDistanceFromCenter
//			);
		} else {
			return Optional.empty();
		}
	}

	public boolean canGenerate(ChunkGenerator chunkGenerator, ChunkPos pos, HeightLimitView world, NoiseConfig noiseConfig) {
		int heightRange = this.heightRange;
		if (heightRange != -1) {
			int maxTerrainHeight = Integer.MIN_VALUE;
			int minTerrainHeight = Integer.MAX_VALUE;

			for (int curChunkX = pos.x - 2; curChunkX <= pos.x + 2; curChunkX++) {
				for (int curChunkZ = pos.z - 2; curChunkZ <= pos.z + 2; curChunkZ++) {
					int height = chunkGenerator.getHeight((curChunkX << 4) + 7, (curChunkZ << 4) + 7,
							Heightmap.Type.WORLD_SURFACE_WG, world, noiseConfig
					);
					maxTerrainHeight = Math.max(maxTerrainHeight, height);
					minTerrainHeight = Math.min(minTerrainHeight, height);
				}
			}

			return maxTerrainHeight - minTerrainHeight <= heightRange;
		}
		return true;
	}

	@Override
	public StructureType<?> getType() {
		return AbandonedCities.BUILDING_STRUCTURE;
	}

//	@Override
//	public void postPlace(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox box, ChunkPos chunkPos, StructurePiecesList pieces) {
//		for (int i = 0; i < 100; i++) {
//			int y = this.startHeight.get(random, new HeightContext(chunkGenerator, world));
//			BlockPos blockPos = new BlockPos(chunkPos.getStartX(), y, chunkPos.getStartZ());
//
//			var zombie = EntityType.ZOMBIE.create(world.toServerWorld());
//			if (zombie == null) return;
//			zombie.setPosition(this.blockPos.toCenterPos());
//			world.spawnEntity(zombie);
//
//			AbandonedCities.LOGGER.info("(building_structure) spawned zombie at {}", this.blockPos.toCenterPos());
//		}
//
//		super.postPlace(world, structureAccessor, chunkGenerator, random, box, chunkPos, pieces);
//	}

	private static void addPieces(StructurePiecesCollector collector, Structure.Context context) {
		collector.addPiece(
				new BuildingGenerator(StructurePieceType.JIGSAW, 1, BlockBox.create(Vec3i.ZERO, new Vec3i(1, 1, 1))));
	}
}
