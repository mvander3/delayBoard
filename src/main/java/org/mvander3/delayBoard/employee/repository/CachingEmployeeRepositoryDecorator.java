package org.mvander3.delayBoard.employee.repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.mvander3.delayBoard.employee.Employee;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class CachingEmployeeRepositoryDecorator implements EmployeeRepository {

    private EmployeeRepository delegate;
    
    private Map<Long, Employee> cachedEmployees = Maps.newConcurrentMap();
    private AtomicBoolean cacheIsDirty = new AtomicBoolean(true);
    
    @Override
    public Collection<Employee> getAll() {
        verifyCacheLoaded();
        return Sets.newHashSet(cachedEmployees.values());
    }

    @Override
    public Employee findByID(long id) {
        verifyCacheLoaded();
        return cachedEmployees.get(id);
    }

    @Override
    public void deleteByID(long id) {
        delegate.deleteByID(id);
        this.cacheIsDirty.set(true);
    }

    @Override
    public void save(Employee employee) {
        delegate.save(employee);
        this.cacheIsDirty.set(true);
    }
    
    private synchronized void reloadCache() {
        Collection<Employee> employees = delegate.getAll();
        Map<Long, Employee> newEmployeeCache = Maps.newConcurrentMap();
        for(Employee employee : employees) {
            newEmployeeCache.put(employee.getId(), employee);
        }
        this.cachedEmployees = newEmployeeCache;
        this.cacheIsDirty.set(false);
    }
    
    private void verifyCacheLoaded() {
        if(this.cacheIsDirty.get()) {
            reloadCache();
        }
    }
    
    @Required
    public void setDelegate(EmployeeRepository delegate) {
        this.delegate = delegate;
    }
    
}
