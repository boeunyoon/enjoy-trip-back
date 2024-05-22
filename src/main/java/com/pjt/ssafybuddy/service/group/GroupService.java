package com.pjt.ssafybuddy.service.group;

import com.pjt.ssafybuddy.entity.group.Group;

import java.util.List;

public interface GroupService {
    int createGroup(Group group);
    Group findGroupById(int id); // Already present
    List<Group> findGroupsByOwner(String ownerId);
    List<Group> findAllGroups();
    List<Group> findGroupsByRegion(String region);
    List<Group> findGroupsByType(String type);
    List<Group> findGroupsByRecruiting(boolean isRecruiting);
    List<Group> findGroupsByRegionAndType(String region, String type);
    int updateGroup(Group group);
    int deleteGroup(int id);
    List<Group> findGroupsByIds(List<Integer> groupIds);
    List<Group> findGroupsByIsOnline(String isOnline);
    List<Group> findGroupsByIsOnlineAndTypeAndRegion(String isOnline, String type, String region);
    List<Group> findFilteredGroups(String region, String type, String isOnline);
}
