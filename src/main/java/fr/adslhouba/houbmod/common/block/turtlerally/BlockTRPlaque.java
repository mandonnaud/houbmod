package fr.adslhouba.houbmod.common.block.turtlerally;

import java.util.List;

import fr.adslhouba.houbmod.common.HoubMod;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;


public class BlockTRPlaque extends Block {
	public IIcon icote, iroth, irota, rothAnim, rotaAnim;
	public static String[] subBlock = new String[] {"roth", "rota"};
	public BlockTRPlaque()
	{	
		super(Material.iron);
		this.setBlockName("trplaque");
		this.setCreativeTab(HoubMod.HoubModCreativeTabs);
		this.setHardness(3.5F);
		this.setResistance(5.5F);
		
		// worldObj.getBlockPowerInput(this.xCoord, this.yCoord, this.zCoord) 
	}	
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		this.icote = iconRegister.registerIcon(HoubMod.MODID + ":cote");
		this.iroth = iconRegister.registerIcon(HoubMod.MODID + ":rotH");
		this.irota = iconRegister.registerIcon(HoubMod.MODID + ":rotA");
		this.rothAnim = iconRegister.registerIcon(HoubMod.MODID + ":rothanim");
		this.rotaAnim = iconRegister.registerIcon(HoubMod.MODID + ":rotaanim");
	}
	public void getSubBlocks(Item item, CreativeTabs tabs, List list)
	{	
		for(int i = 0; i < subBlock.length; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}
	public boolean canProvidePower()
    {
        return true;
    }
	
	public void onNeighborBlockChange(World world, int x, int y, int z, Block block)
	{
		int metadata2=world.getBlockMetadata(x, y, z);
		if (world.isBlockIndirectlyGettingPowered(x,y,z))
		{			
			if (metadata2<subBlock.length) world.setBlockMetadataWithNotify(x, y, z, metadata2+subBlock.length, 3);
		}
		else
		{
			if (metadata2>=subBlock.length) world.setBlockMetadataWithNotify(x, y, z, metadata2-subBlock.length, 3);
			
		}
	}
	public IIcon getIcon(int side, int metadata)
	{
		switch(metadata)
		{
			case 0: // Metadata 0 donc bloc 1.
				if(side == 1) // Si le c�t� est 1, donc en haut,
				{
					return this.iroth; // on utilise la texture du haut.
				}
				return this.icote; // Dans tous les autres cas, on utilise la texture bloc1.
			case 2:
				if(side == 1) // Si le c�t� est 1, donc en haut,
				{
					return this.rothAnim; // on utilise la texture du haut.
				}
				return this.icote; // Dans tous les autres cas, on utilise la texture bloc1.	
			case 1:
				if(side == 1) // Si le c�t� est 1, donc en haut,
				{
					return this.irota; // on utilise la texture du haut.
				}
				return this.icote; // Dans tous les autres cas, on utilise la texture bloc1.
			case 3:
				if(side == 1) // Si le c�t� est 1, donc en haut,
				{
					return this.rotaAnim; // on utilise la texture du haut.
				}
				return this.icote; // Dans tous les autres cas, on utilise la texture bloc1.			
			default:
				return this.icote; // Dans tous les autres cas, on utilise la texture bloc1.
		}
	}
	public int damageDropped(int metadata)
	{
		if (metadata>=subBlock.length)
		{
			metadata-=2;
		}
		return metadata;
	}
}