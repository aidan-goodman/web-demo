package top.aidan.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.aidan.bean.Department;
import top.aidan.bean.Employee;
import top.aidan.service.DepartmentService;
import top.aidan.service.EmployeeService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Aidan on 2021/8/28 20:53
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

@Controller
public class LoginServlet {
    @Autowired
    EmployeeService employeeService;

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password, Model model, HttpSession httpSession) {
        Employee employee = employeeService.doLogin(username, password);

        if (employee == null) {
            model.addAttribute("error", "用户名或密码输入错误，登录失败");
            return "forward:/";
        } else {
            if (employee.getStatus() == 0) {
                model.addAttribute("error", "用户待审批");
                return "forward:/";
            } else if (employee.getStatus() == 2) {
                model.addAttribute("error", "用户审批未通过");
                return "forward:/";
            } else {
                httpSession.setAttribute("currentUser", employee);
                return "redirect:/notifications";
            }
        }
    }


    @RequestMapping("/register")
    public String register(Model model) {
        List<Department> departments = departmentService.getAllDeps();
        model.addAttribute("deps", departments);
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(Employee employee, Model model) {
        Integer result = employeeService.doRegister(employee);
        if (result == 1) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "注册失败");
            model.addAttribute("employee", employee);
            return "forward:/register";
        }
    }
}
