package org.mvander3.delayBoard.employee.action.add;

import java.util.Collection;

import org.mvander3.delayBoard.employee.Employee;
import org.mvander3.delayBoard.employee.repository.EmployeeRepository;
import org.mvander3.speakEasy.action.Action;
import org.mvander3.speakEasy.request.AbstractTypedRequestHandler;
import org.springframework.beans.factory.annotation.Required;

@Action(value="employee.add", inputClass=EmployeeAddRequest.class, outputClass=EmployeeAddResponse.class)
public class EmployeeAddRequestHandler extends AbstractTypedRequestHandler<EmployeeAddRequest, EmployeeAddResponse>{

    private EmployeeRepository repository;
    
    @Override
    protected EmployeeAddResponse processRequestMessage(EmployeeAddRequest input) {
        long employeeID = getEmployeeID();
        Employee employee = new Employee(employeeID, input.getEmployeeName());
        repository.save(employee);
        EmployeeAddResponse response = new EmployeeAddResponse();
        response.setEmployeeID(employeeID);
        response.setStatus("saved");
        return response;
    }

    private long getEmployeeID() {
        System.out.println("Getting new employee id");
        synchronized(repository) {
            Collection<Employee> employees = repository.getAll();
            Long maxID = 0L;
            for(Employee employee : employees) {
                System.out.println("Iterating employee " + employee.getId());
                if(maxID < employee.getId()) {
                    System.out.println("Increasing max id to " + employee.getId());
                    maxID = employee.getId();
                }
            }
            long employeeID = maxID + 1;
            System.out.println("Assinging employee id " + employeeID);
            return employeeID;
        }
    }
    
    @Required
    public void setRepository(EmployeeRepository repository) {
        this.repository = repository;
    }

}
