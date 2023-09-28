package com.example.loanappbackend.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.loanappbackend.model.Employee;
import com.example.loanappbackend.model.UserLogin;
import com.example.loanappbackend.repository.EmployeeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
    private EmployeeRepository employeeRepository;
	

    @Override
    public Employee saveEmployee(Employee employee) {
    	return employeeRepository.save(employee);
    }    
    
    @Override
    public List<Employee> getAllEmployees() {
    	return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(String id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        Employee recievedEmployee=new Employee();
        recievedEmployee.setEmployeeId(null);
        return recievedEmployee;
    }

    @Override
    public ResponseEntity<Employee> updateEmployeeById(String id, JsonPatch patch) {
        try {
        	Optional<Employee> employee = employeeRepository.findById(id);
        	employee.orElseThrow();	//NoSuchElementException is thrown
        	System.out.println(employee.get().toString());
        	
        	Employee employeePatched = applyPatchToEmployee(patch, employee.get());
        	employeeRepository.save(employeePatched);
        	
        	return ResponseEntity.ok(employeePatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NoSuchElementException e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }        
    }
    
    @Override
    public ResponseEntity<Employee> updateEmployeeById(String id, Employee employee) {
    	try {
    		Optional<Employee> employeeFromRepository = employeeRepository.findById(id);
    		employeeFromRepository.orElseThrow();
    		
    		Employee updatedEmployee = employeeRepository.save(employee);
    		return ResponseEntity.ok(updatedEmployee);    		    		
    	} catch (NoSuchElementException e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }  catch(Exception e) {
        	e.printStackTrace();
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    private Employee applyPatchToEmployee(JsonPatch patch, Employee targetEmployee) throws  JsonPatchException, JsonProcessingException {
    	ObjectMapper objectMapper = JsonMapper.builder() // or different mapper for other format
    			   .addModule(new ParameterNamesModule())
    			   .addModule(new Jdk8Module())
    			   .addModule(new JavaTimeModule())
    			   // and possibly other configuration, modules, then:
    			   .build();
//    	objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

    	System.out.println("object mapper made\n");    
    	System.out.println(targetEmployee.toString());
    	
    	JsonNode patched = patch.apply(objectMapper.convertValue(targetEmployee, JsonNode.class));
    	
    	System.out.println("patched done");
    	Employee updatedEmployee =  objectMapper.treeToValue(patched, Employee.class);
    	System.out.println(updatedEmployee.toString());
    	System.out.println("");
    	return updatedEmployee;    	
    }

    @Override
    public String deleteEmployeeById(String id) {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            return "Employee deleted successfully";
        }
        return "No such employee in the database";
    }
    
    @Override
    public ResponseEntity<?> checkLogin(UserLogin user) {
    	
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	
    	String id = user.getUserId();
    	String password = user.getPassword();
    	Optional<Employee> employee1 = employeeRepository.findById(id);
    	
        if (employee1.isPresent()) {
        	Employee originalEmployee = employee1.get();
        	
        	if (Objects.equals(password, originalEmployee.getPassword())) {
        		map.put("employeeId", originalEmployee.getEmployeeId());
        		map.put("designation", originalEmployee.getDesignation());
        		map.put("department", originalEmployee.getDepartment());
        		map.put("message", "successfull!!");
        		return new ResponseEntity<>(map, HttpStatus.OK);
        	}
        	else {
        		map.put("message", "password mismatch");
        		return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
        	}
        		
        }
        
        map.put("message", "user not found");
        return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        
    }
    
    @Override
    public ResponseEntity<?> checkAdminLogin(UserLogin user){
    	
    	
    	Map<String, Object> map = new LinkedHashMap<String, Object>();
    	String id = user.getUserId();
    	String password = user.getPassword();
    	Optional<Employee> employee1 = employeeRepository.findById(id);
    	
    	if(employee1.isPresent()) {
    		Employee originalEmployee = employee1.get();
    		
    		if(Objects.equals(originalEmployee.getIsAdmin().toLowerCase(),"yes")) {
    			if(Objects.equals(password, originalEmployee.getPassword())) {
    				map.put("employeeId", originalEmployee.getEmployeeId());
            		map.put("designation", originalEmployee.getDesignation());
            		map.put("department", originalEmployee.getDepartment());
            		map.put("message", "successfull!!");
            		return new ResponseEntity<>(map, HttpStatus.OK);
    			}
    			else {
    				map.put("message", "password mismatch");
            		return new ResponseEntity<>(map, HttpStatus.FORBIDDEN);
    			}
    		}
    		else {
    			map.put("message", "You are not an admin");
    			return new ResponseEntity<>(map,HttpStatus.NOT_FOUND);
    		}
    	}
    	 map.put("message", "user not found");
         return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
    }
}
