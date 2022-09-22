package net.mcreator.networkcraftiii.procedures;

import net.minecraft.world.level.LevelAccessor;

import net.mcreator.networkcraftiii.network.NetworkcraftiiiModVariables;

public class LaptopBlockDestroyedProcedure {
	public static void execute(LevelAccessor world) {
		if (NetworkcraftiiiModVariables.WorldVariables.get(world).NumLaptops > 0) {
			NetworkcraftiiiModVariables.WorldVariables.get(world).NumLaptops = NetworkcraftiiiModVariables.WorldVariables.get(world).NumLaptops - 1;
			NetworkcraftiiiModVariables.WorldVariables.get(world).syncData(world);
		}
	}
}
