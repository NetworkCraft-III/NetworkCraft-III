
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.networkcraftiii.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

import net.mcreator.networkcraftiii.block.entity.RouterBlockEntity;
import net.mcreator.networkcraftiii.block.entity.LaptopBlockEntity;
import net.mcreator.networkcraftiii.NetworkcraftiiiMod;

public class NetworkcraftiiiModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES,
			NetworkcraftiiiMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> ROUTER = register("router", NetworkcraftiiiModBlocks.ROUTER, RouterBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> LAPTOP = register("laptop", NetworkcraftiiiModBlocks.LAPTOP, LaptopBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block,
			BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
