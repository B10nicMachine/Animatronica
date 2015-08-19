package animatronics.client.gui;

import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.StatCollector;
import animatronics.api.energy.ITEHasEntropy;
import animatronics.client.gui.element.ElementEntropyStorage;
import animatronics.client.gui.element.ElementTextField;

public class GuiSunCollector extends GuiBase {

	public GuiSunCollector(Container container, TileEntity tile) {
		super(container, tile);
		elementList.add(new ElementTextField(30, 6, StatCollector.translateToLocal("tile.blockSunCollector.name"), GuiPatterns.TEXT_COLOR, false));
	}
}
