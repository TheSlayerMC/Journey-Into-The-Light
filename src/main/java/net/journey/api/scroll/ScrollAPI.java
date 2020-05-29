package net.journey.api.scroll;

import net.minecraft.util.ResourceLocation;

import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Code by TimeConqueror
 */
public class ScrollAPI {
    public static Map<String, ScrollCategory> categoryList = new LinkedHashMap<>();

    public static void registerCategory(String categoryName, ResourceLocation background) {
        registerCategory(categoryName, background, 2);
    }

    public static void registerCategory(String categoryName, ResourceLocation background, int categorySize) {
        ScrollCategory ScrollCategory = new ScrollCategory(categoryName, background, categorySize);
        registerCategory(ScrollCategory);
    }

    public static void registerCategory(ScrollCategory ScrollCategory) {
        categoryList.put(ScrollCategory.getCategoryName(), ScrollCategory);
    }

    public static void registerEntry(String existentCategoryName, ScrollEntry ScrollEntry) {
        if (existentCategoryName == null || !categoryList.containsKey(existentCategoryName)) {
            throw new IndexOutOfBoundsException("Attempt to register Tablet Entry \"" + ScrollEntry.getId() + "\" to nonexistent Category \"" + existentCategoryName + "\".\n\tAvailable Category Names: " + categoryList.toString());
        }

        categoryList.get(existentCategoryName).addEntryToCategory(ScrollEntry);
    }

    public static ScrollCategory getCategoryByName(String categoryName) {
        categoryName = categoryName.toUpperCase();
        if (!categoryList.containsKey(categoryName)) {
            throw new IndexOutOfBoundsException("Attempt to get the nonexistent Category \"" + categoryName + "\".\n\tAvailable Category Names: " + categoryList.toString());
        }
        return categoryList.get(categoryName);
    }

    public static ScrollCategory getFirstCategory() {
        Map.Entry<String, ScrollCategory> entry = categoryList.entrySet().iterator().next();
        return entry.getValue();
    }

    public static ScrollCategory getCategoryByIndex(int index) {
        Map.Entry<String, ScrollCategory> category = categoryList.entrySet().stream().skip(index).findFirst().get();
        return category.getValue();
    }

    public static String getCategoryIdByIndex(int index) {
        Map.Entry<String, ScrollCategory> category = categoryList.entrySet().stream().skip(index).findFirst().get();
        return category.getKey();
    }

    public static ScrollEntry getEntryByIndex(ScrollCategory category, int index) {
        Map.Entry<String, ScrollEntry> entry = category.getEntryList().entrySet().stream().skip(index).findFirst().get();
        return entry.getValue();
    }

    public static int getIndexOfCategory(ScrollCategory category) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (category.equals(categoryList.entrySet().stream().skip(i).findFirst().get().getValue())) {
                return i;
            }
        }
        return -1;
    }
}
