package top.aidan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.aidan.bean.Employee;
import top.aidan.mapper.EmployeeMapper;

import java.util.List;

/**
 * Created by Aidan on 2021/8/29 20:26
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public Employee doLogin(String username, String password) {
        Employee employee = employeeMapper.loadEmpByUsername(username);
        if (employee == null || !employee.getPassword().equals(password)) {
            return null;
        }
        return employee;
    }

    public Integer doRegister(Employee employee) {
        Employee emp = employeeMapper.loadEmpByUsername(employee.getUsername());
        if (emp != null) {
            return -1;
        }
        employee.setRole(1);
        employee.setStatus(0);
        return employeeMapper.doRegister(employee);
    }

    public List<Employee> getAllEmpsByStatus(Integer status) {
        List<Employee> employees = employeeMapper.getAllEmpsByStatus(status);
        return employees;
    }

    public Integer updateStatus(Integer employeeid, Integer status) {
        return employeeMapper.approveaccount(employeeid, status);
    }

    public List<Employee> getAllEmployees(Employee employee, Integer page, Integer pageSize) {
        page = (page - 1) * pageSize;
        return employeeMapper.getAllEmployees(employee, page, pageSize);
    }

    public Long getTotal(Employee employee) {
        return employeeMapper.getTotal(employee);
    }
}
