package top.aidan.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Aidan on 2021/8/29 20:57
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

@Controller
public class NotificationsServlet {
   @GetMapping("/notifications")
   public String notifications(){
      return "notifications";
   }
}
