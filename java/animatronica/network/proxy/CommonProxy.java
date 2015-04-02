package animatronica.network.proxy;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import animatronica.client.fx.lib.Vector3;
import animatronica.debug.TileEntityDebug;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {
	
	public void preInit(FMLPreInitializationEvent event) {

	}
	
	public void registerAll(){
		registerTileEntity();
	}
	
	public void registerTileEntity(){
		GameRegistry.registerTileEntity(TileEntityDebug.class, "Debug");
	}
	
	public void setExtraReach(EntityLivingBase entity, float reach) {
		if(entity instanceof EntityPlayerMP)
			((EntityPlayerMP) entity).theItemInWorldManager.setBlockReachDistance(Math.max(5, ((EntityPlayerMP) entity).theItemInWorldManager.getBlockReachDistance() + reach));
	}



	public long getWorldElapsedTicks() {
		return MinecraftServer.getServer().worldServers[0].getTotalWorldTime();
	}

	public void setSparkleFXNoClip(boolean noclip) {
		// NO-OP
	}

	public void setSparkleFXCorrupt(boolean corrupt) {
		// NO-OP
	}

	public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m) {
		sparkleFX(world, x, y, z, r, g, b, size, m, false);
	}

	public void sparkleFX(World world, double x, double y, double z, float r, float g, float b, float size, int m, boolean fake) {
		// NO-OP
	}

	public void setWispFXDistanceLimit(boolean limit) {
		// NO-OP
	}

	public void setWispFXDepthTest(boolean depth) {
		// NO-OP
	}

	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size) {
		wispFX(world, x, y, z, r, g, b, size, 0F);
	}

	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity) {
		wispFX(world, x, y, z, r, g, b, size, gravity, 1F);
	}

	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float gravity, float maxAgeMul) {
		wispFX(world, x, y, z, r, g, b, size, 0, -gravity, 0, maxAgeMul);
	}

	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx, float motiony, float motionz) {
		wispFX(world, x, y, z, r, g, b, size, motionx, motiony, motionz, 1F);
	}

	public void wispFX(World world, double x, double y, double z, float r, float g, float b, float size, float motionx, float motiony, float motionz, float maxAgeMul) {
		// NO-OP
	}
}

