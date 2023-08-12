
package com.epayRoll.dto;

import java.time.LocalDate;

public class SalaryDTO {

    private LocalDate salaryCreditedDate;
    private String amount;
    private int employeeId;

    public LocalDate getSalaryCreditedDate() {
        return salaryCreditedDate;
    }

    public void setSalaryCreditedDate(LocalDate salaryCreditedDate) {
        this.salaryCreditedDate = salaryCreditedDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
