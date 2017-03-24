package fr.adslhouba.houbmod.common;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockGenLaser extends BlockContainer  implements IPeripheralProvider  {
	public BlockGenLaser()
    {
        super(Material.iron);
    }
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityGenLaser();
    }

    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    
    @Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		if (tileEntity instanceof TileEntityGenLaser) {
			return (TileEntityGenLaser) tileEntity;
		}
		return null;
	}
}
