package com.springboot.crud.springbootcrud.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.crud.springbootcrud.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

}
