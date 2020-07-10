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
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class SoulCounter
{
	public static int Souls = 0;
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerKillMob(LivingDeathEvent evt) 
	{
		EntityLiving e = (EntityLiving) evt.getEntityLiving();
		DamageSource source = e.getLastDamageSource();
		if (source.getTrueSource() instanceof EntityPlayer) 
		{
			EntityPlayer player = (EntityPlayer) source.getTrueSource();
			ItemStack item = player.getHeldItemMainhand();
			World world = player.getEntityWorld();
			ITooltipFlag flagIn;
			List<String> lore = new ArrayList<String>();
			if (item.getItem() == ModItems.UNEMPOWERED_REAPER_SCYTHE)
			{
				Souls = Souls + 1;
			}
		}
	}
}
