package fr.adslhouba.houbmod.common.block.cc;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import fr.adslhouba.houbmod.common.HoubMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class blockMaj extends BlockContainer  implements IPeripheralProvider  {
	public blockMaj()
    {
        super(Material.iron);
    }
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityMaj();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    /*
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
    {
        if(world.isRemote)
        {
            return true;
        }
        else
        {
            player.openGui(HoubMod.instance, 0, world, x, y, z);
            return true;
        }
    }
    public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        TileEntity tileentity = world.getTileEntity(x, y, z);

        if(tileentity instanceof IInventory)
        {
            IInventory inv = (IInventory)tileentity;
            for(int i1 = 0; i1 < inv.getSizeInventory(); ++i1)
            {
                ItemStack itemstack = inv.getStackInSlot(i1);

                if(itemstack != null)
                {
                    float f = world.rand.nextFloat() * 0.8F + 0.1F;
                    float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for(float f2 = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
                    {
                        int j1 = world.rand.nextInt(21) + 10;

                        if(j1 > itemstack.stackSize)
                        {
                            j1 = itemstack.stackSize;
                        }

                        itemstack.stackSize -= j1;
                        entityitem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j1, itemstack.getItemDamage()));
                        float f3 = 0.05F;
                        entityitem.motionX = (double)((float)world.rand.nextGaussian() * f3);
                        entityitem.motionY = (double)((float)world.rand.nextGaussian() * f3 + 0.2F);
                        entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f3);

                        if(itemstack.hasTagCompound())
                        {
                            entityitem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }
            world.func_147453_f(x, y, z, block);
        }
        super.breakBlock(world, x, y, z, block, metadata);
    }
    */
    @Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		if (tileEntity instanceof TileEntityMaj) {
			return (TileEntityMaj) tileEntity;
		}
		return null;
	}
    @SideOnly(Side.CLIENT)
	public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par2List, boolean par4)
	{
		par2List.add(StatCollector.translateToLocal("fr.adslhouba.maj.info"));
		par2List.add(StatCollector.translateToLocal("fr.adslhouba.maj.info2"));
	}
}
