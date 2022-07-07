package com.service;

import com.dao.AuditDAO;
import com.dao.AuditDAOImpl;

public class AuditService {
    private AuditDAO auditDAO;

    public AuditService(AuditDAO auditDAO){
        this.auditDAO = auditDAO;
    }

    public void readAudits(){
        auditDAO.readAuditFile();
    }

    public void writeAudits(){
        auditDAO.writeAuditFile();
    }
}
