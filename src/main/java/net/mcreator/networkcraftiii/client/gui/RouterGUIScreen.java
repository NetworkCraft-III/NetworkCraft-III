
package net.mcreator.networkcraftiii.client.gui;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.core.BlockPos;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import net.mcreator.networkcraftiii.world.inventory.RouterGUIMenu;
import net.mcreator.networkcraftiii.network.RouterGUIButtonMessage;
import net.mcreator.networkcraftiii.NetworkcraftiiiMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class RouterGUIScreen extends AbstractContainerScreen<RouterGUIMenu> {
	private final static HashMap<String, Object> guistate = RouterGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;

	public RouterGUIScreen(RouterGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 142;
		this.imageHeight = 90;
	}

	private static final ResourceLocation texture = new ResourceLocation("networkcraftiii:textures/screens/router_gui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "RouterIP:", 8, 8, -12829636);
		this.font.draw(poseStack, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "routerIP")) + "", 61, 8, -12829636);
		this.font.draw(poseStack, "Router Status:", 8, 21, -12829636);
		this.font.draw(poseStack, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "routerStatus")) + "", 86, 21, -12829636);
		this.font.draw(poseStack, "Router:", 8, 42, -12829636);
		this.font.draw(poseStack, "Devices:", 7, 66, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		this.addRenderableWidget(new Button(this.leftPos + 54, this.topPos + 37, 35, 20, new TextComponent("On"), e -> {
			if (true) {
				NetworkcraftiiiMod.PACKET_HANDLER.sendToServer(new RouterGUIButtonMessage(0, x, y, z));
				RouterGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 93, this.topPos + 37, 40, 20, new TextComponent("Off"), e -> {
			if (true) {
				NetworkcraftiiiMod.PACKET_HANDLER.sendToServer(new RouterGUIButtonMessage(1, x, y, z));
				RouterGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 54, this.topPos + 61, 67, 20, new TextComponent("Disconnect"), e -> {
			if (true) {
				NetworkcraftiiiMod.PACKET_HANDLER.sendToServer(new RouterGUIButtonMessage(2, x, y, z));
				RouterGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}));
	}
}
