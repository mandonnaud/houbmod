package fr.adslhouba.houbmod.common;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import dan200.computercraft.api.ComputerCraftAPI;

import fr.adslhouba.houbmod.common.block.cc.*;
import fr.adslhouba.houbmod.common.block.turtlerally.*;
import fr.adslhouba.houbmod.common.entity.EntityProjectil;
import fr.adslhouba.houbmod.common.items.ItemProjectil;
import fr.adslhouba.houbmod.common.items.cc.*;
import fr.adslhouba.houbmod.proxy.CommonProxy;
import fr.adslhouba.houbmod.utils.houbPeripheral;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = "houbmod", name = "HoubMod", version = "1.3.0")

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
    
    public static Block trPlaque, trTapis, trTapisDroite, trTapisGauche, genLaser, ccMaj;
	public static Item projectil, itemMaj;
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		trPlaque = new BlockTRPlaque();
		GameRegistry.registerBlock(trPlaque,BlockTRPlaqueItem.class,"trplaque", MODID, new Object[]{BlockTRPlaque.subBlock});
		
		trTapis = new BlockTRTapis().setBlockName("trtapis").setCreativeTab(HoubMod.HoubModCreativeTabs).setHardness(3.5F);
		GameRegistry.registerBlock(trTapis,BlockTRTapisItem.class,"trtapis");
		
		trTapisDroite = new BlockTRTapisDroite().setBlockName("trtapisdroite").setCreativeTab(HoubMod.HoubModCreativeTabs).setHardness(3.5F);
		GameRegistry.registerBlock(trTapisDroite,BlockTRTapisItem.class,"trtapisdroite");
		
		trTapisGauche = new BlockTRTapisGauche().setBlockName("trtapisgauche").setCreativeTab(HoubMod.HoubModCreativeTabs).setHardness(3.5F);
		GameRegistry.registerBlock(trTapisGauche,BlockTRTapisItem.class,"trtapisgauche");
		
		genLaser = new BlockGenLaser().setBlockName("genLaser").setBlockTextureName("houbMod:genlaser").setCreativeTab(HoubMod.HoubModCreativeTabs).setHardness(3.5F);
		GameRegistry.registerBlock(genLaser, "genLaser");
		
		projectil = new ItemProjectil().setUnlocalizedName("itemHbProjectil").setTextureName(HoubMod.MODID + ":projectil");
		GameRegistry.registerItem(projectil, "projectil");
		
		ccMaj = new blockMaj().setBlockName("ccUpgrader").setBlockTextureName("houbMod:upgrader").setCreativeTab(HoubMod.HoubModCreativeTabs).setHardness(3.5F);
		GameRegistry.registerBlock(ccMaj, "ccUpgrader");
		
		/*
		itemMaj = new itemMaj().setUnlocalizedName("itemHbMaj").setTextureName(HoubMod.MODID + ":maj").setCreativeTab(HoubMod.HoubModCreativeTabs);
		GameRegistry.registerItem(itemMaj, "CC_maj");
		*/
		
		

		GameRegistry.registerTileEntity(TileEntityGenLaser.class, "houbmod:genlaserentity");
		GameRegistry.registerTileEntity(TileEntityMaj.class, "houbmod:tileEntityMaj");
		EntityRegistry.registerModEntity(EntityProjectil.class, "tire", 0, this, 32, 1, false);
		//event.getModConfigurationDirectory(),
		
		//majApi.init(event.getModConfigurationDirectory());
		
	}
	
	@EventHandler
    public void init(FMLInitializationEvent event)
    {
		ComputerCraftAPI.registerPeripheralProvider(new houbPeripheral.Provider());
		proxy.registerRender();

		//NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandlerMaj());
		
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
		
		GameRegistry.addRecipe(new ItemStack(ccMaj), new Object[] {
				"SSS",
				"SRS",
				"SGS", 
				'R', Items.redstone,
				'G', Items.gold_ingot,
				'S', new ItemStack(Items.dye,1,2)
		});
	}
}
