package net.mcreator.networkcraftiii.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;

import net.mcreator.networkcraftiii.network.NetworkcraftiiiModVariables;

import java.util.Random;

public class LaptopBlockAddedProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		NetworkcraftiiiModVariables.WorldVariables.get(world).NumLaptops = NetworkcraftiiiModVariables.WorldVariables.get(world).NumLaptops + 1;
		NetworkcraftiiiModVariables.WorldVariables.get(world).syncData(world);
		if (!world.isClientSide()) {
			BlockPos _bp = new BlockPos(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null)
				_blockEntity.getTileData().putString("macAddress",
						((Mth.nextInt(new Random(), 0, 9) + "" + Mth.nextInt(new Random(), 0, 9)) + "-"
								+ (Mth.nextInt(new Random(), 0, 9) + "" + Mth.nextInt(new Random(), 0, 9)) + "-"
								+ (Mth.nextInt(new Random(), 0, 9) + "" + Mth.nextInt(new Random(), 0, 9)) + "-"
								+ (Mth.nextInt(new Random(), 0, 9) + "" + Mth.nextInt(new Random(), 0, 9)) + "-"
								+ (Mth.nextInt(new Random(), 0, 9) + "" + Mth.nextInt(new Random(), 0, 9)) + "-"
								+ (Mth.nextInt(new Random(), 0, 9) + "" + Mth.nextInt(new Random(), 0, 9))));
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (!world.isClientSide()) {
			BlockPos _bp = new BlockPos(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null)
				_blockEntity.getTileData().putString("subNet", "");
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (!world.isClientSide()) {
			BlockPos _bp = new BlockPos(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null)
				_blockEntity.getTileData().putString("routerIP", "");
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (!world.isClientSide()) {
			BlockPos _bp = new BlockPos(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null)
				_blockEntity.getTileData().putString("assignedIP", "");
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (!world.isClientSide()) {
			BlockPos _bp = new BlockPos(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null)
				_blockEntity.getTileData().putBoolean("isOn", (false));
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
		if (!world.isClientSide()) {
			BlockPos _bp = new BlockPos(x, y, z);
			BlockEntity _blockEntity = world.getBlockEntity(_bp);
			BlockState _bs = world.getBlockState(_bp);
			if (_blockEntity != null)
				_blockEntity.getTileData().putString("status", "Off");
			if (world instanceof Level _level)
				_level.sendBlockUpdated(_bp, _bs, _bs, 3);
		}
	}
}
