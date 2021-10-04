package top.aidan.bean;

/**
 * Created by Aidan on 2021/8/28 19:51
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

public class Department {
   private Integer departmentid;
   private String departmentname;

   public Integer getDepartmentid() {
      return departmentid;
   }

   public void setDepartmentid(Integer departmentid) {
      this.departmentid = departmentid;
   }

   public String getDepartmentname() {
      return departmentname;
   }

   public void setDepartmentname(String departmentname) {
      this.departmentname = departmentname;
   }

   @Override
   public String toString() {
      return "Department{" +
              "departmentid=" + departmentid +
              ", departmentname='" + departmentname + '\'' +
              '}';
   }
}
