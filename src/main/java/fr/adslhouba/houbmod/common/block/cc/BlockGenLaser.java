package fr.adslhouba.houbmod.common.block.cc;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import fr.adslhouba.houbmod.common.HoubMod;
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
        this.setBlockName("genLaser");
        this.setBlockTextureName("houbMod:genlaser");
        this.setCreativeTab(HoubMod.HoubModCreativeTabs);
        this.setHardness(3.5F);
        this.setResistance(5.5F);
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
