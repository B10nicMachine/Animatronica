package animatronics.common.item;

import java.util.List;

import animatronics.Animatronics;
import animatronics.api.energy.IItemAllowsSeeingEntropy;
import animatronics.api.energy.ITERequiresEntropy;
import animatronics.api.energy.ITEStoresEntropy;
import animatronics.api.energy.ITETransfersEntropy;
import animatronics.common.tile.TileEntityPrimary;
import animatronics.utils.config.AnimatronicsConfiguration;
import animatronics.utils.helper.DistanceHelper;
import animatronics.utils.helper.NBTHelper;
import animatronics.utils.item.ItemContainerBase;
import animatronics.utils.misc.Vector3;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

public class ItemBindingStaff extends ItemContainerBase implements IItemAllowsSeeingEntropy {
	
	public ItemBindingStaff(String unlocalizedName, String modId, int maxDamage) {
		super(unlocalizedName, modId, maxDamage);
		this.setCreativeTab(Animatronics.creativeTabAnimatronics);
		this.maxStackSize = 1;
		this.setTextureName("bs");
	}

	@Override
    public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
		if(world.isRemote) return false;
		if(stack.getTagCompound() == null)
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile != null)
			{
				if(tile instanceof ITEStoresEntropy || tile instanceof ITETransfersEntropy)
				{
					NBTHelper.getStackTag(stack).setIntArray("pos", new int[]{x,y,z});
					NBTHelper.getStackTag(stack).setInteger("dim", player.dimension);
					player.addChatMessage(new ChatComponentText("Staff contains machine alias").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
					return true;
				}
			}
		}else
		{
			TileEntity tile = world.getTileEntity(x, y, z);
			if(tile != null && player.isSneaking())
			{
				if(tile instanceof ITERequiresEntropy  || tile instanceof ITETransfersEntropy)
				{
					int[] coords = NBTHelper.getStackTag(stack).getIntArray("pos");
					float distance = new DistanceHelper(new Vector3(x,y,z),new Vector3(coords[0],coords[1],coords[2])).getDistance();
					float maxDist = AnimatronicsConfiguration.maxEnergyDistance;
					if(distance <= maxDist){
						((TileEntityPrimary)tile).storageCoord = new Vector3(coords[0],coords[1],coords[2]);
						world.playSoundAtEntity(player, "random.levelup", 1.0F, 0.01F);
						player.addChatMessage(new ChatComponentText("Machine linked").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
						world.markBlockForUpdate(x, y, z);
						stack.setTagCompound(null);
						return true;
					}
				}
			}
		}
        return false;
    }
	/*
	 * TileEntity tile = world.getTileEntity(x, y, z);
			if(tile != null)
			{
				if(tile instanceof ITERequiresEntropy || tile instanceof ICoordClickable)
				{
					int[] o = NBTHelper.getStackTag(stack).getIntArray("pos");
					float distance = new DistanceHelper(new Coord3D(x,y,z),new Coord3D(o[0],o[1],o[2])).getDistance();
					if(distance <= AnimatronicaConfiguration.maxEnergyDistance)
					{
						TileEntity tile1 = world.getTileEntity(o[0],o[1],o[2]);
						if(tile1 != null && tile1 instanceof ITERequiresEntropy)
						{
							((ITERequiresEntropy)tile1).storagePos = new Coord3D(x+0.5F,y+0.5F,z+0.5F);
							player.addChatMessage(new ChatComponentText("Machine linked").setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN)));
							stack.setTagCompound(null);
							return true;
						}
					}
				}
			}*/
	/*
	@Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
    	if(itemStack.stackTagCompound.hasKey("pos") && itemStack.stackTagCompound.hasKey("dimension") && !world.isRemote)
    	{
    		itemStack.stackTagCompound.removeTag("pos");
    		itemStack.stackTagCompound.removeTag("dimension");
    		world.playSoundAtEntity(entityPlayer, "random.break", 1.0F, 0.01F);
    	}
        return itemStack;
    }*/
    
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4) 
    {
    	if(itemStack.getTagCompound() != null)
    	{

    		int coord[] = NBTHelper.getStackTag(itemStack).getIntArray("pos");
    		list.add("Connected to Machine at:");
	    	list.add(EnumChatFormatting.DARK_RED + "x: "+coord[0]);
	    	list.add(EnumChatFormatting.DARK_GREEN + "y: "+coord[1]);
	    	list.add(EnumChatFormatting.DARK_BLUE + "z: "+coord[2]);
	    	list.add(EnumChatFormatting.GOLD + "dimension: "+NBTHelper.getStackTag(itemStack).getInteger("dimension"));
    	}
    }
    
    public static int[] getCoords(ItemStack stack)
    {
    	return NBTHelper.getStackTag(stack).getIntArray("pos");
    }
    
    
    public boolean createTag(ItemStack stack)
    {
    	if(stack.getTagCompound() == null)
    	{
    		NBTTagCompound tag = new NBTTagCompound();
    		tag.setIntArray("pos", new int[]{0,0,0});
    		return true;
    	}
    	return false;
    }
}    