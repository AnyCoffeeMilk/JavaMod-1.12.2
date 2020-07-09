package com.mods.reaper.init;

import java.util.ArrayList;
import java.util.List;

import com.mods.reaper.items.ItemBase;
import com.mods.reaper.items.ReaperScythe;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemStack;

public class ModItems 
{
	public static final List<Item> ITEMS = new ArrayList<Item>();
	
	//Items
	public static final Item VOID_INGOT = new ItemBase("void_ingot");
	public static final Item VOID_NUGGET = new ItemBase("void_nugget");
	public static final Item VOID_DUST = new ItemBase("void_dust");
	
	//Tools
	public static final Item SCYTHE = new ItemBase("scythe");
	public static final Item UNEMPOWERED_REAPER_SCYTHE = new ItemBase("unempowered_reaper_scythe");
	public static final Item REAPER_SCYTHE = new ReaperScythe("reaper_scythe");
}
