package fr.adslhouba.houbmod.common.block.turtlerally;

import fr.adslhouba.houbmod.common.HoubMod;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockTRTapisGauche  extends BlockTRTapis {
	public void registerBlockIcons(IIconRegister iconRegister)
    {
        this.tapisCote = iconRegister.registerIcon(HoubMod.MODID + ":tapis/cote");
        for (int i=0; i<8; i++) {
        	this.IIconTab[i]=iconRegister.registerIcon(HoubMod.MODID + ":tapis/gauche"+i);
        }
    }
}