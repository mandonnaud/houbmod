package fr.adslhouba.houbmod.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dan200.computercraft.api.ComputerCraftAPI;
import fr.adslhouba.houbmod.proxy.CommonProxy;
import fr.adslhouba.houbmod.utils.houbPeripheral;

@Mod(modid = "houbmod", name = "HoubMod", version = "1.2.2")

public class HoubMod {
	public static final String MODID = "houbmod";

	@Instance("houbmod")
	public static HoubMod instance;
	
	public static CreativeTabs HoubModCreativeTabs = new CreativeTabs("houb_tabs")
    {
        @Override
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(HoubMod.trPlaque);
        }

        @SideOnly(Side.CLIENT)
        public int func_151243_f()
        {
             return 0; // mettez ici votre metadata
        }
    };
    @SidedProxy(clientSide = "fr.adslhouba.houbmod.proxy.ClientProxy", serverSide = "fr.adslhouba.houbmod.proxy.CommonProxy")
    public static CommonProxy proxy;
    
    public static Block trPlaque, trTapis, trTapisDroite, trTapisGauche, genLaser;
	public static Item projectil;
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		
		trPlaque = new BlockTRPlaque(Material.rock).setBlockName("trplaque").setCreativeTab(HoubMod.HoubModCreativeTabs);
		GameRegistry.registerBlock(trPlaque,BlockTRPlaqueItem.class,"trplaque", MODID, new Object[]{BlockTRPlaque.subBlock});
		
		trTapis = new BlockTRTapis().setBlockName("trtapis").setCreativeTab(HoubMod.HoubModCreativeTabs);
		GameRegistry.registerBlock(trTapis,BlockTRTapisItem.class,"trtapis");
		
		trTapisDroite = new BlockTRTapisDroite().setBlockName("trtapisdroite").setCreativeTab(HoubMod.HoubModCreativeTabs);
		GameRegistry.registerBlock(trTapisDroite,BlockTRTapisItem.class,"trtapisdroite");
		
		trTapisGauche = new BlockTRTapisGauche().setBlockName("trtapisgauche").setCreativeTab(HoubMod.HoubModCreativeTabs);
		GameRegistry.registerBlock(trTapisGauche,BlockTRTapisItem.class,"trtapisgauche");
		
		genLaser = new BlockGenLaser().setBlockName("genLaser").setBlockTextureName("houbMod:genlaser").setCreativeTab(HoubMod.HoubModCreativeTabs);
		GameRegistry.registerBlock(genLaser, "genLaser");
		
		projectil = new ItemProjectil().setTextureName(HoubMod.MODID + ":projectil");
		GameRegistry.registerItem(projectil, "projectil");

		GameRegistry.registerTileEntity(TileEntityGenLaser.class, "houbmod:genlaserentity");
		EntityRegistry.registerModEntity(EntityProjectil.class, "tire", 0, this, 32, 1, false);
	}
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		ComputerCraftAPI.registerPeripheralProvider(new houbPeripheral.Provider());
		proxy.registerRender();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		GameRegistry.addRecipe(new ItemStack(trTapis, 9, 0), new Object[] {"XXX", "YYY", "   ", 'X', new ItemStack(Blocks.wool, 1, 1), 'Y',new ItemStack(Blocks.wool, 1, 15)});
		GameRegistry.addRecipe(new ItemStack(trTapisGauche, 9, 0), new Object[] {"YXX", "YYY", "   ", 'X', new ItemStack(Blocks.wool, 1, 1), 'Y',new ItemStack(Blocks.wool, 1, 15)});
		GameRegistry.addRecipe(new ItemStack(trTapisDroite, 9, 0), new Object[] {"YYX", "YYY", "   ", 'X', new ItemStack(Blocks.wool, 1, 1), 'Y',new ItemStack(Blocks.wool, 1, 15)});
		
		GameRegistry.addRecipe(new ItemStack(trPlaque, 9, 0), new Object[] {"YXY", "YYY", "   ", 'X', new ItemStack(Blocks.wool, 1, 11), 'Y',new ItemStack(Blocks.wool, 1, 8)});
		GameRegistry.addRecipe(new ItemStack(trPlaque, 9, 1), new Object[] {"YXY", "YYY", "   ", 'X', new ItemStack(Blocks.wool, 1, 14), 'Y',new ItemStack(Blocks.wool, 1, 8)});
		
		GameRegistry.addRecipe(new ItemStack(trPlaque, 1, 1), new Object[] {"X ", "  ", 'X', new ItemStack(trPlaque, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(trPlaque, 1, 0), new Object[] {"X ", "  ", 'X', new ItemStack(trPlaque, 1, 1)});
		
		GameRegistry.addRecipe(new ItemStack(trTapis, 1, 0), new Object[] {"X ", "  ", 'X', new ItemStack(trTapisDroite, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(trTapisDroite, 1, 0), new Object[] {"X ", "  ", 'X', new ItemStack(trTapisGauche, 1, 0)});
		GameRegistry.addRecipe(new ItemStack(trTapisGauche, 1, 0), new Object[] {"X ", "  ", 'X', new ItemStack(trTapis, 1, 0)});
		
		GameRegistry.addRecipe(new ItemStack(genLaser), new Object[] {
				"YXY",
				"YZY",
				"YVY", 
				'V', Blocks.dispenser,
				'X', new ItemStack(Items.dye, 1, 1),
				'Z', Items.arrow,
				'Y', Items.iron_ingot
		});
	}
}
