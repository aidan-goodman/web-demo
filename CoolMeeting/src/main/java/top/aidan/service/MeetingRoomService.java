package top.aidan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.aidan.bean.MeetingRoom;
import top.aidan.mapper.MeetingRoomMapper;

import java.util.List;

/**
 * Created by Aidan on 2021/9/5 10:40
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

@Service
public class MeetingRoomService {
    @Autowired
    MeetingRoomMapper meetingRoomMapper;

    public List<MeetingRoom> getAllMrs() {
        return meetingRoomMapper.getAllMrs();
    }

    public MeetingRoom getMrById(Integer roomId) {
        return meetingRoomMapper.getMrById(roomId);
    }
}
