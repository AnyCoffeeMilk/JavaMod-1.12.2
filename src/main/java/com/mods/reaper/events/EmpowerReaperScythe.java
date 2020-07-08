package com.mods.reaper.events;

import com.mods.reaper.init.ModItems;
import com.mods.reaper.util.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class EmpowerReaperScythe 
{
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerKillMob(AttackEntityEvent evt)
	{
		EntityPlayer player = evt.getEntityPlayer();
		Entity e = evt.getTarget();
		World world = player.getEntityWorld();
		ResourceLocation location = new ResourceLocation("block.end_portal.spawn");
		SoundEvent event = new SoundEvent(location);
		if (e instanceof EntityVillager)
		{
			if (player.getHeldItemMainhand().getItem() == ModItems.UNEMPOWERED_REAPER_SCYTHE)
			{
				if (world.isRemote)
				{
					EntityLightningBolt lb = new EntityLightningBolt(world, e.posX, e.posY, e.posZ, true);
		            world.spawnEntity(lb);
				}
				player.playSound(event, 1.0F, 1.0F);
				e.attackEntityFrom(DamageSource.causePlayerDamage(player), ((EntityVillager) e).getHealth());
				player.setHeldItem(EnumHand.MAIN_HAND, new ItemStack(ModItems.REAPER_SCYTHE));
			}
		}
	}
}
