
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.networkcraftiii.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import net.mcreator.networkcraftiii.block.RouterBlock;
import net.mcreator.networkcraftiii.block.ModemBlock;
import net.mcreator.networkcraftiii.block.LaptopBlock;
import net.mcreator.networkcraftiii.block.CableBlock;
import net.mcreator.networkcraftiii.NetworkcraftiiiMod;

public class NetworkcraftiiiModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, NetworkcraftiiiMod.MODID);
	public static final RegistryObject<Block> MODEM = REGISTRY.register("modem", () -> new ModemBlock());
	public static final RegistryObject<Block> ROUTER = REGISTRY.register("router", () -> new RouterBlock());
	public static final RegistryObject<Block> LAPTOP = REGISTRY.register("laptop", () -> new LaptopBlock());
	public static final RegistryObject<Block> CABLE = REGISTRY.register("cable", () -> new CableBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			ModemBlock.registerRenderLayer();
			RouterBlock.registerRenderLayer();
			LaptopBlock.registerRenderLayer();
			CableBlock.registerRenderLayer();
		}
	}
}
