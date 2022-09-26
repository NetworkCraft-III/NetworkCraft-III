package net.mcreator.networkcraftiii.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.networkcraftiii.network.NetworkcraftiiiModVariables;

public class RouterBlockDestroyedProcedure {
	public static void execute(LevelAccessor world) {
		if (NetworkcraftiiiModVariables.WorldVariables.get(world).NumRouters > 0) {
			NetworkcraftiiiModVariables.WorldVariables.get(world).NumRouters = NetworkcraftiiiModVariables.WorldVariables.get(world).NumRouters - 1;
			NetworkcraftiiiModVariables.WorldVariables.get(world).syncData(world);
		}
	}
}
