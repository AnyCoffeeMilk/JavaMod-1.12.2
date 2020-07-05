package com.mods.reaper.blocks;

import java.util.Random;

import com.mods.reaper.init.ModItems;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;

public class DeadStone extends BlockBase
{
	public DeadStone(String name, Material material)
	{
		super(name, material);
		setSoundType(SoundType.STONE);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 3);
	}
	
	@Override
	public Item getItemDropped(IBlockState state,Random rand, int fortune)
	{
		return ModItems.VOID_DUST;
	}
	
	@Override
	public int quantityDropped(Random rand)
	{
		int max = 2;
		int min = 3;
		return rand.nextInt(max) + min;
	}
}
