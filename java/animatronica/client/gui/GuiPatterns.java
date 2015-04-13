package animatronica.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import animatronica.Animatronica;

public class GuiPatterns { //extends GuiContainer { // это нам уже не надо, будет статик хелпером
	
//	public static GuiPatterns(GuiContainer cont, Container container) {
//		super(container);
//	}

	public void renderSlot(GuiContainer cont, Slot slot) {
        int x = (cont.width - cont.xSize) / 2;
        int y = (cont.height - cont.ySize) / 2;
        ResourceLocation slotTex = new ResourceLocation(Animatronica.MOD_ID.toLowerCase(), "textures/gui/elements/Slot_base.png");
		Minecraft.getMinecraft().getTextureManager().bindTexture(slotTex);
		cont.drawTexturedModalRect(x+slot.xDisplayPosition-1, y+slot.yDisplayPosition-1, 0, 0, 18, 18);
	}

}
