package top.aidan.mapper;

import org.apache.ibatis.annotations.Param;
import top.aidan.bean.Employee;

import java.util.List;

/**
 * Created by Aidan on 2021/8/29 20:20
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

public interface EmployeeMapper {

    Employee loadEmpByUsername(String username);

    Integer doRegister(Employee employee);

    List<Employee> getAllEmpsByStatus(Integer status);

    Integer approveaccount(@Param("employeeid") Integer employeeid, @Param("status") Integer status);

    List<Employee> getAllEmployees(@Param("employee") Employee employee, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    Long getTotal(Employee employee);
}
