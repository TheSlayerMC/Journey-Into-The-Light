package net.slayer.json;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class JsonGenerator extends JFrame {

    public static final int WIDTH = 860;
    public static final int HEIGHT = 600;

    public JTextField nameField, texNameField;
    public JLabel name, textureName, copy, version;
    public JButton exit, generate;
    public JCheckBox block, tool;
    public BufferedWriter itemModelWriter, blockModelWriter, blockStateWriter;

    public static String MOD_ID = "jitl";
    public static JsonGenerator INSTANCE = new JsonGenerator();

    public static void main(String[] args) { }

    public JsonGenerator() {
        block = new JCheckBox("Is block?");
        tool = new JCheckBox("Render in 3D? (Things like tools)");

        texNameField = new JTextField(10);
        nameField = new JTextField(10);

        exit = new JButton("Exit");
        exit.addActionListener(new ExitHandler());

        generate = new JButton("Generate");
        generate.addActionListener(new ExportHandler());

        name = new JLabel("NAME (The Item or Block name):", SwingConstants.LEFT);
        textureName = new JLabel("TEXTURE NAME: (If same as object name leave blank, auto fills)", SwingConstants.LEFT);
        copy = new JLabel("©The_SlayerMC 2020", SwingConstants.CENTER);
        version = new JLabel("v0.1 for 1.16.3 use", SwingConstants.CENTER);

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(10, 5));

        pane.add(name);
        pane.add(nameField);
        pane.add(textureName);
        pane.add(texNameField);
        pane.add(block);
        pane.add(tool);
        pane.add(generate);
        pane.add(exit);
        pane.add(version);
        pane.add(copy);

        setTitle("Item/Block JSON Generator");
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public class ExportHandler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent arg0) {
            String dir = "./src/main/resources/assets/" + JsonGenerator.MOD_ID + "/";
            String itemModelDir = dir + "models/item/" + nameField.getText() + ".json";
            String blockModelDir = dir + "models/block/" + nameField.getText() + ".json";
            String blockStateDir = dir + "blockstates/" + nameField.getText() + ".json";

            File itemModel = new File(itemModelDir);
            try {
                if(itemModel.exists()) itemModel.delete();
                itemModel.createNewFile();
                itemModelWriter = new BufferedWriter(new FileWriter(itemModel));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if(!tool.isSelected() && block.isSelected()) {
                File blockModel = new File(blockModelDir);
                try {
                    if(blockModel.exists()) blockModel.delete();
                    blockModel.createNewFile();
                    blockModelWriter = new BufferedWriter(new FileWriter(blockModel));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                File blockState = new File(blockStateDir);
                try {
                    if(blockState.exists()) blockState.delete();
                    blockState.createNewFile();
                    blockStateWriter = new BufferedWriter(new FileWriter(blockState));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(texNameField.getText().equals("")) texNameField = nameField;

            if(block.isSelected()) {
                Block.INSTANCE.writeNormalBlock(nameField.getText(), texNameField.getText());
            }

            else if(!block.isSelected() && tool.isSelected())
                Item.INSTANCE.write3DItem(nameField.getText(), texNameField.getText());

            if(!block.isSelected() && !tool.isSelected())
                Item.INSTANCE.writeNormalItem(nameField.getText(), texNameField.getText());

            if(block.isSelected()) {
                itemModelInit();
                blockModelInit();
                blockStateInit();
            }

            if(!block.isSelected()) itemModelInit();
        }
    }

    public void itemModelInit(){
        try {
            itemModelWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void blockModelInit(){
        try {
            blockModelWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void blockStateInit(){
        try {
            blockStateWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToItemModelFile(String text){
        try {
            itemModelWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToBlockModelFile(String text){
        try {
            blockModelWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToBlockStateFile(String text){
        try {
            blockStateWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ExitHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            System.exit(0);
        }
    }
}