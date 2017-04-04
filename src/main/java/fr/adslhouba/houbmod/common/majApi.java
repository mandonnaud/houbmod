package fr.adslhouba.houbmod.common;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

import com.google.common.io.Files;

import net.minecraftforge.common.DimensionManager;

public class majApi {
	public static majApi instance;
	public static void init(int idComputer) 
	{
		try {
			/*
			File dossierMinecraft = dossierConfig.getParentFile();
			File dossierMods = new File(dossierMinecraft, "mods");
			File dossierComputercraft = new File(dossierMods, "computercraft");
			if (!dossierComputercraft.exists()) {			
				dossierComputercraft.mkdir();
			}
			File dossierLua = new File(dossierComputercraft, "lua");
			if (!dossierLua.exists()) {			
				dossierLua.mkdir();
			}
			File dossierRom = new File(dossierLua, "rom");
			if (!dossierRom.exists()) {			
				dossierRom.mkdir();
			}
			File dossierApis = new File(dossierRom, "apis");
			if (!dossierApis.exists()) {			
				dossierApis.mkdir();
			}
			File dossierPrograms = new File(dossierApis, "programs");
			if (!dossierPrograms.exists()) {			
				dossierPrograms.mkdir();
			}
			File dossierTurtle = new File(dossierApis, "turtle");
			if (!dossierTurtle.exists()) {			
				dossierTurtle.mkdir();
			}
			
			File dossierType1=new File(dossierSources,"assets");
			if (!dossierType1.exists()) {			
				dossierType1.mkdir();
			}
			dossierType1=new File(dossierType1,"computercraft");
			if (!dossierType1.exists()) {			
				dossierType1.mkdir();
			}
			dossierType1=new File(dossierType1,"lua");if (!dossierType1.exists()) {			
				dossierType1.mkdir();
			}
			dossierType1=new File(dossierType1,"rom");
			if (!dossierType1.exists()) {			
				dossierType1.mkdir();
			}
			dossierType1=new File(dossierType1,"programs");
			if (!dossierType1.exists()) {			
				dossierType1.mkdir();
			}
			*/
			File dossierComputer=new File(DimensionManager.getCurrentSaveRootDirectory(),"computer");
			
			File dossierOrdi = new File(dossierComputer,String.valueOf(idComputer));
			if (!dossierOrdi.exists()) {			
				dossierOrdi.mkdir();
			}
			
			File majFIle = new File(dossierOrdi,"maj");
			
			if (!majFIle.exists()) {
				URL website = new URL("https://raw.githubusercontent.com/Niuttuc/ImperaMiniJeu/master/Common/kitmaj.lua");
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream(majFIle);
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
			}
			/*
			
			//majFIle.createNewFile();
			
			File dossierHoubmod=new File(dossierSources,"assets");
			dossierHoubmod=new File(dossierHoubmod,"houbmod");
			dossierHoubmod=new File(dossierHoubmod,"lua");
			
			File fileLuaMaj=new File(dossierHoubmod,"maj.lua");
			
			Files.copy(fileLuaMaj, majFIle);
			*/
			/*
			dossierType1=new File(dossierType1,"programs");
			
			
			majFIle = new File(dossierPrograms, "maj");
			majFIle.createNewFile();
			
			Files.copy(fileLuaMaj, majFIle);
			*/
			
			              
		} catch (Exception e) {
			System.out.println("\n\nHOUBA\n");
	        e.printStackTrace();
	    }
	}	
}
