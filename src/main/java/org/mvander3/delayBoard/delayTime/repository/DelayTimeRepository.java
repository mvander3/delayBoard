package org.mvander3.delayBoard.delayTime.repository;

import java.util.Map;

import org.mvander3.util.time.Time;

public interface DelayTimeRepository {

    public Time getDelayTimeForEmployee(String employeeID);
    
    public Map<String, Time> getAllDelayTimes();
    
    public void addDelayTimeForEmployee(String employeeID, Time delayTime);
    
    public void addAllDelayTime(Map<String, Time> delayTimesByEmployee);
    
    public void clearAndUpdateAllDelayTimes(Map<String, Time> delayTimesByEmployee);
    
    public void clearAllDelayTimes();
    
    public void clearDelayTimeForEmployee(String employeeID);
    
}
