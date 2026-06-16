package com.lidmemory.menu;

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

    private final Container inputSlots = new SimpleContainer(INPUT_SIZE);
    private final Container outputSlot = new SimpleContainer(1);

    public RelicWorkbenchMenu(int id, Inventory playerInv, MenuType<?> menuType) {
        super(menuType, id);
        checkContainerSize(inputSlots, INPUT_SIZE);
        checkContainerSize(outputSlot, 1);

        for (int row = 0; row < 5; row++)
            for (int col = 0; col < 5; col++)
                addSlot(new Slot(inputSlots, row * 5 + col, 44 + col * 18, 18 + row * 18));

        addSlot(new Slot(outputSlot, 0, 152, 54) {
            @Override public boolean mayPlace(ItemStack stack) { return false; }
        });

        for (int row = 0; row < 3; row++)
            for (int col = 0; col < 9; col++)
                addSlot(new Slot(playerInv, col + row * 9 + 9, 8 + col * 18, 122 + row * 18));
        for (int col = 0; col < 9; col++)
            addSlot(new Slot(playerInv, col, 8 + col * 18, 180));
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
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
    public ItemStack quickMoveStack(Player player, int index) { return ItemStack.EMPTY; }

    @Override
    public boolean stillValid(Player player) { return true; }
}
