package fr.adslhouba.houbmod.common.block.cc;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dan200.computercraft.api.filesystem.IMount;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.lua.LuaException;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import dan200.computercraft.api.peripheral.IPeripheralProvider;

import fr.adslhouba.houbmod.common.majApi;
import fr.adslhouba.houbmod.common.entity.EntityProjectil;
import fr.adslhouba.houbmod.utils.houbPeripheral;


public class TileEntityMaj extends TileEntity  implements houbPeripheral {
	public static String publicName = "majForce";
	//private IInventory inventory;
	private String name = "Upgrader";
	public static final String[] METHOD_NAMES = new String[]{};
	private ItemStack[] contents = new ItemStack[27];
	private String customName;
	
	public TileEntityMaj() {
		super();
		//inventory = new InventoryBasic("Upgrader", true, 1);
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

	/*
	 @Override
	    public void readFromNBT(NBTTagCompound compound)
	    {
	        super.readFromNBT(compound); // exécute ce qui se trouve dans la fonction readFromNBT de la classe mère (lecture de la position du tile entity)
	        if(compound.hasKey("CustomName", Constants.NBT.TAG_STRING)) // si un tag custom name de type string existe
	        {
	            this.customName = compound.getString("CustomName"); // on le lit
	        }

	        NBTTagList nbttaglist = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND); // on obtient la liste de tags nommée Items
	        this.contents = new ItemStack[this.getSizeInventory()]; // on réinitialise le tableau
	        for(int i = 0; i < nbttaglist.tagCount(); ++i) // i varie de 0 à la taille la liste
	        {
	            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i); // on lit le tag nbt
	            int j = nbttagcompound1.getByte("Slot") & 255; // on lit à quel slot se trouve l'item stack

	            if(j >= 0 && j < this.contents.length)
	            {
	                this.contents[j] = ItemStack.loadItemStackFromNBT(nbttagcompound1); // on lit l'item stack qui se trouve dans le tag
	            }
	        }
	    }

	    @Override
	    public void writeToNBT(NBTTagCompound compound)
	    {
	        super.writeToNBT(compound); // exécute se qui se trouve dans la fonction writeToNBT de la classe mère (écriture de la position du tile entity)
	        if(this.hasCustomInventoryName()) // s'il y a un nom custom
	        {
	            compound.setString("CustomName", this.customName); // on le met dans le tag nbt
	        }

	        NBTTagList nbttaglist = new NBTTagList(); // on créé une nouvelle liste de tags
	        for(int i = 0; i < this.contents.length; ++i) // i varie de 0 à la taille de notre tableau
	        {
	            if(this.contents[ i] != null) // si l'item stack à l'emplacement i du tableau n'est pas null
	            {
	                NBTTagCompound nbttagcompound1 = new NBTTagCompound(); // on créé un tag nbt
	                nbttagcompound1.setByte("Slot", (byte)i); // on enregistre son emplacement dans le tableau
	                this.contents[ i].writeToNBT(nbttagcompound1); // on écrit l'item dans le tag
	                nbttaglist.appendTag(nbttagcompound1); // on ajoute le tab à la liste
	            }
	        }
	        compound.setTag("Items", nbttaglist); // on enregistre la liste dans le tag nbt
	    }
    
    */
	@Override
	public String getType() {
		return publicName;
	}
	
	@Override
	public boolean equals(IPeripheral other) {
		return other instanceof TileEntityMaj && other == this;
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
		
		return new Object[0];
	}
	@Override
	public void attach(IComputerAccess computer) {
		majApi.init(computer.getID());
	}
	@Override
	public void detach(IComputerAccess computer) {
		
	}
/*
	@Override
	public int getSizeInventory() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ItemStack getStackInSlot(int p_70301_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getInventoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		// TODO Auto-generated method stub
		return false;
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
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		// TODO Auto-generated method stub
		return true;
	}
	*/
}
