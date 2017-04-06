package fr.adslhouba.houbmod.common.block.cc;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import fr.adslhouba.houbmod.common.HoubMod;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockInterrupteur extends BlockContainer implements IPeripheralProvider  {
	public BlockInterrupteur()
    {
        super(Material.iron);
        this.setBlockName("ccInterupteur");
		this.setCreativeTab(HoubMod.HoubModCreativeTabs);
		this.setHardness(3.5F);
		this.setResistance(5.5F);
		
    }
	@Override
	public TileEntity createNewTileEntity(World world, int metadata)
    {
        return new TileEntityInterupteur();
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
			return (TileEntityInterupteur) tileEntity;
		}
		return null;
	}
}
