package com.lidmemory.fabric;

import com.lidmemory.menu.RelicWorkbenchMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class RelicWorkbenchScreen extends AbstractContainerScreen<RelicWorkbenchMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation("lidmemory", "textures/gui/relic_workbench.png");
    private static final int PANEL_W = 202;
    private static final int PANEL_H = 204;

    public RelicWorkbenchScreen(RelicWorkbenchMenu menu, Inventory inv, Component title) {
        super(menu, inv, title);
        this.imageWidth = PANEL_W;
        this.imageHeight = PANEL_H;
    }

    @Override
    protected void renderBg(GuiGraphics g, float f, int mx, int my) {
        int x = (width - PANEL_W) / 2;
        int y = (height - PANEL_H) / 2;
        
        // Draw the transparent base texture (does nothing)
        g.blit(TEXTURE, x, y, 0, 0, PANEL_W, PANEL_H);

        // Draw panel background
        g.fill(x + 4, y + 4, x + 198, y + 200, 0xFF8B7355);

        // Title bar area
        g.fill(x + 4, y + 4, x + 198, y + 18, 0xFFA0835C);

        // 5x5 input slots background (44,18 to 44+90=134, 18+90=108)
        g.fill(x + 43, y + 17, x + 135, y + 109, 0xFF6B5B3E);

        // Output slot background
        g.fill(x + 151, y + 53, x + 171, y + 73, 0xFF6B5B3E);

        // Player inventory background (8,122 to 8+162=170, 122+54=176)
        g.fill(x + 7, y + 121, x + 171, y + 177, 0xFF7A6A48);

        // Hotbar background
        g.fill(x + 7, y + 179, x + 171, y + 198, 0xFF7A6A48);

        // Draw 5x5 grid lines on input area
        for (int row = 0; row <= 5; row++) {
            int ly = y + 18 + row * 18;
            g.fill(x + 44, ly, x + 134, ly + 1, 0xFF4A3C2A);
        }
        for (int col = 0; col <= 5; col++) {
            int lx = x + 44 + col * 18;
            g.fill(lx, y + 18, lx + 1, y + 108, 0xFF4A3C2A);
        }

        // Output slot border
        g.fill(x + 152, y + 54, x + 170, y + 55, 0xFF4A3C2A);
        g.fill(x + 152, y + 72, x + 170, y + 73, 0xFF4A3C2A);
        g.fill(x + 152, y + 54, x + 153, y + 73, 0xFF4A3C2A);
        g.fill(x + 169, y + 54, x + 170, y + 73, 0xFF4A3C2A);
    }

    @Override
    public void render(GuiGraphics g, int mx, int my, float delta) {
        renderBackground(g);
        super.render(g, mx, my, delta);
        renderTooltip(g, mx, my);
    }

    @Override
    protected void renderLabels(GuiGraphics g, int mx, int my) {
        g.drawString(font, title, 8, 6, 0x404040, false);
        g.drawString(font, playerInventoryTitle, 8, 110, 0x404040, false);
    }
}
