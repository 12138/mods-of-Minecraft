package mapnpc.client.gui.component;

import mapnpc.common.misc.Resources;
import mapnpc.common.misc.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonNextPage extends GuiButton {

    private final boolean nextPage;
    
    private int arrowWidth=23;
    private int arrowHight=13;

    public GuiButtonNextPage(int par1, int par2, int par3, boolean par4)
    {
        super(par1, par2, par3, 19, 15, "");
        this.nextPage = par4;
    }


    public void drawButton(Minecraft par1Minecraft, int par2, int par3)
    {
        if (this.drawButton)
        {
            boolean flag = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
            par1Minecraft.renderEngine.bindTexture(Resources.GUI_ARROW);
            int k = 0;
            int l = 91;

            if (flag)
            {
                l += 38;
            }

            if (this.nextPage)
            {
                k += 161;
            }

            Utils.drawTexturedModalRectWisely( this.arrowWidth*2, this.arrowHight*2,this.xPosition, this.yPosition, k, l, this.arrowWidth, this.arrowHight);
        }
    }
}
