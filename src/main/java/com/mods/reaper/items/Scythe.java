package com.mods.reaper.items;

import com.mods.reaper.Main;
import com.mods.reaper.init.ModItems;
import com.mods.reaper.util.IHasModel;

import net.minecraft.item.ItemSword;
import net.minecraft.item.Item.ToolMaterial;

public class Scythe extends ItemSword implements IHasModel
{
	public Scythe(String name) {
		super(ToolMaterial.STONE);
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
