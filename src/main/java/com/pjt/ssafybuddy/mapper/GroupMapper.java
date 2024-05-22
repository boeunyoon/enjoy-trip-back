package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.entity.group.Group;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GroupMapper {
    int createGroup(Group group);
    Group findById(int id);
    List<Group> findByOwner(String ownerId);
    List<Group> findAllGroups();
    List<Group> findGroupsByRegion(String region);
    List<Group> findGroupsByType(String type);
    List<Group> findGroupsByRecruiting(boolean isRecruiting);
    List<Group> findGroupsByRegionAndType(@Param("region") String region, @Param("type") String type);
    int updateGroup(Group group);
    int deleteGroup(int id);
    List<Group> findGroupsByIds(@Param("groupIds") List<Integer> groupIds);
    List<Group> findGroupsByIsOnline(@Param("isOnline") String isOnline);
    List<Group> findGroupsByIsOnlineAndTypeAndRegion(@Param("isOnline") String isOnline, @Param("type") String type, @Param("region") String region);
    List<Group> findFilteredGroups(@Param("region") String region, @Param("type") String type, @Param("isOnline") String isOnline);
}
