
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.networkcraftiii.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import net.mcreator.networkcraftiii.NetworkcraftiiiMod;

public class NetworkcraftiiiModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, NetworkcraftiiiMod.MODID);
	public static final RegistryObject<Item> MODEM = block(NetworkcraftiiiModBlocks.MODEM, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> ROUTER = block(NetworkcraftiiiModBlocks.ROUTER, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> LAPTOP = block(NetworkcraftiiiModBlocks.LAPTOP, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> CABLE = block(NetworkcraftiiiModBlocks.CABLE, CreativeModeTab.TAB_BUILDING_BLOCKS);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
