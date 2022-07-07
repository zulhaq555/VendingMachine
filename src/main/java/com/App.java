package com;

import com.controller.VendingController;
import com.dao.AuditDAO;
import com.dao.AuditDAOImpl;
import com.dao.ItemDAO;
import com.dao.ItemDAOImpl;
import com.service.AuditService;
import com.service.ItemService;
import com.ui.UserIO;
import com.ui.UserIOImpl;
import com.ui.VendingView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args){

        /*UserIO userIO = new UserIOImpl();
        ItemDAO itemDAO = new ItemDAOImpl();
        AuditDAO auditDAO = new AuditDAOImpl();

        VendingView view = new VendingView(userIO);
        ItemService itemService = new ItemService(itemDAO);
        AuditService auditService = new AuditService(auditDAO);

        VendingController vendingController = new VendingController(itemService, auditService, view);*/

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        VendingController vendingController = applicationContext.getBean("controller", VendingController.class);

        vendingController.startController();
    }
}
