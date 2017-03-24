package fr.adslhouba.houbmod.common;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition.MovingObjectType;
import net.minecraft.world.World;

public class EntityProjectil extends EntityThrowable {
	
	private int ticksAlive=0;
	private int ticksAliveMax=400;
    
	public EntityProjectil(World par1World, double par2, double par4, double par6)
	{
		super(par1World, par2, par4, par6);
	}
	public EntityProjectil(World par1World, EntityLivingBase par2EntityLivingBase)
	{
		super(par1World, par2EntityLivingBase);
	}
	public EntityProjectil(World par1World)
	{
		super(par1World);
		this.motionX=0;
		this.motionY=0;
		this.motionZ=0;
	}
	
	public void direction(String direct,double vitesse) {
		this.posZ+=0.5;
		vitesse=vitesse*0.3;
		this.ticksAliveMax=(int) Math.round(120/vitesse)+2;
		if (direct.equals("SOUTH")) {
			this.motionZ=vitesse;
			this.motionX=0;
			//this.posX+=0.5;
		} else if (direct.equals("NORTH")) {
			this.motionZ=vitesse*-1;
			this.motionX=0;
			//this.posX+=0.5;
		} else if (direct.equals("WEST")) {			
			this.motionZ=0;
			this.motionX=vitesse*-1;
			//this.posY+=0.5;
		} else if (direct.equals("EAST")) {
			this.motionZ=0;
			this.motionX=vitesse;
			//this.posY+=0.5;
		}
	}
	@Override
	protected void onImpact(MovingObjectPosition mop)
	{
		//this.setDead();
	}
	
    @Override
    public void onUpdate()
    {
    	++this.ticksAlive;
    	if (this.ticksAlive<2) {
    		
    	} else if (this.ticksAlive>this.ticksAliveMax) {
    		this.setDead();
    	} else {  		
    		Block test=this.worldObj.getBlock((int)Math.floor(this.posX),(int)Math.floor(this.posY),(int)Math.floor(this.posZ));    		
    		if (test instanceof BlockAir) {
    			
    		} else {	    			
    			this.setDead();
	    	}
    		if (!this.isDead) {
	    		this.setPosition(this.posX, this.posY, this.posZ);
				this.posX += this.motionX;
			    this.posZ += this.motionZ;
    		}
	  	}
     }
}
