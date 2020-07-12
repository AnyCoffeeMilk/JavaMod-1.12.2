package com.mods.reaper.init;

import java.util.ArrayList;
import java.util.List;

import com.mods.reaper.blocks.BlockBase;
import com.mods.reaper.blocks.DeadStone;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class ModBlocks 
{
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	
	public static final Block DEAD_STONE = new DeadStone("dead_stone", Material.ROCK);
}
