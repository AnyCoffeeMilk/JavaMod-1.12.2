package com.mods.reaper.items;

import java.util.List;

import javax.annotation.Nullable;

import com.mods.reaper.Main;
import com.mods.reaper.events.SoulCounter;
import com.mods.reaper.init.ModEnchantments;
import com.mods.reaper.init.ModItems;
import com.mods.reaper.util.IHasModel;
import com.mods.reaper.util.Reference;
import com.mods.reaper.util.handlers.ModGuiHandler;

import net.minecraft.item.ItemSword;
import net.minecraft.world.World;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class ReaperScythe extends ItemSword implements IHasModel, ICapabilityProvider
{
	private ItemStackHandler handler;
	@CapabilityInject(IItemHandler.class)
	static Capability<IItemHandler> ITEM_HANDLER_CAPABILITY = null;
	
	public ReaperScythe(String name) {
		super(ToolMaterial.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.modtab);
		
		ModItems.ITEMS.add(this);
		this.handler = new ItemStackHandler(1);
	}
	
	@Override
	public void registerModels() 
	{
		Main.proxy.registerItemRenderer(this, 0, "inventory");
	}
	
	@Override
	public boolean canHarvestBlock(IBlockState blockIn) 
	{ 
		return false; 
	}
	
	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items)
	{
		if (tab == Main.modtab) 
		{
			ItemStack stack = new ItemStack(ModItems.REAPER_SCYTHE);
			stack.addEnchantment(ModEnchantments.BLOOD_BINDING_CURSE, 1);
			items.add(stack);
		}
	}
	
	@Override
    public boolean hitEntity(ItemStack item, EntityLivingBase entity, EntityLivingBase player) 
	{
        item.damageItem(0, player);
        return true;
    }
	
	@Override
    public boolean onBlockDestroyed(ItemStack item, World world, IBlockState block, BlockPos pos, EntityLivingBase player)
    {
        if ((double)block.getBlockHardness(world, pos) != 0.0D)
        {
            item.damageItem(0, player);
        }
        return true;
    }
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int i, boolean bool) 
	{
		super.onUpdate(stack, world, entity, i, bool);
		if(!stack.isItemEnchanted()) 
		{
			stack.addEnchantment(ModEnchantments.BLOOD_BINDING_CURSE, 1);
			super.onUpdate(stack, world, entity, i, bool);
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> lore, ITooltipFlag flagIn)
    {
        if (stack.hasTagCompound() && stack.getTagCompound().hasKey("Souls"))
        {
            lore.add("§cSouls : " + Integer.toString(stack.getTagCompound().getInteger("Souls")));
        }
    }
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
		if (player.getHeldItemMainhand().getItem() == ModItems.REAPER_SCYTHE && player.isSneaking())
		{
			player.openGui(Main.instance, Reference.SCYTHE_UPGRADE, world, hand.ordinal(), player.chunkCoordY, player.chunkCoordZ);
		}
        return new ActionResult<ItemStack>(EnumActionResult.PASS, player.getHeldItem(hand));
    }
	
	
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) 
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return (T) this.handler;
		}
		return this.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) 
	{
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
		{
			return true;
		}
		return this.hasCapability(capability, facing);
	}
}
