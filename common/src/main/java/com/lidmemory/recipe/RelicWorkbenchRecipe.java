package com.lidmemory.recipe;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 5×5 shaped recipe for the Relic Workbench.
 * The pattern is a 5×5 grid of character keys; the key map links each character to an Item.
 */
public final class RelicWorkbenchRecipe {
    private static final int GRID = 5;
    private final String[] pattern;
    private final Map<Character, Item> key;
    private final ItemStack output;

    private static final List<RelicWorkbenchRecipe> RECIPES = new ArrayList<>();

    public RelicWorkbenchRecipe(String[] pattern, Map<Character, Item> key, ItemStack output) {
        if (pattern.length != GRID) throw new IllegalArgumentException("Pattern must be 5 rows");
        for (String row : pattern) {
            if (row.length() != GRID) throw new IllegalArgumentException("Each row must be 5 chars");
        }
        this.pattern = pattern;
        this.key = key;
        this.output = output;
    }

    public ItemStack getOutput() {
        return output.copy();
    }

    public ItemStack[][] getPatternItems() {
        ItemStack[][] grid = new ItemStack[GRID][GRID];
        for (int r = 0; r < GRID; r++) {
            for (int c = 0; c < GRID; c++) {
                char ch = pattern[r].charAt(c);
                Item item = key.get(ch);
                grid[r][c] = item != null ? new ItemStack(item) : ItemStack.EMPTY;
            }
        }
        return grid;
    }

    public boolean matches(ItemStack[][] input) {
        for (int r = 0; r < GRID; r++) {
            for (int c = 0; c < GRID; c++) {
                char ch = pattern[r].charAt(c);
                Item expected = key.get(ch);
                ItemStack provided = input[r][c];
                if (expected != null) {
                    if (provided.isEmpty() || !provided.is(expected)) return false;
                } else {
                    if (!provided.isEmpty()) return false;
                }
            }
        }
        return true;
    }

    public static void register(RelicWorkbenchRecipe recipe) {
        RECIPES.add(recipe);
    }

    public static RelicWorkbenchRecipe findMatch(ItemStack[][] input) {
        for (RelicWorkbenchRecipe recipe : RECIPES) {
            if (recipe.matches(input)) return recipe;
        }
        return null;
    }

    public static void initRecipes() {
        // Recipes will be registered here or via external calls
    }
}