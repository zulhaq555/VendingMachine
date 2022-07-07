package com.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Zain Ul-Haq
 * Audit DAO
 * The purpose of this class is to create audit abjects which will track any changes made
 * to any items, in this case their stock field, and record the changes.
 */
public class Audit {

    private String itemChange;
    private Date changeDate;
    public static ArrayList<Audit> audits = new ArrayList<>();

    /**
     * The main constructor for Audit for new Audits
     * @param itemName The name of the item whose stock has changed
     */
    public Audit(String itemName){
        this.changeDate = new Date();
        this.itemChange = itemName;
        audits.add(this);
    }

    /**
     * The constructor for instantiating existing audits read from file.
     * @param itemName Name of item changed
     * @param date Date/Time which item was changed
     * @throws ParseException Required when parsing 'date' String into Date type
     */
    public Audit(String itemName, String date) throws ParseException {
        this.itemChange = itemName;
        //Creates a format for parsing
        SimpleDateFormat format = new SimpleDateFormat("E MMM dd HH:mm:ss z y");
        this.changeDate = format.parse(date);
        audits.add(this);
    }

    /**
     * @return The name of the item purchased
     */
    public String getItemChange() {
        return itemChange;
    }

    /**
     * @return The date/time of the item purchase
     */
    public String getChangeDate() {
        return changeDate.toString();
    }
}
