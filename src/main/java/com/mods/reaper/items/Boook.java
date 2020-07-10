package com.mods.reaper.items;

import com.mods.reaper.Main;
import com.mods.reaper.init.ModItems;
import com.mods.reaper.init.ModTab;
import com.mods.reaper.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

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
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) 
	{
		ItemStack item = playerIn.getHeldItem(handIn);
		if (item.getItem() == ModItems.BOOOK) 
		{
			System.out.println("onItemRightClick");
			return super.onItemRightClick(worldIn, playerIn, handIn);
		}
		return null;
	}
}
