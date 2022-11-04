package net.mcreator.networkcraftiii.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;

import net.mcreator.networkcraftiii.network.NetworkcraftiiiModVariables;
import net.mcreator.networkcraftiii.init.NetworkcraftiiiModBlocks;

public class RouterBlockDestroyedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (NetworkcraftiiiModVariables.WorldVariables.get(world).NumRouters > 0) {
			NetworkcraftiiiModVariables.WorldVariables.get(world).NumRouters = NetworkcraftiiiModVariables.WorldVariables.get(world).NumRouters - 1;
			NetworkcraftiiiModVariables.WorldVariables.get(world).syncData(world);
			sx = -10;
			for (int index0 = 0; index0 < (int) (20); index0++) {
				sy = -10;
				for (int index1 = 0; index1 < (int) (20); index1++) {
					sz = -10;
					for (int index2 = 0; index2 < (int) (20); index2++) {
						if ((world.getBlockState(new BlockPos(x + sx, y + sy, z + sz))).getBlock() == NetworkcraftiiiModBlocks.LAPTOP.get()
								&& (new Object() {
									public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
										BlockEntity blockEntity = world.getBlockEntity(pos);
										if (blockEntity != null)
											return blockEntity.getTileData().getBoolean(tag);
										return false;
									}
								}.getValue(world, new BlockPos(x + sx, y + sy, z + sz), "isOn")) == true
								&& ("deleted routerIP from destrroyed block").equals(new Object() {
									public String getValue(LevelAccessor world, BlockPos pos, String tag) {
										BlockEntity blockEntity = world.getBlockEntity(pos);
										if (blockEntity != null)
											return blockEntity.getTileData().getString(tag);
										return "";
									}
								}.getValue(world, new BlockPos(x + sx, y + sy, z + sz), "subNet"))) {
							if (!world.isClientSide()) {
								BlockPos _bp = new BlockPos(x + sx, y + sy, z + sz);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getTileData().putString("subNet", "null");
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							if (!world.isClientSide()) {
								BlockPos _bp = new BlockPos(x + sx, y + sy, z + sz);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getTileData().putString("routerIP", "null");
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
							if (!world.isClientSide()) {
								BlockPos _bp = new BlockPos(x + sx, y + sy, z + sz);
								BlockEntity _blockEntity = world.getBlockEntity(_bp);
								BlockState _bs = world.getBlockState(_bp);
								if (_blockEntity != null)
									_blockEntity.getTileData().putString("assignedIP", "null");
								if (world instanceof Level _level)
									_level.sendBlockUpdated(_bp, _bs, _bs, 3);
							}
						}
						sz = sz + 1;
					}
					sy = sy + 1;
				}
				sx = sx + 1;
			}
		}
	}
}
