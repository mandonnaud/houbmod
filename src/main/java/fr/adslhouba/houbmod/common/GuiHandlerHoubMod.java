package fr.adslhouba.houbmod.common;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import fr.adslhouba.houbmod.common.block.cc.TileEntityEnclumeCPO;

public class GuiHandlerHoubMod implements IGuiHandler
{
   @Override
   public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
   {
	   TileEntity tile = world.getTileEntity(x, y, z);
       if(tile instanceof TileEntityEnclumeCPO)
       {
           return new ContainerCupboard((TileEntityEnclumeCPO)tile, player.inventory);
       }
       return null;
   }

   @Override
   public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
   {
	   TileEntity tile = world.getTileEntity(x, y, z);
       if(tile instanceof TileEntityEnclumeCPO)
       {
           return new GuiCupboard((TileEntityEnclumeCPO)tile, player.inventory);
       }
       return null;
   }
}