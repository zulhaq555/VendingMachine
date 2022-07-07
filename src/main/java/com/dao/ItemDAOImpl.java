package com.dao;

import java.io.*;
import java.util.Scanner;
import com.dto.Item;

public class ItemDAOImpl implements ItemDAO{

    /**
     * Reads each line in Stock, where each line ia a new item, then creates instances for each
     * item and adds them to the arrayList.
     */
    public void readItemFile() {
        try {
            FileReader fr = new FileReader("src/main/java/com/files/Stock.txt");
            BufferedReader br = new BufferedReader(fr);
            br.readLine();

            Scanner scanner = new Scanner(br);

            //Data marshalling
            while (scanner.hasNextLine()) {
                //The data is split into an array, each index will be a parameter of Item
                String[] data = scanner.nextLine().split(",");
                Item item = new Item(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]));
            }

            br.close();
            fr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * When the user has made a successful purchase, any changes to the item details will be
     * written to the file.
     */
    public void writeItemFile() {
        try {
            FileWriter fw = new FileWriter("src/main/java/com/files/Stock.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Item Name | Item Price | Item Stock\n");

            //Data is unmarshalled and written
            for (Item i : Item.items) {
                bw.write(i.getItemName() + ","
                        + i.getItemPrice() + ","
                        + i.getItemStock() + "\n");
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
