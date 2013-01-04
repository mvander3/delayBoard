package org.mvander3.delayBoard.delayTime.action.lookup;

import java.util.Map;

import org.mvander3.delayBoard.delayTime.repository.DelayTimeRepository;
import org.mvander3.speakEasy.action.Action;
import org.mvander3.speakEasy.request.AbstractTypedRequestHandler;
import org.mvander3.util.time.Time;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.collect.Maps;

@Action(value = "delay.lookup", inputClass = DelayTimeLookupRequest.class, outputClass = DelayTimeLookupResponse.class)
public class DelayTimeLookupRequestHandler extends AbstractTypedRequestHandler<DelayTimeLookupRequest, DelayTimeLookupResponse> {

    private DelayTimeRepository repository;
    
	@Override
    protected DelayTimeLookupResponse processRequestMessage(DelayTimeLookupRequest input) {
	    Map<String, Time> delayTimesByEmployee = getDelayTimeByEmployeeMap(input);
	    DelayTimeLookupResponse response = new DelayTimeLookupResponse();
	    response.setDelayTimes(delayTimesByEmployee);
	    return response; 
    }

    private Map<String, Time> getDelayTimeByEmployeeMap(DelayTimeLookupRequest input) {
	    if(input.getEmployeeID() != null) {
	        Time delayTime = repository.getDelayTimeForEmployee(input.getEmployeeID());
	        Map<String, Time> delayTimesByEmployee = Maps.newHashMapWithExpectedSize(1);
	        delayTimesByEmployee.put(input.getEmployeeID(), delayTime);
	        return delayTimesByEmployee;
	    }
        return repository.getAllDelayTimes();
    }
	
	@Required
	public void setRepository(DelayTimeRepository repository) {
	    this.repository = repository;
	}
	
}
