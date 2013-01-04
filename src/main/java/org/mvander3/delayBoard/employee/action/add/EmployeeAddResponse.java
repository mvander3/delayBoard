package org.mvander3.delayBoard.employee.action.add;

public class EmployeeAddResponse {

    private long employeeID;
    private String status;
    
    public long getEmployeeID() {
        return employeeID;
    }
    
    public void setEmployeeID(long employeeID) {
        this.employeeID = employeeID;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
}
