package com.mods.reaper.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mods.reaper.Main;
import com.mods.reaper.events.SoulCounter;
import com.mods.reaper.init.ModEnchantments;
import com.mods.reaper.init.ModItems;
import com.mods.reaper.util.IHasModel;

import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ReaperScythe extends ItemSword implements IHasModel
{
	public ReaperScythe(String name) {
		super(ToolMaterial.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modtab);
		addInformation(item, world, lore, flagIn);
		
		ModItems.ITEMS.add(this);
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn)
    {
        return false;
    }
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (tab == Main.modtab) 
		{
			ItemStack item = new ItemStack(ModItems.REAPER_SCYTHE);
			item.addEnchantment(ModEnchantments.BLOOD_BINDING_CURSE, 1);
			items.add(item);
		}
	}
	
	@Override
    public boolean hitEntity(ItemStack item, EntityLivingBase entity, EntityLivingBase player)
    {
        item.damageItem(0, player);
        return true;
    }
	
	@Override
    public boolean onBlockDestroyed(ItemStack item, World world, IBlockState block, BlockPos pos, EntityLivingBase player)
    {
        if ((double)block.getBlockHardness(world, pos) != 0.0D)
        {
            item.damageItem(0, player);
        }
        return true;
    }
	
	@Override
	public void addInformation(ItemStack item, World world,List<String> lore,ITooltipFlag flagIn) 
	{
		if (item.getItem() == ModItems.UNEMPOWERED_REAPER_SCYTHE)
		{
			lore.add(1, SoulCounter.Souls.);
		}
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean bool) {
		super.onUpdate(stack, world, entity, i, bool);
		if(stack.isItemEnchanted() == false) 
		{
			stack.addEnchantment(ModEnchantments.BLOOD_BINDING_CURSE, 1);
			super.onUpdate(stack, world, entity, i, bool);
		}
	}
}
