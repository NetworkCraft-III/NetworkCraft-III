
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
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import net.mcreator.networkcraftiii.world.inventory.LaptopGUIMenu;
import net.mcreator.networkcraftiii.network.LaptopGUIButtonMessage;
import net.mcreator.networkcraftiii.NetworkcraftiiiMod;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class LaptopGUIScreen extends AbstractContainerScreen<LaptopGUIMenu> {
	private final static HashMap<String, Object> guistate = LaptopGUIMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox specifiedRouter;

	public LaptopGUIScreen(LaptopGUIMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 219;
		this.imageHeight = 129;
	}

	private static final ResourceLocation texture = new ResourceLocation("networkcraftiii:textures/screens/laptop_gui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		specifiedRouter.render(ms, mouseX, mouseY, partialTicks);
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
		if (specifiedRouter.isFocused())
			return specifiedRouter.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		specifiedRouter.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "MAC Address:", 23, 16, -12829636);
		this.font.draw(poseStack, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "macAddress")) + "", 92, 16, -12829636);
		this.font.draw(poseStack, "Status:", 49, 6, -12829636);
		this.font.draw(poseStack, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "status")) + "", 92, 6, -12829636);
		this.font.draw(poseStack, "Router IP:", 33, 37, -12829636);
		this.font.draw(poseStack, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "routerIP")) + "", 92, 38, -12829636);
		this.font.draw(poseStack, "Laptop:", 48, 58, -12829636);
		this.font.draw(poseStack, "Laptop IP:", 33, 27, -12829636);
		this.font.draw(poseStack, "" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				BlockEntity BlockEntity = world.getBlockEntity(pos);
				if (BlockEntity != null)
					return BlockEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "assignedIP")) + "", 92, 27, -12829636);
		this.font.draw(poseStack, "Connect:", 42, 82, -12829636);
		this.font.draw(poseStack, "Router IP:", 32, 107, -12829636);
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
		this.addRenderableWidget(new Button(this.leftPos + 88, this.topPos + 54, 35, 20, new TextComponent("On"), e -> {
			if (true) {
				NetworkcraftiiiMod.PACKET_HANDLER.sendToServer(new LaptopGUIButtonMessage(0, x, y, z));
				LaptopGUIButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 127, this.topPos + 54, 40, 20, new TextComponent("Off"), e -> {
			if (true) {
				NetworkcraftiiiMod.PACKET_HANDLER.sendToServer(new LaptopGUIButtonMessage(1, x, y, z));
				LaptopGUIButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}));
		specifiedRouter = new EditBox(this.font, this.leftPos + 88, this.topPos + 102, 120, 20, new TextComponent("000.000.000.000")) {
			{
				setSuggestion("000.000.000.000");
			}

			@Override
			public void insertText(String text) {
				super.insertText(text);
				if (getValue().isEmpty())
					setSuggestion("000.000.000.000");
				else
					setSuggestion(null);
			}

			@Override
			public void moveCursorTo(int pos) {
				super.moveCursorTo(pos);
				if (getValue().isEmpty())
					setSuggestion("000.000.000.000");
				else
					setSuggestion(null);
			}
		};
		guistate.put("text:specifiedRouter", specifiedRouter);
		specifiedRouter.setMaxLength(32767);
		this.addWidget(this.specifiedRouter);
		this.addRenderableWidget(new Button(this.leftPos + 88, this.topPos + 78, 51, 20, new TextComponent("Connect"), e -> {
			if (true) {
				NetworkcraftiiiMod.PACKET_HANDLER.sendToServer(new LaptopGUIButtonMessage(2, x, y, z));
				LaptopGUIButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}));
		this.addRenderableWidget(new Button(this.leftPos + 143, this.topPos + 78, 67, 20, new TextComponent("Disconnect"), e -> {
			if (true) {
				NetworkcraftiiiMod.PACKET_HANDLER.sendToServer(new LaptopGUIButtonMessage(3, x, y, z));
				LaptopGUIButtonMessage.handleButtonAction(entity, 3, x, y, z);
			}
		}));
	}
}
