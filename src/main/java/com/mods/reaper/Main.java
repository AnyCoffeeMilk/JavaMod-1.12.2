package com.mods.reaper;

import com.mods.reaper.init.ModRecipes;
import com.mods.reaper.init.ModTab;
import com.mods.reaper.proxy.CommonProxy;
import com.mods.reaper.util.Reference;
import com.mods.reaper.util.handlers.ModGuiHandler;
import com.mods.reaper.util.handlers.RegistryHandler;
import com.mods.reaper.world.ModWorldGen;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main 
{
	@Instance(Reference.MOD_ID)
	public static Main instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	public static final CreativeTabs modtab = new ModTab("reapertab");
	
	@EventHandler
	public void PreInit(FMLPreInitializationEvent event) { RegistryHandler.preInitRegistries(); }
	
	@EventHandler
	public static void Init(FMLInitializationEvent event) { RegistryHandler.InitRegistries(); }
	
	@EventHandler
	public static void PostInit(FMLPostInitializationEvent event) { }
}
