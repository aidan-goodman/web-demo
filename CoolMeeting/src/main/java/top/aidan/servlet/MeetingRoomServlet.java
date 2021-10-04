package top.aidan.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.aidan.bean.MeetingRoom;
import top.aidan.service.MeetingRoomService;

import java.util.List;

/**
 * Created by Aidan on 2021/9/5 10:39
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

@Controller
public class MeetingRoomServlet {
    @Autowired
    MeetingRoomService meetingRoomService;

    @RequestMapping("/meetingrooms")
    public String getAllMrs(Model model){
        model.addAttribute("mrs",meetingRoomService.getAllMrs());
        return "meetingrooms";
    }

    @RequestMapping("/roomdetails")
    public String roomDetails(Integer roomId,Model model){
        model.addAttribute("mr",meetingRoomService.getMrById(roomId));
        return "roomdetails";
    }
}
