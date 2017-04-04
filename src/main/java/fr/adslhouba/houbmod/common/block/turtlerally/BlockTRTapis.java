package fr.adslhouba.houbmod.common.block.turtlerally;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import fr.adslhouba.houbmod.common.HoubMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class BlockTRTapis extends Block
{
    public IIcon tapisCote;
    public IIcon[] IIconTab = new IIcon[8];

    public BlockTRTapis()
    {
    	super(Material.iron);
    }

    public int damageDropped(int metadata)
    {
        return 0;
    }

    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
        list.add(new ItemStack(item, 1, 0));
    }

    public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.tapisCote = iconRegister.registerIcon(HoubMod.MODID + ":tapis/cote");
        for (int i=0; i<8; i++) {
        	this.IIconTab[i]=iconRegister.registerIcon(HoubMod.MODID + ":tapis/droit"+i);
        }
    }
    public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
    	int metadataReel=world.getBlockMetadata(x, y, z);
		if (world.isBlockIndirectlyGettingPowered(x,y,z))
		{			
			System.out.println(x+" "+y+" "+z);
			System.out.println("Change de "+metadataReel+" a "+(metadataReel+4));
			if (metadataReel<4) world.setBlockMetadataWithNotify(x, y, z, metadataReel+4, 3);
		}
		else
		{
			if (metadataReel>=4) world.setBlockMetadataWithNotify(x, y, z, metadataReel-4, 3);
		}
	}
    public IIcon getIcon(int side, int metadata)
    {
        if(side == 1)
        {   
        	return this.IIconTab[metadata];
        }
        return this.tapisCote;
    }
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack)
    {
    	int direction = MathHelper.floor_double((double)(living.rotationYaw * 4.0F / 360.0F) + 2.5D) & 3; 
    	
    	if (world.isBlockIndirectlyGettingPowered(x,y,z)) {
    		direction+=4;
    	}
     
    	world.setBlockMetadataWithNotify(x, y, z, direction, 2);
    }
}