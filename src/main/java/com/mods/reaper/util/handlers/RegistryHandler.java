package com.mods.reaper.util.handlers;

import com.mods.reaper.Main;
import com.mods.reaper.init.ModBlocks;
import com.mods.reaper.init.ModEnchantments;
import com.mods.reaper.init.ModItems;
import com.mods.reaper.init.ModRecipes;
import com.mods.reaper.util.IHasModel;
import com.mods.reaper.world.ModWorldGen;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@EventBusSubscriber
public class RegistryHandler 
{
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event)
	{
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}
	
	@SubscribeEvent
	public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event)
	{
		event.getRegistry().registerAll(ModEnchantments.ENCHANTMENT.toArray(new Enchantment[0]));
	}
	
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ModItems.ITEMS)
		{
			if(item instanceof IHasModel) 
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : ModBlocks.BLOCKS)
		{
			if(block instanceof IHasModel) 
			{
				((IHasModel)block).registerModels();
			}
		}
	}
	
	public static void preInitRegistries()
	{
		GameRegistry.registerWorldGenerator(new ModWorldGen(), 3);
	}
	
	public static void InitRegistries()
	{
		ModRecipes.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(Main.instance, new ModGuiHandler());
	}
}
