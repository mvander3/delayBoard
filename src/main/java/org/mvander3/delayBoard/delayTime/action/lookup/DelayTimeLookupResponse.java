package org.mvander3.delayBoard.delayTime.action.lookup;

import java.util.Map;

import org.mvander3.util.time.Time;

public class DelayTimeLookupResponse {

	private Map<String, Time> delayTimes;

    public Map<String, Time> getDelayTimes() {
        return delayTimes;
    }

    public void setDelayTimes(Map<String, Time> delayTimes) {
        this.delayTimes = delayTimes;
    }
	
}
