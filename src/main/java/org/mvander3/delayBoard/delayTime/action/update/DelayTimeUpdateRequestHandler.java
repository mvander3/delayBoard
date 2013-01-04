package org.mvander3.delayBoard.delayTime.action.update;

import org.mvander3.delayBoard.delayTime.repository.DelayTimeRepository;
import org.mvander3.speakEasy.action.Action;
import org.mvander3.speakEasy.request.AbstractTypedRequestHandler;
import org.springframework.beans.factory.annotation.Required;

@Action(value = "delay.update", inputClass=DelayTimeUpdateRequest.class, outputClass=DelayTimeUpdateResponse.class)
public class DelayTimeUpdateRequestHandler extends AbstractTypedRequestHandler<DelayTimeUpdateRequest, DelayTimeUpdateResponse> {

    private DelayTimeRepository repository;
    
    @Override
    protected DelayTimeUpdateResponse processRequestMessage(DelayTimeUpdateRequest input) {
        repository.clearAndUpdateAllDelayTimes(input.getDelayTimes());
        
        DelayTimeUpdateResponse response = new DelayTimeUpdateResponse();
        response.setStatus("updated");
        return response;
    }

    @Required
    public void setRepository(DelayTimeRepository repository) {
        this.repository = repository;
    }
    
}
