package top.aidan.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.aidan.service.EmployeeService;

/**
 * Created by Aidan on 2021/9/1 9:32
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

@Controller
@RequestMapping("/admin")
public class ApproveaccountServlet {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("/approveaccount")
    public String approveaccount(Model model) {
        final Integer STATUS = 0;
        model.addAttribute("emps", employeeService.getAllEmpsByStatus(STATUS));
        return "approveaccount";
    }

    @RequestMapping("/updateStatus")
    public String updateStatus(Integer employeeid, Integer status) {
        int result =employeeService.updateStatus(employeeid,status);
        return "redirect:/admin/approveaccount";
    }
}
