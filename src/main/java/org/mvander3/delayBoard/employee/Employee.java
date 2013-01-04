package org.mvander3.delayBoard.employee;

public class Employee implements Comparable<Employee> {
    
    private static final String SERIALIZED_FIELD_SEPARATOR = "::";
    
    private Long id;
    private String name;
    
    public Employee(long id, String name) {
        this(name);
        if(id < 0) {
            throw new IllegalArgumentException("If specified, parameter 'id' must be positive");
        }
        this.id = id;
    }
    
    public Employee(String name) {
        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Parameter 'name' must be non-null and non-empty");
        }
        this.name = name;
    }
    
    public static Employee fromString(String employeeString) {
        return fromStringInternal(employeeString);
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    private static Employee fromStringInternal(String employeeString) {
        String[] employeeFieldParts = employeeString.split(SERIALIZED_FIELD_SEPARATOR);
        int numberOfFields = 2;
        if(employeeFieldParts.length != (numberOfFields + 1)) {
            throw new IllegalArgumentException("Employee string must contain exactly " + (numberOfFields + 1) 
                + " parts separated by " + SERIALIZED_FIELD_SEPARATOR);
        }
        if(! employeeFieldParts[0].equals("Employee")) {
            throw new IllegalArgumentException("Employee string must start with the substring 'Employee'");
        }
        Long id = Long.valueOf(employeeFieldParts[1]);
        String name = employeeFieldParts[2];
        return new Employee(id, name);
    }
    
    @Override
    public String toString() {
        return "Employee" + SERIALIZED_FIELD_SEPARATOR + this.id + SERIALIZED_FIELD_SEPARATOR + this.name;   
    }
    
    @Override
    public int compareTo(Employee other) {
        if(other == null) {
            return -1;
        }
        if(other.getId() == null && this.getId() != null) {
            return -1;  
        } 
        if(other.getId() != null && this.getId() == null) {
            return 1;
        }
        int idCompareTo = this.getId().compareTo(other.getId());
        if(idCompareTo != 0) {
            return idCompareTo;
        }
        if(other.getName() == null && this.getName() != null) {
            return -1;  
        } 
        if(other.getName() != null && this.getName() == null) {
            return 1;
        }
        return this.getName().compareTo(other.getName());
    }
    
}
