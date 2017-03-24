package fr.adslhouba.houbmod.utils;

import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public interface houbPeripheral extends IPeripheral {

	/**
	 * This is the common provider for all Peripherals++ TileEntities
	 */
	public static class Provider implements IPeripheralProvider {

		@Override
		public IPeripheral getPeripheral(World world, int x, int y, int z, int side) {
			TileEntity tile = world.getTileEntity(x, y, z);
			return tile instanceof houbPeripheral ? (houbPeripheral) tile : null;
		}
	}
}