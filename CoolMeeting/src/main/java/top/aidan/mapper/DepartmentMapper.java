package top.aidan.mapper;

import org.apache.ibatis.annotations.Param;
import top.aidan.bean.Department;

import java.util.List;

/**
 * Created by Aidan on 2021/8/28 19:52
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

public interface DepartmentMapper {

    Department getDepById(Integer id);

    List<Department> getAllDeps();

    Integer addDepartment(String departmentname);

    Integer deleteDep(Integer departmentid);

    Integer updateDep(@Param("departmentid") Integer id, @Param("departmentname") String name);
}
