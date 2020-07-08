package com.mods.reaper.enchantments;

import com.mods.reaper.init.ModEnchantments;
import com.mods.reaper.util.Reference;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EnchantmentBloodBindingCurse extends Enchantment
{
	public EnchantmentBloodBindingCurse()
	{
		 super(Rarity.VERY_RARE, EnumEnchantmentType.WEAPON, new EntityEquipmentSlot[] { EntityEquipmentSlot.MAINHAND });
		 this.setName("blood_binding_curse");
		 this.setRegistryName(new ResourceLocation(Reference.MOD_ID + ":blood_binding_curse"));
		 
		 ModEnchantments.ENCHANTMENT.add(this);
	}
	
	@Override
	public int getMinEnchantability(int enchantmentLevel)
    {
        return 25;
    }
	
	@Override
	public int getMaxEnchantability(int enchantmentLevel)
    {
        return 25;
    }
	
	@Override
    public int getMaxLevel() 
	{ 
		return 1; 
	}
	
	@Override
    public boolean isTreasureEnchantment() 
	{ 
		return false; 
	}
	
	public boolean isCurse()
    {
        return true;
    }
	
	@SubscribeEvent
	public void EntityJoinWorldEvent(net.minecraftforge.event.entity.EntityJoinWorldEvent event) 
	{
		
	}
}
