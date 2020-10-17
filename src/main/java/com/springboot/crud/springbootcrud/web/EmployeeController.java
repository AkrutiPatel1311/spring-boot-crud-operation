package com.springboot.crud.springbootcrud.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.crud.springbootcrud.exception.RecordNotFoundException;
import com.springboot.crud.springbootcrud.model.Employee;
import com.springboot.crud.springbootcrud.service.EmployeeService;

@Controller
@RequestMapping("/")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;

	@RequestMapping
	public String getAllEmployees(Model model) {
		List<Employee> list = employeeService.getAllEmployees();

		model.addAttribute("employees", list);
		return "list-employees";
	}

	@RequestMapping(path = { "/edit", "/edit/{id}" })
	public String editEmployeeById(Model model, @PathVariable("id") Optional<Long> id) throws RecordNotFoundException {
		if (id.isPresent()) {
			Employee entity = employeeService.getEmployeeById(id.get());
			model.addAttribute("employee", entity);
		} else {
			model.addAttribute("employee", new Employee());
		}
		return "add-edit-employee";
	}

	@RequestMapping(path = "/delete/{id}")
	public String deleteEmployeeById(Model model, @PathVariable("id") Long id) throws RecordNotFoundException {
		employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createEmployee", method = RequestMethod.POST)
	public String createOrUpdateEmployee(Employee employee) {
		employeeService.createOrUpdateEmployee(employee);
		return "redirect:/";
	}
}
