package fr.adslhouba.houbmod.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import fr.adslhouba.houbmod.common.block.cc.TileEntityMaj;

public class GuiHandlerMaj implements IGuiHandler
{
   @Override
   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
   {
	   TileEntity tile = world.getTileEntity(x, y, z);
       /*
	   if(tile instanceof TileEntityMaj)
       {
           return new ContainerCupboard((TileEntityMaj)tile, player.inventory);
       }
       */
       return null;
   }

   @Override
   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
   {
       return null;
   }
}