package com.mods.reaper.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes 
{
	public static void init()
	{
		GameRegistry.addSmelting(ModItems.VOID_DUST, new ItemStack(ModItems.VOID_NUGGET, 1), 1.0F);
	}
}
