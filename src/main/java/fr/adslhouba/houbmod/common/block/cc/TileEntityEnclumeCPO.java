package fr.adslhouba.houbmod.common.block.cc;

import java.lang.ref.WeakReference;
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
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TileEntityEnclumeCPO extends TileEntity  implements houbPeripheral, IInventory, IFluidHandler {
	public static String publicName = "enclumeCPO";
	private String name = "tileEntityEnclumeCPO";
	private HashMap<IComputerAccess,Boolean> computers = new HashMap<IComputerAccess,Boolean>();
	private ITurtleAccess turtle;
	public static final String[] METHOD_NAMES = new String[]{"rename"};
	
	public TileEntityEnclumeCPO() {
	
		super();
	}

	public TileEntityEnclumeCPO(ITurtleAccess turtle) {
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
        if(compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) // si un tag custom name de type string existe
        {
            this.customName = compound.getString("CustomName"); // on le lit
        }

        NBTTagList nbttaglist = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND); // on obtient la liste de tags nommée Items
        this.contents = null; // on réinitialise le tableau
        
        NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(0); // on lit le tag nbt
 
        this.contents = ItemStack.loadItemStackFromNBT(nbttagcompound1); // on lit l'item stack qui se trouve dans le tag
        
        
    }

    @Override
    public void writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        if(this.hasCustomInventoryName()) // s'il y a un nom custom
        {
            compound.setString("CustomName", this.customName); // on le met dans le tag nbt
        }

        NBTTagList nbttaglist = new NBTTagList(); // on créé une nouvelle liste de tags
        if(this.contents != null) // si l'item stack à l'emplacement i du tableau n'est pas null
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound(); // on créé un tag nbt
                nbttagcompound1.setByte("Slot", (byte)0); // on enregistre son emplacement dans le tableau
                this.contents.writeToNBT(nbttagcompound1); // on écrit l'item dans le tag
                nbttaglist.appendTag(nbttagcompound1); // on ajoute le tab à la liste
            }
      
        compound.setTag("Items", nbttaglist); // on enregistre la liste dans le tag nbt
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
	
	private IFluidHandler targetTank;
	private ForgeDirection ForgeDirection;
	public void searchForTank() {
		targetTank = null;
		if (searchForTank2(xCoord+1,yCoord,zCoord,ForgeDirection.WEST)) return;
		if (searchForTank2(xCoord-1,yCoord,zCoord,ForgeDirection.EAST)) return;
		if (searchForTank2(xCoord,yCoord+1,zCoord,ForgeDirection.DOWN)) return;
		if (searchForTank2(xCoord,yCoord-1,zCoord,ForgeDirection.UP)) return;
		if (searchForTank2(xCoord,yCoord,zCoord+1,ForgeDirection.NORTH)) return;
		if (searchForTank2(xCoord,yCoord,zCoord-1,ForgeDirection.SOUTH)) return;
	}
	public boolean searchForTank2(int x,int y,int z,ForgeDirection from) {
		boolean isAir = worldObj.isAirBlock(x, y, z);
		if (!isAir) {
			TileEntity te = worldObj.getTileEntity(x, y, z);
			if (te instanceof IFluidHandler) {
				IFluidHandler test=(IFluidHandler) te;
				FluidTankInfo[] tankInfo=test.getTankInfo(from);
				if (tankInfo != null && tankInfo.length > 0) {
			        if (tankInfo[0].fluid != null) {
			        	System.out.println(tankInfo[0].fluid.getLocalizedName());
			        	if (tankInfo[0].fluid.getLocalizedName().equals("Liquid XP")) {
			        		System.out.println("OK");
			        		System.out.println(tankInfo[0].fluid.amount);
			        		if (tankInfo[0].fluid.amount>=55*20) {
			        			System.out.println("OK");
				        		targetTank = test;
				        		ForgeDirection=from;
				        		return true;
			        		}
			        	}
			        }
				}
			}
		}
		return false;
	}
	
	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws LuaException, InterruptedException {
		switch(method) {
			case 0:				
				if (arguments.length != 1)
					return new Object[]{false,"Manque texte"};
				if (this.contents==null)
					return new Object[]{false,"Pas d'item"};
				
				searchForTank();
				if (targetTank==null) {
					return new Object[]{false,"Pas de tank xp avec 1100"};
				}
				
				FluidStack drained = targetTank.drain(ForgeDirection, 55*20, true);
				
				String renameText=(String) arguments[0];
				rename(renameText);
				return new Object[]{true,""};			
			
				/*
				fluid = tank.getFluid();
				if (fluid != null && fluid.isFluidEqual(new FluidStack(OpenBlocks.Fluids.xpJuice, 0))) {
				getFluidAmount() 
				FluidStack drained = drain(direction, requiredXPJuice, false);
				if (drained != null) {
				drain(int maxDrain, boolean doDrain) 
				*/
				
		}
		return new Object[0];
	}
	@Override
	public void attach(IComputerAccess computer) {
		
	}
	@Override
	public void detach(IComputerAccess computer) {
		
	}
	
	private ItemStack contents = null;
	private String customName;

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slotIndex) {
		return contents;
	}

	@Override
	public ItemStack decrStackSize(int slotIndex, int amount)
	   {
	       if(this.contents != null) // si le contenu dans l'emplacement n'est pas null
	       {
	           ItemStack itemstack;

	           if(this.contents.stackSize <= amount) // si la quantité est inférieur où égale à ce qu'on souhaite retirer
	           {
	               itemstack = this.contents; // la variable itemstack prends la valeur du contenu
	               this.contents = null; // on retire ce qui est dans la variable contents
	               this.markDirty(); // met à jour le tile entity
	               return itemstack; // renvoie itemstack
	           }
	           else // sinon
	           {
	               itemstack = this.contents.splitStack(amount); // la fonction splitStack(quantité) retire dans this.contents[slotIndex] le contenu et le met dans itemstack

	               if(this.contents.stackSize == 0) // au cas où la quantité passe à 0 (ce qui ne devrait pas arriver en temps normal)
	               {
	                   this.contents = null; // on met sur null, ça évite de se retrouver avec des itemstack bugué qui contiennent 0
	               }
	               this.markDirty(); // met à jour le tile entity
	               return itemstack; // renvoie itemstack
	           }
	       }
	       else // sinon si le contenu dans cette emplacement est null
	       {
	           return null; // renvoie null, puisqu'il n'y a rien dans cette emplacement
	       }
	   }

	@Override
	public ItemStack getStackInSlotOnClosing(int slotIndex)
   {
       if(this.contents != null)
       {
           ItemStack itemstack = this.contents;
           this.contents = null;
           return itemstack;
       }
       else
       {
           return null;
       }
   }
	
	public void rename(String nouveauNom)
	{
		if (this.contents != null) {
			NBTTagCompound tag = this.contents.getTagCompound();	
			if( tag == null )
			{
				this.contents.setTagCompound( tag = new NBTTagCompound() );
			}
			NBTTagCompound display = tag.getCompoundTag( "display" );
			tag.setTag( "display", display );
			display.setString("Name",nouveauNom);
		}
	}
	
	 @Override
	   public void setInventorySlotContents(int slotIndex, ItemStack stack)
	   {
		  
	       this.contents = stack; // met l'item stack dans le tableau
	       
	       if(stack != null && stack.stackSize > this.getInventoryStackLimit()) // si la taille de l'item stack dépasse la limite maximum de l'inventaire
	       {
	           stack.stackSize = this.getInventoryStackLimit(); // on le remet sur la limite
	           
	       }

	       this.markDirty(); // met à jour le tile entity
	   }

	 @Override
	   public String getInventoryName()
	   {
	       return this.hasCustomInventoryName() ? this.customName : "tile.houbmod.fenetre";
	   }

	 public void setCustomName(String customName)
	   {
	       this.customName = customName;
	   }
	 
	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	   public int getInventoryStackLimit()
	   {
	       return 64;
	   }

	@Override
	   public boolean isUseableByPlayer(EntityPlayer player)
	   {
	       return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
	   }

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
		
	}

	 @Override
	   public boolean isItemValidForSlot(int slotIndex, ItemStack stack)
	   {
	       return true;
	   }

	@Override
	public int fill(net.minecraftforge.common.util.ForgeDirection from, FluidStack resource, boolean doFill) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public FluidStack drain(net.minecraftforge.common.util.ForgeDirection from, FluidStack resource, boolean doDrain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FluidStack drain(net.minecraftforge.common.util.ForgeDirection from, int maxDrain, boolean doDrain) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canFill(net.minecraftforge.common.util.ForgeDirection from, Fluid fluid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean canDrain(net.minecraftforge.common.util.ForgeDirection from, Fluid fluid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FluidTankInfo[] getTankInfo(net.minecraftforge.common.util.ForgeDirection from) {
		// TODO Auto-generated method stub
		return null;
	}	
	
}
