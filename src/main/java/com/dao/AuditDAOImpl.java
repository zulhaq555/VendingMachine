package com.dao;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;
import com.dto.Audit;


public class AuditDAOImpl implements AuditDAO{
    /**
     * Reads each line in AuditFile which represents a single audit,
     * then marshals data to be used as parameters to instantiate Audit and adds new audit into
     * arraylist
     */
    public void readAuditFile(){
        try {
            FileReader fr = new FileReader("src/main/java/com/files/AuditFile.txt");
            BufferedReader br = new BufferedReader(fr);
            br.readLine();

            Scanner scanner = new Scanner(br);

            //Data marshalling
            while (scanner.hasNextLine()){
                //The data is split into an array, each index will be a parameter of audit
                String[] data = scanner.nextLine().split(",");
                Audit audit = new Audit(data[0], data[1]);
            }

            br.close();
            fr.close();

        }catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Whenever a purchase is made, the item purchased will be added to the arraylist
     * along with its date/time and the arraylist will be written to the file
     */
    public void writeAuditFile(){
        try {
            FileWriter fw = new FileWriter("src/main/java/com/files/AuditFile.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("Item Name | Change Date/Time\n");

            //Data is unmarshalled and written
            for (Audit a: Audit.audits){
                bw.write(a.getItemChange() + "," + a.getChangeDate() + "\n");
            }

            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
