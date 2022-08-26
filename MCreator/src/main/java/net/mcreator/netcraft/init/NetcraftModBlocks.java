
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.mcreator.netcraft.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.block.Block;

import net.mcreator.netcraft.block.RouterBlock;
import net.mcreator.netcraft.block.NetswitchBlock;
import net.mcreator.netcraft.block.ModemBlock;
import net.mcreator.netcraft.NetcraftMod;

public class NetcraftModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, NetcraftMod.MODID);
	public static final RegistryObject<Block> ROUTER = REGISTRY.register("router", () -> new RouterBlock());
	public static final RegistryObject<Block> NETSWITCH = REGISTRY.register("netswitch", () -> new NetswitchBlock());
	public static final RegistryObject<Block> MODEM = REGISTRY.register("modem", () -> new ModemBlock());

	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
	public static class ClientSideHandler {
		@SubscribeEvent
		public static void clientSetup(FMLClientSetupEvent event) {
			RouterBlock.registerRenderLayer();
			NetswitchBlock.registerRenderLayer();
			ModemBlock.registerRenderLayer();
		}
	}
}
