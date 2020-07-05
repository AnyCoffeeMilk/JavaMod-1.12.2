package com.mods.reaper.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class ModTab extends CreativeTabs
{
	public ModTab(String label) 
	{
		super("modtab");
		this.setBackgroundImageName("reaper.png");
	}

	@Override
	public ItemStack getTabIconItem() 
	{
		return new ItemStack(ModItems.SCYTHE);
	}
}
