package org.mvander3.delayBoard.employee.action.lookup;

import java.util.List;

import org.mvander3.delayBoard.employee.Employee;

public class EmployeeLookupResponse {

    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
}
