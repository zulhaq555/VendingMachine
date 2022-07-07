package com.service;

import com.dao.ItemDAO;
import com.dto.Item;

public class ItemService {

    private ItemDAO itemDAO;

    public ItemService(ItemDAO itemDAO){
        this.itemDAO = itemDAO;
    }

    public void readItems(){
        itemDAO.readItemFile();
    }

    public void writeItems(){
        itemDAO.writeItemFile();
    }
}
