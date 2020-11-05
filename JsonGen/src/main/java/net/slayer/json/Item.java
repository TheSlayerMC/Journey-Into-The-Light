package main.java.net.slayer.json;

public class Item {

    public static Item INSTANCE = new Item();

    public void writeNormalItem(String name, String tex) {
        writeToItemModelFile("{");
        writeToItemModelFile("    \"parent\": \"item/generated\",");
        writeToItemModelFile("    \"textures\": {");
        writeToItemModelFile("        \"layer0\": " + "\"" + JsonGenerator.MOD_ID + ":items/" + tex + "\"");
        writeToItemModelFile("    }");
        writeToItemModelFile("}");
    }

    /**
     * Used for things that are held like tools
     * @param name
     * @param tex
     */
    public void write3DItem(String name, String tex) {
        writeToItemModelFile("{");
        writeToItemModelFile("    \"parent\": \"item/handheld\",");
        writeToItemModelFile("    \"textures\": {");
        writeToItemModelFile("        \"layer0\": " + "\"" + JsonGenerator.MOD_ID + ":items/" + tex + "\"");
        writeToItemModelFile("    }");
        writeToItemModelFile("}");
    }

    private void writeToItemModelFile(String s) {
        JsonGenerator.INSTANCE.writeToItemModelFile(s);
    }
}