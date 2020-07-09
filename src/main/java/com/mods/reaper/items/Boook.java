package com.mods.reaper.items;

import com.mods.reaper.Main;
import com.mods.reaper.init.ModItems;
import com.mods.reaper.init.ModTab;
import com.mods.reaper.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Boook extends Item implements IHasModel
{
	public Boook(String name)
	{
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modtab);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
}
