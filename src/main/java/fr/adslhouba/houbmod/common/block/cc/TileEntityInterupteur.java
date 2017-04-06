package fr.adslhouba.houbmod.common.block.cc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.turtle.ITurtleAccess;
import fr.adslhouba.houbmod.common.entity.EntityProjectil;
import fr.adslhouba.houbmod.utils.houbPeripheral;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityInterupteur extends TileEntity  implements houbPeripheral {
	public static String publicName = "hb_interupteur";
	private String name = "TileEntityInterupteur";
	private List<EntityProjectil> aSpawn=new ArrayList<EntityProjectil>();
	private HashMap<IComputerAccess,Boolean> computers = new HashMap<IComputerAccess,Boolean>();
	private ITurtleAccess turtle;
	public static final String[] METHOD_NAMES = new String[]{"set","get"};
	private int intensite = 0;
	private boolean changement = false;
	
	public TileEntityInterupteur() {
		super();
	}

	public TileEntityInterupteur(ITurtleAccess turtle) {
		this.xCoord = turtle.getPosition().posX;
		this.yCoord = turtle.getPosition().posY;
		this.zCoord = turtle.getPosition().posZ;
		this.setWorldObj(turtle.getWorld());
		this.turtle = turtle;
	}
	
	public String getName() {
		return name;
	}

	@Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
    }
    
	@Override
	public String getType() {
		return publicName;
	}
	
	@Override
	public boolean equals(IPeripheral other) {
		return other instanceof TileEntityInterupteur && other == this;
	}
	
	@Override
	public String[] getMethodNames() {
		return METHOD_NAMES;
	}
	
	@Override
    public void updateEntity()
    {
		
    }
	
	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
		switch(method) {
			case 0:	// set
				if (arguments.length != 1)
					throw new LuaException("Only argument intensity redstonne power");
				if (!(arguments[0] instanceof Double))
					throw new LuaException("Bad argument #1 X (expected number)");
				
				Double intesiteRecu=(Double) arguments[0];
				this.intensite=(int) Math.round(intesiteRecu);
				this.changement=true;
			break;
		}
		return new Object[0];
	}
	@Override
	public void attach(IComputerAccess computer) {
		
	}
	@Override
	public void detach(IComputerAccess computer) {
		
	}	
}
