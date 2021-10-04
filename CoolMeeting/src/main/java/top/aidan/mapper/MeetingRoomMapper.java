package top.aidan.mapper;

import top.aidan.bean.MeetingRoom;

import java.util.List;

/**
 * Created by Aidan on 2021/9/5 10:43
 * GitHub: github.com/huaxin0304
 * Blog: aidanblog.top
 */

public interface MeetingRoomMapper {
    List<MeetingRoom> getAllMrs();

    MeetingRoom getMrById(Integer roomId);
}
