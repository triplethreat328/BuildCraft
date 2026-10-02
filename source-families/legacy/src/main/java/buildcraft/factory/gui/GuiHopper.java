package buildcraft.factory.gui;

import com.mojang.blaze3d.vertex.PoseStack;
import buildcraft.factory.container.ContainerHopper;
import buildcraft.lib.gui.GuiBC8;
import buildcraft.lib.gui.GuiIcon;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

/** Classic four-slot hopper screen. */
public class GuiHopper extends GuiBC8<ContainerHopper> {
    private static final ResourceLocation TEXTURE_BASE =
        new ResourceLocation("buildcraftfactory:textures/gui/chute.png");
    private static final int SIZE_X = 176, SIZE_Y = 153;
    private static final GuiIcon ICON_GUI = new GuiIcon(TEXTURE_BASE, 0, 0, SIZE_X, SIZE_Y);

    public GuiHopper(ContainerHopper container, Inventory inv, Component title) {
        super(container, inv, title);
        imageWidth = SIZE_X;
        imageHeight = SIZE_Y;
    }

    @Override
    protected void drawBackgroundLayer(PoseStack pose, int mouseX, int mouseY, float partialTicks) {
        ICON_GUI.drawAt(getActiveGraphics(), mainGui.rootElement);
    }
}
