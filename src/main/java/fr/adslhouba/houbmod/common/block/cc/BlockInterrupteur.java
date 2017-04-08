package fr.adslhouba.houbmod.common.block.cc;

import java.util.Random;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import fr.adslhouba.houbmod.common.HoubMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockInterrupteur extends BlockContainer implements IPeripheralProvider  {
	public BlockInterrupteur()
    {
        super(Material.iron);
        this.setBlockName("ccInterupteur");
		this.setCreativeTab(HoubMod.HoubModCreativeTabs);
		this.setHardness(3.5F);
		this.setResistance(5.5F);
//		this.IsProvidingWeakPower (IBlockAccess worldIn, BlockPos pos, IBlockState state, EnumFacing side); 
    }
	@Override
	public boolean canProvidePower()
	{
	    return true;
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity instanceof TileEntityInterupteur) {

            return ((TileEntityInterupteur)tileEntity).intensite;

        }
		return 0;
	}	
	
	 
	@Override
	public TileEntityInterupteur createNewTileEntity(World world, int metadata)
    {
        return new TileEntityInterupteur(this);
    }

	 
    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    
    @Override
	public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		
		if (tileEntity instanceof TileEntityInterupteur) {
			TileEntityInterupteur fuck=(TileEntityInterupteur) tileEntity;
			fuck.block=this;
			return fuck;
		}
		return null;
	}

    
}
