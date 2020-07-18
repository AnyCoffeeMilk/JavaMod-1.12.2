package com.mods.reaper.events;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.mods.reaper.init.ModItems;
import com.mods.reaper.util.Reference;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class SoulCounter
{
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerKillMob(LivingDeathEvent evt) 
	{
		EntityLiving e = (EntityLiving) evt.getEntityLiving();
		DamageSource source = evt.getSource();
		if (source.getTrueSource() instanceof EntityPlayer) 
		{
			EntityPlayer player = (EntityPlayer) source.getTrueSource();
			ItemStack stack = player.getHeldItemMainhand();
			if (stack.getItem() == ModItems.REAPER_SCYTHE)
			{
				NBTTagCompound nbt = stack.getTagCompound();
				nbt.setInteger("Souls", nbt.getInteger("Souls") + 1);
				stack.setTagCompound(nbt);
			}
		}
	}
}
