package org.mvander3.delayBoard.employee.repository;

import java.util.Collection;

import org.mvander3.delayBoard.employee.Employee;

public interface EmployeeRepository {

    public Collection<Employee> getAll();
    
    public Employee findByID(long id);
    
    public void deleteByID(long id);
    
    public void save(Employee employee);
    
}
