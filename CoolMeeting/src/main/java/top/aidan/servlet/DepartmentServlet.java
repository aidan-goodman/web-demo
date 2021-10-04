package top.aidan.servlet;

import com.alibaba.druid.stat.TableStat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import top.aidan.bean.Department;
import top.aidan.service.DepartmentService;

/**
 * Created by Aidan on 2021/8/28 20:33
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

@Controller
@RequestMapping("/admin")
public class DepartmentServlet {

    @Autowired
    DepartmentService departmentService;

    @RequestMapping("/departments")
    public String departments(Model model) {
        model.addAttribute("deps", departmentService.getAllDeps());
        return "departments";

    }

    @RequestMapping("/addDepartment")
    public String addDepartment(String departmentname) {
        departmentService.addDepartment(departmentname);
        return "redirect:/admin/departments";
    }

    @RequestMapping("/deleteDep")
    public String deleteDep(Integer departmentid) {
        departmentService.deleteDep(departmentid);
        return "redirect:/admin/departments";
    }

    @RequestMapping("/updateDep")
    @ResponseBody
    public String updateDep(Integer id, String name) {
        Integer result = departmentService.updateDep(id, name);
        if (result == 1) {
            return "success";
        }
        return "error";
    }
}















