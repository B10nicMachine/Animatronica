package animatronics.client.gui;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import animatronics.api.energy.ITEHasEntropy;
import animatronics.client.gui.element.ElementEntropyProcessor;
import animatronics.client.gui.element.ElementEntropyStorage;
import animatronics.client.gui.element.ElementTextField;

public class GuiHeatCollapser extends GuiBase {

	public GuiHeatCollapser(Container container, TileEntity tile) {
		super(container, tile);
		elementList.add(new ElementEntropyStorage(6, 6, (ITEHasEntropy)tile));
		elementList.add(new ElementTextField(78, 34, StatCollector.translateToLocal("tooltip.fuel"), 4210752, false));
		elementList.add(new ElementTextField(28, 6, StatCollector.translateToLocal("tile.blockHeatCollapser.name"), 4210752, false));

	}
	
	
	
	
	@Override
	protected void drawGuiContainerForegroundLayer(int x, int y) {
		int xx = x - (width-xSize)/2, yy = y - (height - ySize)/2;
		if(xx > 6 && xx < 24 && yy > 6 && yy < 78){
			drawHoveringText(Arrays.asList(new String[]{"Entropy: " + genericTile.getEntropy() + "/" + genericTile.getMaxEntropy(), (genericTile.getEntropy()*100/genericTile.getMaxEntropy()) + "%"}), xx, yy, Minecraft.getMinecraft().fontRenderer);
		}
		super.drawGuiContainerForegroundLayer(x, y);
	}
}
