package com.lidmemory.menu;

import com.lidmemory.recipe.RelicWorkbenchRecipe;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class RelicWorkbenchMenu extends AbstractContainerMenu {
    private static final int INPUT_SIZE = 25;

    private final Container inputSlots = new SimpleContainer(INPUT_SIZE) {
        @Override
        public void setChanged() {
            super.setChanged();
            RelicWorkbenchMenu.this.slotsChanged(this);
        }
    };
    private final Container outputSlot = new SimpleContainer(1);
    private final Player player;

    public RelicWorkbenchMenu(int id, Inventory playerInv, MenuType<?> menuType) {
        super(menuType, id);
        this.player = playerInv.player;
        checkContainerSize(inputSlots, INPUT_SIZE);
        checkContainerSize(outputSlot, 1);

        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 5; col++)
                addSlot(new Slot(inputSlots, row * 5 + col, 44 + col * 18, 18 + row * 18));

        addSlot(new Slot(outputSlot, 0, 152, 54) {
            @Override public boolean mayPlace(ItemStack stack) { return false; }
            @Override
            public void onTake(Player p, ItemStack stack) {
                craftOutput();
                super.onTake(p, stack);
            }
        });

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 122 + row * 18));
        for (int col = 0; col < 9; col++)
            addSlot(new Slot(playerInv, col, 8 + col * 18, 180));

        updateOutput();
    }

    @Override
    public void slotsChanged(Container container) {
        super.slotsChanged(container);
        if (container == inputSlots) {
            updateOutput();
        }
    }

    private void updateOutput() {
        ItemStack[][] grid = new ItemStack[5][5];
        for (int r = 0; r < 5; r++)
            for (int c = 0; c < 5; c++)
                grid[r][c] = inputSlots.getItem(r * 5 + c);

        RelicWorkbenchRecipe match = RelicWorkbenchRecipe.findMatch(grid);
        if (match != null) {
            outputSlot.setItem(0, match.getOutput());
        } else {
            outputSlot.setItem(0, ItemStack.EMPTY);
        }
    }

    private void craftOutput() {
        ItemStack[][] grid = new ItemStack[5][5];
        for (int r = 0; r < 5; r++)
            for (int c = 0; c < 5; c++)
                grid[r][c] = inputSlots.getItem(r * 5 + c);

        RelicWorkbenchRecipe match = RelicWorkbenchRecipe.findMatch(grid);
        if (match == null) return;

        ItemStack[][] pattern = match.getPatternItems();
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (!pattern[r][c].isEmpty()) {
                    inputSlots.removeItem(r * 5 + c, 1);
                }
            }
        }
        updateOutput();
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        // Return input items to player
        for (int i = 0; i < INPUT_SIZE; i++) {
            var stack = inputSlots.getItem(i);
            if (!stack.isEmpty()) {
                if (!player.addItem(stack)) {
                    player.drop(stack, false);
                }
                inputSlots.setItem(i, ItemStack.EMPTY);
            }
        }
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        Slot slot = this.slots.get(index);
        if (slot == null || !slot.hasItem()) return ItemStack.EMPTY;

        ItemStack stack = slot.getItem();
        ItemStack copy = stack.copy();

        int outputIndex = 25;
        int invStart = 26;
        int hotbarStart = 53;

        if (index == outputIndex) {
            // Try to move from output to inventory
            if (!this.moveItemStackTo(stack, invStart, hotbarStart + 9, true)) {
                return ItemStack.EMPTY;
            }
            slot.onTake(player, stack);
        } else if (index >= invStart) {
            // Move from inventory to input
            if (!this.moveItemStackTo(stack, 0, INPUT_SIZE, false)) {
                return ItemStack.EMPTY;
            }
        } else if (index < INPUT_SIZE) {
            // Move from input to inventory
            if (!this.moveItemStackTo(stack, invStart, hotbarStart + 9, false)) {
                return ItemStack.EMPTY;
            }
        }

        if (stack.isEmpty()) {
            slot.setByPlayer(ItemStack.EMPTY);
        } else {
            slot.setChanged();
        }

        return copy;
    }

    @Override
    public boolean stillValid(Player player) { return true; }
}