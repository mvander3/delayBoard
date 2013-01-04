package org.mvander3.delayBoard.employee.repository;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.mvander3.delayBoard.employee.Employee;
import org.springframework.beans.factory.annotation.Required;

import com.google.common.base.Charsets;
import com.google.common.collect.Lists;
import com.google.common.io.Files;

public class FileEmployeeRepository implements EmployeeRepository {
    
    private Logger log = Logger.getLogger(getClass()); 
    
    private static final Charset EMPLOYEE_FILE_CHARSET = Charsets.UTF_8;
    
    private String employeeFileName;
    
    @Override
    public Collection<Employee> getAll() {
        return getEmployeesFromFile();
    }

    @Override
    public Employee findByID(long id) {
        Collection<Employee> employees = getAll();
        for(Employee employee : employees) {
            if(employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    @Override
    public void deleteByID(long id) {
        List<Employee> employees = getEmployeesFromFile();
        ListIterator<Employee> employeeIterator = employees.listIterator();
        while(employeeIterator.hasNext()) {
            Employee employee = employeeIterator.next();
            if(employee.getId().equals(id)) {
                employeeIterator.remove();
            }
        }
        writeEmployeesToFile(employees);
    }

    @Override
    public synchronized void save(Employee employee) {
        List<Employee> employees = getEmployeesFromFile();
        employees.add(employee);
        writeEmployeesToFile(employees);
    }
    
    private synchronized void writeEmployeesToFile(List<Employee> employees) {
        StringBuilder employeeFileContentBuilder = new StringBuilder();
        for(Employee employee : employees) {
            if(employeeFileContentBuilder.length() != 0) {
                employeeFileContentBuilder.append("\n");
            }
            employeeFileContentBuilder.append(employee.toString());
        }
        log.debug("Writing out:\n" + employeeFileContentBuilder.toString());
        try {
            Files.write(employeeFileContentBuilder.toString(), getEmployeeFile(), EMPLOYEE_FILE_CHARSET);
        } catch (IOException e) {
            logAndThrowUnchecked("Could not write new list of employees to file " + this.employeeFileName,e);
        }
    }
    
    private List<Employee> getEmployeesFromFile() {
        List<String> employeeLines = getFileLines();
        List<Employee> employees = Lists.newArrayListWithExpectedSize(employeeLines.size());
        for(String employeeLine : employeeLines) {
            System.out.println("Deserializing employee from line: " + employeeLine);
            employees.add(Employee.fromString(employeeLine));
        }
        return employees;
    }
    
    private synchronized List<String> getFileLines() {
        File employeeFile = getEmployeeFile();
        try {
            return Files.readLines(employeeFile, EMPLOYEE_FILE_CHARSET);
        } catch (IOException e) {
            String message = "Could not read list of employees from file " + this.employeeFileName;
            log.error(message,e);
            throw new RuntimeException(message,e);
        }
    }
    
    private File getEmployeeFile() {
        File employeeFile = new File(this.employeeFileName);
        return employeeFile;
    }
    
    private void logAndThrowUnchecked(String message, Exception e) {
        log.error(message,e);
        throw new RuntimeException(e);
    }
    
    @Required
    public void setEmployeeFileName(String employeeFileName) {
        this.employeeFileName = employeeFileName;
    }
    
}
