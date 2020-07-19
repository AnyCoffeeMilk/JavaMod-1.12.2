package com.mods.reaper.events;

import com.mods.reaper.init.ModItems;
import com.mods.reaper.util.Reference;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
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
