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
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
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
			List<String> lore = new ArrayList<String>();
			if (item.getItem() == ModItems.UNEMPOWERED_REAPER_SCYTHE)
			{
				ITooltipFlag flagIn = null;
				List<String> list = item.getTooltip(player, flagIn);
				for (int i = 0; i < list.size(); i++)
				{
					String nbt = list.get(i);
					if (nbt.contains("§4Soul:"))
					{
						int Souls = Integer.parseInt(nbt.replace("§4Soul:", ""));
						Souls = Souls + 1;
						NBTTagCompound tag = item.getTagCompound();
						NBTTagList soullore = new NBTTagList();
						soullore.appendTag(new NBTTagString("§4Soul:"+Souls));
						item.setTagInfo("Lore", soullore);
					}
				}
			}
		}
	}
}
