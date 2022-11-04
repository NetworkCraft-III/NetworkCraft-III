
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
		this.imageWidth = 143;
		this.imageHeight = 98;
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
		this.font.draw(poseStack, "RouterIP:", 22, 7, -12829636);
		this.font.draw(poseStack, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "routerIP")) + "", 73, 7, -12829636);
		this.font.draw(poseStack, "Router Status:", 16, 20, -12829636);
		this.font.draw(poseStack, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "routerStatus")) + "", 92, 20, -12829636);
		this.font.draw(poseStack, "Internet Status:", 6, 33, -12829636);
		this.font.draw(poseStack, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "internetStatus")) + "", 92, 33, -12829636);
		this.font.draw(poseStack, "Internet:", 9, 53, -12829636);
		this.font.draw(poseStack, "Router:", 19, 75, -12829636);
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
		this.addRenderableWidget(new Button(this.leftPos + 58, this.topPos + 71, 35, 20, new TextComponent("On"), e -> {
			if (true) {
				NetworkcraftiiiMod.PACKET_HANDLER.sendToServer(new RouterGUIButtonMessage(0, x, y, z));
				RouterGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 96, this.topPos + 71, 40, 20, new TextComponent("Off"), e -> {
			if (true) {
				NetworkcraftiiiMod.PACKET_HANDLER.sendToServer(new RouterGUIButtonMessage(1, x, y, z));
				RouterGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 58, this.topPos + 48, 35, 20, new TextComponent("On"), e -> {
			if (true) {
				NetworkcraftiiiMod.PACKET_HANDLER.sendToServer(new RouterGUIButtonMessage(2, x, y, z));
				RouterGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 96, this.topPos + 48, 40, 20, new TextComponent("Off"), e -> {
			if (true) {
				NetworkcraftiiiMod.PACKET_HANDLER.sendToServer(new RouterGUIButtonMessage(3, x, y, z));
				RouterGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}));
	}
}
