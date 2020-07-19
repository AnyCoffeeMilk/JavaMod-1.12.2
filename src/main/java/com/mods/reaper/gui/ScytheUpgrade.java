package com.mods.reaper.gui;

import com.mods.reaper.gui.container.ContainerScytheUpgrade;
import com.mods.reaper.init.ModBlocks;
import com.mods.reaper.items.ReaperScythe;
import com.mods.reaper.util.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;

public class ScytheUpgrade extends GuiContainer
{
	private static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/gui/pedestal.png");
	private ReaperScythe rs;
	private IInventory playerInv;
	private final ContainerScytheUpgrade rsInv;

	public ScytheUpgrade(IInventory playerInv, ReaperScythe rs) 
	{
		super(new ContainerScytheUpgrade(playerInv, rs));
		this.rs = rs;
		this.playerInv = playerInv;
		this.rsInv = (ContainerScytheUpgrade)this.inventorySlots;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) 
	{
		String name = I18n.format("Scythe Upgrade");
		this.fontRenderer.drawString(name, (this.xSize / 2 - this.fontRenderer.getStringWidth(name) / 2) + 3, 8, 4210752);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) 
	{
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		this.mc.getTextureManager().bindTexture(TEXTURES);
		drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);
	}
}
