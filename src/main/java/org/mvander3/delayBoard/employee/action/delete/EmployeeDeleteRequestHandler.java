package org.mvander3.delayBoard.employee.action.delete;

import org.mvander3.delayBoard.employee.repository.EmployeeRepository;
import org.mvander3.speakEasy.action.Action;
import org.mvander3.speakEasy.request.AbstractTypedRequestHandler;
import org.springframework.beans.factory.annotation.Required;

@Action(value="employee.delete", inputClass=EmployeeDeleteRequest.class, outputClass=EmployeeDeleteResponse.class)
public class EmployeeDeleteRequestHandler extends AbstractTypedRequestHandler<EmployeeDeleteRequest, EmployeeDeleteResponse> {

    private EmployeeRepository repository;
    
    @Override
    protected EmployeeDeleteResponse processRequestMessage(EmployeeDeleteRequest input) {
        if(input.getEmployeeID() == null) {
            throw new IllegalArgumentException("Employee id in request must be specified");
        }
        this.repository.deleteByID(input.getEmployeeID());
        EmployeeDeleteResponse response = new EmployeeDeleteResponse();
        response.setEmployeeID(input.getEmployeeID());
        response.setStatus("deleted");
        return response;
    }
    
    @Required
    public void setRepository(EmployeeRepository repository) {
        this.repository = repository;
    }
    

}
