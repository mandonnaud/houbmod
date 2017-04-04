package fr.adslhouba.houbmod.common.block.turtlerally;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.adslhouba.houbmod.common.HoubMod;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;

public class BlockTRTapisItem extends ItemBlock {
	public BlockTRTapisItem(Block block)
	{
		super(block);
		itemfordesc = block;
		this.setMaxDamage(0);
	}
	private Block itemfordesc;
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		
		par2List.add(StatCollector.translateToLocal("fr.adslhouba.trblock.addInfo1"));
		par2List.add("\u00A7a"+StatCollector.translateToLocal("fr.adslhouba.trtapis.code"));
		if (itemfordesc==HoubMod.trTapis) {
			par2List.add("\u00A7a"+StatCollector.translateToLocal("fr.adslhouba.trtapis.code1"));
		}
		else if (itemfordesc==HoubMod.trTapisDroite) {
			par2List.add("\u00A7a"+StatCollector.translateToLocal("fr.adslhouba.trtapis.code2"));
		}
		else if (itemfordesc==HoubMod.trTapisGauche) {
			par2List.add("\u00A7a"+StatCollector.translateToLocal("fr.adslhouba.trtapis.code3"));
		}
		par2List.add("\u00A7c"+StatCollector.translateToLocal("fr.adslhouba.trblock.addInfo2"));
		
	}
}
