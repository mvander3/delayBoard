package org.mvander3.delayBoard.employee.action.lookup;

import java.util.List;

import org.mvander3.delayBoard.employee.Employee;
import org.mvander3.delayBoard.employee.repository.EmployeeRepository;
import org.mvander3.speakEasy.action.Action;
import org.mvander3.speakEasy.request.AbstractTypedRequestHandler;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.collect.Lists;

@Action(value="employee.lookup", inputClass=EmployeeLookupRequest.class, outputClass=EmployeeLookupResponse.class)
public class EmployeeLookupRequestHandler extends AbstractTypedRequestHandler<EmployeeLookupRequest, EmployeeLookupResponse> {

    private EmployeeRepository repository;
    
    @Override
    protected EmployeeLookupResponse processRequestMessage(EmployeeLookupRequest input) {
        List<Employee> employees = Lists.newArrayList();
        if(input.getEmployeeID() != null) {
            Employee employee = repository.findByID(input.getEmployeeID());
            if(employee != null) {
                employees.add(employee);
            }
        } else {
            employees.addAll(repository.getAll());
        }
        EmployeeLookupResponse response = new EmployeeLookupResponse();
        response.setEmployees(employees);
        return response;
    }

    @Required
    public void setRepository(EmployeeRepository repository) {
        this.repository = repository;
    }
}
