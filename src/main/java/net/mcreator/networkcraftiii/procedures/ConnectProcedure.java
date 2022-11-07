package net.mcreator.networkcraftiii.procedures;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.components.EditBox;

import net.mcreator.networkcraftiii.init.NetworkcraftiiiModBlocks;

import java.util.Random;
import java.util.HashMap;

public class ConnectProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		boolean Connected = false;
		if ((new Object() {
			public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getTileData().getBoolean(tag);
				return false;
			}
		}.getValue(world, new BlockPos(x, y, z), "isOn")) == true) {
			Connected = false;
			sx = -10;
			for (int index0 = 0; index0 < (int) (20); index0++) {
				sy = -10;
				for (int index1 = 0; index1 < (int) (20); index1++) {
					sz = -10;
					for (int index2 = 0; index2 < (int) (20); index2++) {
						if ((world.getBlockState(new BlockPos(x + sx, y + sy, z + sz))).getBlock() == NetworkcraftiiiModBlocks.ROUTER.get()
								&& (new Object() {
									public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
										BlockEntity blockEntity = world.getBlockEntity(pos);
										if (blockEntity != null)
											return blockEntity.getTileData().getBoolean(tag);
										return false;
									}
								}.getValue(world, new BlockPos(x + sx, y + sy, z + sz), "isOn")) == true) {
							if ((guistate.containsKey("text:specifiedRouter") ? ((EditBox) guistate.get("text:specifiedRouter")).getValue() : "")
									.equals("")
									|| (guistate.containsKey("text:specifiedRouter")
											? ((EditBox) guistate.get("text:specifiedRouter")).getValue()
											: "").equals(new Object() {
												public String getValue(LevelAccessor world, BlockPos pos, String tag) {
													BlockEntity blockEntity = world.getBlockEntity(pos);
													if (blockEntity != null)
														return blockEntity.getTileData().getString(tag);
													return "";
												}
											}.getValue(world, new BlockPos(x + sx, y + sy, z + sz), "routerIP"))) {
								if (!world.isClientSide()) {
									BlockPos _bp = new BlockPos(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null)
										_blockEntity.getTileData().putString("subNet", (new Object() {
											public String getValue(LevelAccessor world, BlockPos pos, String tag) {
												BlockEntity blockEntity = world.getBlockEntity(pos);
												if (blockEntity != null)
													return blockEntity.getTileData().getString(tag);
												return "";
											}
										}.getValue(world, new BlockPos(x + sx, y + sy, z + sz), "routerSubNet")));
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
								if (!world.isClientSide()) {
									BlockPos _bp = new BlockPos(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null)
										_blockEntity.getTileData().putString("routerIP", (new Object() {
											public String getValue(LevelAccessor world, BlockPos pos, String tag) {
												BlockEntity blockEntity = world.getBlockEntity(pos);
												if (blockEntity != null)
													return blockEntity.getTileData().getString(tag);
												return "";
											}
										}.getValue(world, new BlockPos(x + sx, y + sy, z + sz), "routerIP")));
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
								if (!world.isClientSide()) {
									BlockPos _bp = new BlockPos(x, y, z);
									BlockEntity _blockEntity = world.getBlockEntity(_bp);
									BlockState _bs = world.getBlockState(_bp);
									if (_blockEntity != null)
										_blockEntity.getTileData().putString("assignedIP", ((new Object() {
											public String getValue(LevelAccessor world, BlockPos pos, String tag) {
												BlockEntity blockEntity = world.getBlockEntity(pos);
												if (blockEntity != null)
													return blockEntity.getTileData().getString(tag);
												return "";
											}
										}.getValue(world, new BlockPos(x + sx, y + sy, z + sz), "routerSubNet")) + ""
												+ Mth.nextInt(new Random(), 1, 255)));
									if (world instanceof Level _level)
										_level.sendBlockUpdated(_bp, _bs, _bs, 3);
								}
								if (entity instanceof Player _player && !_player.level.isClientSide())
									_player.displayClientMessage(new TextComponent(("Connection Established with " + (new Object() {
										public String getValue(LevelAccessor world, BlockPos pos, String tag) {
											BlockEntity blockEntity = world.getBlockEntity(pos);
											if (blockEntity != null)
												return blockEntity.getTileData().getString(tag);
											return "";
										}
									}.getValue(world, new BlockPos(x + sx, y + sy, z + sz), "routerIP")) + ".")), (false));
								Connected = true;
								break;
							}
						}
						sz = sz + 1;
					}
					if (Connected) {
						break;
					}
					sy = sy + 1;
				}
				sx = sx + 1;
				if (Connected) {
					break;
				}
			}
			if (!Connected) {
				if (entity instanceof Player _player && !_player.level.isClientSide())
					_player.displayClientMessage(new TextComponent(
							"Connection Failed. Ensure Laptop and Router are on. Laptop must be in 10x10x10 range of Router. If specified, ensure RouterIP matches a Router in range of Laptop. "),
							(false));
			}
		} else {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Connection Falied. Ensure Lapton is on."), (false));
		}
		if (entity instanceof Player _player)
			_player.closeContainer();
	}
}
