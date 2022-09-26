
package net.mcreator.networkcraftiii.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.networkcraftiii.world.inventory.RouterGUIMenu;
import net.mcreator.networkcraftiii.procedures.TurnRouterOnProcedure;
import net.mcreator.networkcraftiii.procedures.TurnRouterOffProcedure;
import net.mcreator.networkcraftiii.procedures.RemoveInternetProcedure;
import net.mcreator.networkcraftiii.procedures.ProvideInternetProcedure;
import net.mcreator.networkcraftiii.NetworkcraftiiiMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RouterGUIButtonMessage {
	private final int buttonID, x, y, z;

	public RouterGUIButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public RouterGUIButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(RouterGUIButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(RouterGUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level;
		HashMap guistate = RouterGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			TurnRouterOnProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			TurnRouterOffProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			ProvideInternetProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 3) {

			RemoveInternetProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		NetworkcraftiiiMod.addNetworkMessage(RouterGUIButtonMessage.class, RouterGUIButtonMessage::buffer, RouterGUIButtonMessage::new,
				RouterGUIButtonMessage::handler);
	}
}
