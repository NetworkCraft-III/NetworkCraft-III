
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.netcraft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import net.mcreator.netcraft.NetcraftMod;

public class NetcraftModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, NetcraftMod.MODID);
	public static final RegistryObject<Item> ROUTER = block(NetcraftModBlocks.ROUTER, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> NETSWITCH = block(NetcraftModBlocks.NETSWITCH, CreativeModeTab.TAB_BUILDING_BLOCKS);
	public static final RegistryObject<Item> MODEM = block(NetcraftModBlocks.MODEM, CreativeModeTab.TAB_BUILDING_BLOCKS);

	private static RegistryObject<Item> block(RegistryObject<Block> block, CreativeModeTab tab) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
	}
}
