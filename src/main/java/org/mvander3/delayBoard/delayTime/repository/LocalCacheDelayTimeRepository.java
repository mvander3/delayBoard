package org.mvander3.delayBoard.delayTime.repository;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;

import org.mvander3.util.time.Time;

import com.google.common.collect.Maps;

public class LocalCacheDelayTimeRepository implements DelayTimeRepository {

    private Map<String, Time> delayTimesByEmployee = getNewDelayTimesMap();

    @Override
    public Time getDelayTimeForEmployee(String employeeID) {
        Time time = delayTimesByEmployee.get(employeeID);
        if(time == null) {
            return null;
        }
        return time.getCopy();
    }

    @Override
    public Map<String, Time> getAllDelayTimes() {
        Map<String, Time> delayTimesByEmployeeCopy = Maps.newHashMap();
        for(Entry<String, Time> delayTimeEntry : delayTimesByEmployee.entrySet()) {
            delayTimesByEmployeeCopy.put(delayTimeEntry.getKey(), delayTimeEntry.getValue());
        }
        return delayTimesByEmployeeCopy;
    }

    @Override
    public void addDelayTimeForEmployee(String employeeID, Time delayTime) {
        delayTimesByEmployee.put(employeeID, delayTime);
    }

    @Override
    public void addAllDelayTime(Map<String, Time> delayTimesByEmployee) {
        for(Entry<String, Time> delayTimeEntry : delayTimesByEmployee.entrySet()) {
            this.delayTimesByEmployee.put(delayTimeEntry.getKey(), delayTimeEntry.getValue());
        }
    }

    @Override
    public void clearAndUpdateAllDelayTimes(Map<String, Time> delayTimesByEmployee) {
        Map<String, Time> newDelayTimesByEmployee = getNewDelayTimesMap();
        newDelayTimesByEmployee.putAll(delayTimesByEmployee);
        this.delayTimesByEmployee = newDelayTimesByEmployee;
    }

    @Override
    public void clearAllDelayTimes() {
        this.delayTimesByEmployee.clear();
    }

    @Override
    public void clearDelayTimeForEmployee(String employeeID) {
        this.delayTimesByEmployee.remove(employeeID);
    }
    
    private ConcurrentMap<String, Time> getNewDelayTimesMap() {
        return Maps.newConcurrentMap();
    }

}
