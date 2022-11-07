
package net.mcreator.networkcraftiii.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import net.mcreator.networkcraftiii.world.inventory.LaptopGUIMenu;
import net.mcreator.networkcraftiii.procedures.TurnLaptopOnProcedure;
import net.mcreator.networkcraftiii.procedures.TurnLaptopOffProcedure;
import net.mcreator.networkcraftiii.procedures.DisconnectProcedure;
import net.mcreator.networkcraftiii.procedures.ConnectProcedure;
import net.mcreator.networkcraftiii.NetworkcraftiiiMod;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class LaptopGUIButtonMessage {
	private final int buttonID, x, y, z;

	public LaptopGUIButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public LaptopGUIButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(LaptopGUIButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(LaptopGUIButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
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
		HashMap guistate = LaptopGUIMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			TurnLaptopOnProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 1) {

			TurnLaptopOffProcedure.execute(world, x, y, z, entity);
		}
		if (buttonID == 2) {

			ConnectProcedure.execute(world, x, y, z, entity, guistate);
		}
		if (buttonID == 3) {

			DisconnectProcedure.execute(world, x, y, z, entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		NetworkcraftiiiMod.addNetworkMessage(LaptopGUIButtonMessage.class, LaptopGUIButtonMessage::buffer, LaptopGUIButtonMessage::new,
				LaptopGUIButtonMessage::handler);
	}
}
