package fr.adslhouba.houbmod.common.items.cc;

import fr.adslhouba.houbmod.utils.houbPeripheral;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class itemMaj extends Item {
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float px, float py, float pz)
	{
		Block block=world.getBlock(x, y, z);
		if (block.getLocalizedName().equals("Computer") ||  block.getLocalizedName().equals("Turtle") ||  block.getLocalizedName().equals(" Command Computer")) {
			TileEntity tileentity = world.getTileEntity(x, y, z);
			System.out.println(tileentity.getClass());
		}
		
		
		// BlockComputer
		// BlockCommandComputer
		// BlockTurtle
		return true;
	}
}
