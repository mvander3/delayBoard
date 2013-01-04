package org.mvander3.delayBoard.delayTime.action.update;

import java.util.Map;

import org.mvander3.util.time.Time;

public class DelayTimeUpdateRequest {

    private Map<String, Time> delayTimes;

    public Map<String, Time> getDelayTimes() {
        return delayTimes;
    }

    public void setDelayTimes(Map<String, Time> delayTimes) {
        this.delayTimes = delayTimes;
    }
    

}
