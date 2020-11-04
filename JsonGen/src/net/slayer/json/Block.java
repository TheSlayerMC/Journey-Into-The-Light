package net.slayer.json;

public class Block {

    public static Block INSTANCE = new Block();

    public void writeNormalBlock(String name, String tex) {
        writeToBlockStateFile("{");
        writeToBlockStateFile("    \"variants\": {");
        writeToBlockStateFile("        \"\": { \"model\": \"" + JsonGenerator.MOD_ID + ":block/" + name + "\"}");
        writeToBlockStateFile("    }");
        writeToBlockStateFile("}");

        writeToBlockModelFile("{");
        writeToBlockModelFile("    \"parent\": \"block/cube_all\",");
        writeToBlockModelFile("    \"textures\": {");
        writeToBlockModelFile("        \"all\": \"" + JsonGenerator.MOD_ID + ":blocks/" + tex + "\"");
        writeToBlockModelFile("    }");
        writeToBlockModelFile("}");

        writeToItemModelFile("{");
        writeToItemModelFile("    \"parent\": \"" + JsonGenerator.MOD_ID + ":block/"+ name + "\"");
        writeToItemModelFile("}");
    }

    private void writeToBlockStateFile(String s) {
        JsonGenerator.INSTANCE.writeToBlockStateFile(s);
    }

    private void writeToBlockModelFile(String s) {
        JsonGenerator.INSTANCE.writeToBlockModelFile(s);
    }

    private void writeToItemModelFile(String s) {
        JsonGenerator.INSTANCE.writeToItemModelFile(s);
    }
}