package com.mods.reaper.events;

import java.util.ListIterator;

import com.mods.reaper.init.ModEnchantments;
import com.mods.reaper.util.Reference;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class BloodBinding
{
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerDeath(PlayerDropsEvent evt) 
	{
		if (evt.getEntityPlayer() == null || evt.getEntityPlayer() instanceof net.minecraftforge.common.util.FakePlayer || evt.isCanceled()) { return;}
		if ((evt.getEntityPlayer()).world.getGameRules().getBoolean("keepInventory")) { return;}
		
		ListIterator<EntityItem> iter = evt.getDrops().listIterator();
		while (iter.hasNext()) 
		{
			EntityItem ei = iter.next();
			ItemStack item = ei.getItem();
			if (bindingToPlayer(item) && addToPlayerInventory(evt.getEntityPlayer(), item))
			{
				iter.remove();
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerRespawn(PlayerEvent.Clone evt) 
	{
		if (!evt.isWasDeath() || evt.isCanceled()) { return;}
		if (evt.getOriginal() == null || evt.getEntityPlayer() == null || evt.getEntityPlayer() instanceof net.minecraftforge.common.util.FakePlayer) { return;}
		if ((evt.getEntityPlayer()).world.getGameRules().getBoolean("keepInventory")) { return;}
		if (evt.getOriginal() == evt.getEntityPlayer() || (evt.getOriginal()).inventory == (evt.getEntityPlayer()).inventory || ((evt.getOriginal()).inventory.armorInventory == (evt.getEntityPlayer()).inventory.armorInventory && (evt.getOriginal()).inventory.mainInventory == (evt.getEntityPlayer()).inventory.mainInventory)) { return;}
		
		for (int i = 0; i < (evt.getOriginal()).inventory.mainInventory.size(); i++) 
		{
			ItemStack item = (ItemStack)(evt.getOriginal()).inventory.mainInventory.get(i);
			if (bindingToPlayer(item) && addToPlayerInventory(evt.getEntityPlayer(), item))
			{
				(evt.getOriginal()).inventory.mainInventory.set(i, ItemStack.EMPTY);
			}
		}
	}

	private static boolean bindingToPlayer(ItemStack item) 
	{
		Enchantment e = ModEnchantments.BLOOD_BINDING_CURSE;
		if (EnchantmentHelper.getEnchantments(item).containsKey(e))
		{
			return true;
		}
		return false;
	}
	
	private static boolean addToPlayerInventory(EntityPlayer entityPlayer, ItemStack item) 
	{
		if (item == null || entityPlayer == null) 
		{
			return false;
		}
		
		InventoryPlayer inv = entityPlayer.inventory;
		for (int i = 0; i < inv.mainInventory.size(); i++) 
		{
			if (((ItemStack)inv.mainInventory.get(i)).isEmpty()) 
			{
				inv.mainInventory.set(i, item.copy());
				return true;
			}
		}
		return false;
	}
}
