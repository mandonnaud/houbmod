package fr.adslhouba.houbmod.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;
import dan200.computercraft.api.turtle.ITurtleAccess;
import fr.adslhouba.houbmod.utils.houbPeripheral;


public class TileEntityGenLaser extends TileEntity  implements houbPeripheral {
	public static String publicName = "laserGen";
	private String name = "tileEntityLaserGen";
	private List<EntityProjectil> aSpawn=new ArrayList<EntityProjectil>();
	private HashMap<IComputerAccess,Boolean> computers = new HashMap<IComputerAccess,Boolean>();
	private ITurtleAccess turtle;
	public static final String[] METHOD_NAMES = new String[]{"tire"};
	
	public TileEntityGenLaser() {
	
		super();
	}

	public TileEntityGenLaser(ITurtleAccess turtle) {
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
		return other instanceof TileEntityGenLaser && other == this;
	}
	
	@Override
	public String[] getMethodNames() {
		return METHOD_NAMES;
	}
	
	@Override
    public void updateEntity()
    {
    	if (aSpawn.size()!=0) {
    		Iterator itr = aSpawn.iterator();
    	     
    		while(itr.hasNext()) {
    		   EntityProjectil element = (EntityProjectil) itr.next();
    		   itr.remove();    		   
    		   worldObj.spawnEntityInWorld(element);
    		}
    	}
    }
	
	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
		switch(method) {
			case 0:	
				if (arguments.length < 4)
					throw new LuaException("Argument X Y Z DIRECTION [VITESSE]");
				if (!(arguments[0] instanceof Double))
					throw new LuaException("Bad argument #1 X (expected number)");
				if (!(arguments[1] instanceof Double))
					throw new LuaException("Bad argument #2 Y (expected number)");
				if (!(arguments[2] instanceof Double))
					throw new LuaException("Bad argument #3 Z (expected number)");
				
				Double X=(Double) arguments[0];
				Double Y=(Double) arguments[1];
				Double Z=(Double) arguments[2];
				
				if (!(arguments[3] instanceof String))
					throw new LuaException("Bad argument #4 (expected string)");

				if (!(arguments[3].equals("SOUTH") || arguments[3].equals("NORTH") || arguments[3].equals("WEST") || arguments[3].equals("EAST"))) {
					throw new LuaException("Bad argument ("+arguments[3]+") #4 (expected SOUTH NORTH WEST EAST");
				}
				
				Double vitesse=1.0;
				if (arguments.length > 4) {
					if(!(arguments[4] instanceof Double)) {
						throw new LuaException("Bad argument #5 (expected number)");
					} else {
						vitesse=(Double) arguments[4];
					}
				}
				
				if (Math.abs(X-this.xCoord)+Math.abs(Y-this.yCoord)>32) {
					throw new LuaException("Hors range max 32");
				}
				if (worldObj != null) {				
					EntityProjectil tire=new EntityProjectil(worldObj);
					
					tire.setPosition(X+0.5, Y+0.5, Z);
					tire.direction((String)arguments[3],vitesse);
					aSpawn.add(tire);
					
					
				}
				//
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
