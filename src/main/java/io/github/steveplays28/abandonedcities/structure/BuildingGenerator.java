package io.github.steveplays28.abandonedcities.structure;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.StairShape;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.structure.StructureContext;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePieceType;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;

public class BuildingGenerator extends StructurePiece {
	public BuildingGenerator(StructurePieceType type, int chainLength, BlockBox boundingBox) {
		super(type, chainLength, boundingBox);
	}

	@Override
	protected void writeNbt(StructureContext context, NbtCompound nbt) {}

	@Override
	public void generate(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox chunkBox, ChunkPos chunkPos, BlockPos pivot) {
//		for (int i = 0; i < 100; i++) {
//			var zombie = EntityType.ZOMBIE.create(world.toServerWorld());
//			if (zombie == null) return;
//			zombie.setPosition(pivot.toCenterPos());
//			world.spawnEntity(zombie);
//
//			AbandonedCities.LOGGER.info("(building_structure) spawned zombie at {}", pivot.toCenterPos());
//		}

		this.fillWithOutline(world, chunkBox, 1, 1, 1, 5, 1, 7, Blocks.SPRUCE_PLANKS.getDefaultState(),
				Blocks.SPRUCE_PLANKS.getDefaultState(), false
		);
		this.fillWithOutline(world, chunkBox, 1, 4, 2, 5, 4, 7, Blocks.SPRUCE_PLANKS.getDefaultState(),
				Blocks.SPRUCE_PLANKS.getDefaultState(), false
		);
		this.fillWithOutline(world, chunkBox, 2, 1, 0, 4, 1, 0, Blocks.SPRUCE_PLANKS.getDefaultState(),
				Blocks.SPRUCE_PLANKS.getDefaultState(), false
		);
		this.fillWithOutline(world, chunkBox, 2, 2, 2, 3, 3, 2, Blocks.SPRUCE_PLANKS.getDefaultState(),
				Blocks.SPRUCE_PLANKS.getDefaultState(), false
		);
		this.fillWithOutline(world, chunkBox, 1, 2, 3, 1, 3, 6, Blocks.SPRUCE_PLANKS.getDefaultState(),
				Blocks.SPRUCE_PLANKS.getDefaultState(), false
		);
		this.fillWithOutline(world, chunkBox, 5, 2, 3, 5, 3, 6, Blocks.SPRUCE_PLANKS.getDefaultState(),
				Blocks.SPRUCE_PLANKS.getDefaultState(), false
		);
		this.fillWithOutline(world, chunkBox, 2, 2, 7, 4, 3, 7, Blocks.SPRUCE_PLANKS.getDefaultState(),
				Blocks.SPRUCE_PLANKS.getDefaultState(), false
		);
		this.fillWithOutline(world, chunkBox, 1, 0, 2, 1, 3, 2, Blocks.OAK_LOG.getDefaultState(),
				Blocks.OAK_LOG.getDefaultState(), false
		);
		this.fillWithOutline(world, chunkBox, 5, 0, 2, 5, 3, 2, Blocks.OAK_LOG.getDefaultState(),
				Blocks.OAK_LOG.getDefaultState(), false
		);
		this.fillWithOutline(world, chunkBox, 1, 0, 7, 1, 3, 7, Blocks.OAK_LOG.getDefaultState(),
				Blocks.OAK_LOG.getDefaultState(), false
		);
		this.fillWithOutline(world, chunkBox, 5, 0, 7, 5, 3, 7, Blocks.OAK_LOG.getDefaultState(),
				Blocks.OAK_LOG.getDefaultState(), false
		);
		this.addBlock(world, Blocks.OAK_FENCE.getDefaultState(), 2, 3, 2, chunkBox);
		this.addBlock(world, Blocks.OAK_FENCE.getDefaultState(), 3, 3, 7, chunkBox);
		this.addBlock(world, Blocks.AIR.getDefaultState(), 1, 3, 4, chunkBox);
		this.addBlock(world, Blocks.AIR.getDefaultState(), 5, 3, 4, chunkBox);
		this.addBlock(world, Blocks.AIR.getDefaultState(), 5, 3, 5, chunkBox);
		this.addBlock(world, Blocks.POTTED_RED_MUSHROOM.getDefaultState(), 1, 3, 5, chunkBox);
		this.addBlock(world, Blocks.CRAFTING_TABLE.getDefaultState(), 3, 2, 6, chunkBox);
		this.addBlock(world, Blocks.CAULDRON.getDefaultState(), 4, 2, 6, chunkBox);
		this.addBlock(world, Blocks.OAK_FENCE.getDefaultState(), 1, 2, 1, chunkBox);
		this.addBlock(world, Blocks.OAK_FENCE.getDefaultState(), 5, 2, 1, chunkBox);
		BlockState blockState = (BlockState) Blocks.SPRUCE_STAIRS.getDefaultState().with(
				StairsBlock.FACING, Direction.NORTH);
		BlockState blockState2 = (BlockState) Blocks.SPRUCE_STAIRS.getDefaultState().with(
				StairsBlock.FACING, Direction.EAST);
		BlockState blockState3 = (BlockState) Blocks.SPRUCE_STAIRS.getDefaultState().with(
				StairsBlock.FACING, Direction.WEST);
		BlockState blockState4 = (BlockState) Blocks.SPRUCE_STAIRS.getDefaultState().with(
				StairsBlock.FACING, Direction.SOUTH);
		this.fillWithOutline(world, chunkBox, 0, 4, 1, 6, 4, 1, blockState, blockState, false);
		this.fillWithOutline(world, chunkBox, 0, 4, 2, 0, 4, 7, blockState2, blockState2, false);
		this.fillWithOutline(world, chunkBox, 6, 4, 2, 6, 4, 7, blockState3, blockState3, false);
		this.fillWithOutline(world, chunkBox, 0, 4, 8, 6, 4, 8, blockState4, blockState4, false);
		this.addBlock(
				world, (BlockState) blockState.with(StairsBlock.SHAPE, StairShape.OUTER_RIGHT), 0, 4, 1, chunkBox);
		this.addBlock(world, (BlockState) blockState.with(StairsBlock.SHAPE, StairShape.OUTER_LEFT), 6, 4, 1, chunkBox);
		this.addBlock(
				world, (BlockState) blockState4.with(StairsBlock.SHAPE, StairShape.OUTER_LEFT), 0, 4, 8, chunkBox);
		this.addBlock(
				world, (BlockState) blockState4.with(StairsBlock.SHAPE, StairShape.OUTER_RIGHT), 6, 4, 8, chunkBox);

		for (int i = 2; i <= 7; i += 5) {
			for (int j = 1; j <= 5; j += 4) {
				this.fillDownwards(world, Blocks.OAK_LOG.getDefaultState(), j, -1, i, chunkBox);
			}
		}
	}
}
