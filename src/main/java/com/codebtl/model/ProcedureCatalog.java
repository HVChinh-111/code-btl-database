package com.codebtl.model;

import java.math.BigDecimal;

public class ProcedureCatalog {
    private int procedureId;
    private String name;
    private String type;
    private String description;
    private BigDecimal defaultPrice;

    public ProcedureCatalog() {
    }

    public ProcedureCatalog(int procedureId, String name, String type, String description, BigDecimal defaultPrice) {
        this.procedureId = procedureId;
        this.name = name;
        this.type = type;
        this.description = description;
        this.defaultPrice = defaultPrice;
    }

    public int getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(int procedureId) {
        this.procedureId = procedureId;
    }
    
    // Format ID as PRO + 7 digits
    public String getFormattedProcedureId() {
        return String.format("PRO%07d", procedureId);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDefaultPrice() {
        return defaultPrice;
    }

    public void setDefaultPrice(BigDecimal defaultPrice) {
        this.defaultPrice = defaultPrice;
    }
}
