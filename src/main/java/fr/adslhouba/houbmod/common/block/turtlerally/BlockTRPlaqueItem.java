package fr.adslhouba.houbmod.common.block.turtlerally;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;

public class BlockTRPlaqueItem extends ItemBlock
{
	private final String[] subName;
	private int metadataItem=0;
	public BlockTRPlaqueItem(Block block, String[] subBlock)
	{
		super(block);
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.subName = subBlock;
	}
	public int getMetadata(int metadata)
	{
		return metadata;
	}
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int metadata)
	{
		return this.field_150939_a.getIcon(2, metadata);
	}
	public String getUnlocalizedName(ItemStack stack)
	{
		int metadata = stack.getItemDamage();
		if(metadata < 0 || metadata >= BlockTRPlaque.subBlock.length)
		{
			metadata = 0;
		}
		metadataItem=metadata;
		return super.getUnlocalizedName() + "." + BlockTRPlaque.subBlock[metadata];
	}
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add(StatCollector.translateToLocal("fr.adslhouba.trblock.addInfo1"));
		par2List.add("\u00A7a"+StatCollector.translateToLocal("fr.adslhouba.plaque.code"));
		par2List.add("\u00A7a"+StatCollector.translateToLocal("fr.adslhouba.plaque.code"+metadataItem));
		par2List.add("\u00A7c"+StatCollector.translateToLocal("fr.adslhouba.trblock.addInfo2"));
	}
}