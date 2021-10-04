package top.aidan.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import top.aidan.bean.Employee;
import top.aidan.service.EmployeeService;

import java.util.List;

/**
 * Created by Aidan on 2021/9/3 11:11
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

@Controller
@RequestMapping("/admin")
public class EmployeeServlet {
    public static final Integer PAGE_SIZE = 10;

    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/searchemployees")
    public String getAllEmployees(Employee employee, @RequestParam(defaultValue = "1") Integer page, Model model) {
        List<Employee> employees = employeeService.getAllEmployees(employee, page, PAGE_SIZE);
        Long total = employeeService.getTotal(employee);
        model.addAttribute("emps", employees);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("pageNum", total % PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1);
        return "searchemployees";
    }

    @RequestMapping("/updateEmp")
    public String updateEmp(Integer id) {
        employeeService.updateStatus(id, 2);
        return "redirect:/admin/searchemployees?status=1";
    }
}
