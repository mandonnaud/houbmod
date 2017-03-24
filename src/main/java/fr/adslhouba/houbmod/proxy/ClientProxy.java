package fr.adslhouba.houbmod.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import fr.adslhouba.houbmod.common.EntityProjectil;
import fr.adslhouba.houbmod.common.HoubMod;
import net.minecraft.client.renderer.entity.RenderSnowball;

public class ClientProxy extends CommonProxy {
	@Override
	public void registerRender()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityProjectil.class, new RenderSnowball(HoubMod.projectil));
	}
}
