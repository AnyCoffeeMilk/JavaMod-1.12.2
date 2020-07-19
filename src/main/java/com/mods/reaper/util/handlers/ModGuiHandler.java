package com.mods.reaper.util.handlers;

import com.mods.reaper.gui.ScytheUpgrade;
import com.mods.reaper.gui.container.ContainerScytheUpgrade;
import com.mods.reaper.init.ModItems;
import com.mods.reaper.items.ReaperScythe;
import com.mods.reaper.util.Reference;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class ModGuiHandler implements IGuiHandler 
{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (ID == Reference.SCYTHE_UPGRADE) 
		{
			ItemStack stack = player.getHeldItemMainhand();
			if (stack.getItem() == ModItems.REAPER_SCYTHE)
			{
				ReaperScythe rs = (ReaperScythe)stack.getItem();
				return new ContainerScytheUpgrade(player.inventory, rs);
			}
		}
		return null;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		if (ID == Reference.SCYTHE_UPGRADE)
		{
			ItemStack stack = player.getHeldItemMainhand();
			if (stack.getItem() == ModItems.REAPER_SCYTHE)
			{
				ReaperScythe rs = (ReaperScythe)stack.getItem();
				return new ScytheUpgrade(player.inventory, rs);
			}
		}
		return null;
	}
}
