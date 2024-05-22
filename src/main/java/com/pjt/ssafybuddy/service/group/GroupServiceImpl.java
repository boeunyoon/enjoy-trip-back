package com.pjt.ssafybuddy.service.group;

import com.pjt.ssafybuddy.entity.group.Group;
import com.pjt.ssafybuddy.entity.group.GroupMember;
import com.pjt.ssafybuddy.mapper.GroupMapper;
import com.pjt.ssafybuddy.service.group.GroupMemberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

    private final GroupMapper groupMapper;
    private final GroupMemberService groupMemberService;

    @Override
    public int createGroup(Group group) {
        try {
            // Create the group
            int result = groupMapper.createGroup(group);
            // Add the owner to the group_members table as an admin
            GroupMember adminMember = new GroupMember();
            adminMember.setGroupId(group.getId());
            adminMember.setUserId(group.getGroupOwner());
            adminMember.setIsAdmin(true);  // Use setIsAdmin instead of setIsAdmin
            groupMemberService.addMember(adminMember);
            return result;
        } catch (Exception e) {
            logger.error("Failed to create group", e);
            throw new RuntimeException("Failed to create group: " + e.getMessage());
        }
    }


    @Override
    public Group findGroupById(int id) {
        return groupMapper.findById(id);
    }

    @Override
    public List<Group> findGroupsByOwner(String ownerId) {
        return groupMapper.findByOwner(ownerId);
    }

    @Override
    public List<Group> findAllGroups() {
        return groupMapper.findAllGroups();
    }

    @Override
    public List<Group> findGroupsByRegion(String region) {
        return groupMapper.findGroupsByRegion(region);
    }

    @Override
    public List<Group> findGroupsByType(String type) {
        return groupMapper.findGroupsByType(type);
    }

    @Override
    public List<Group> findGroupsByRecruiting(boolean isRecruiting) {
        return groupMapper.findGroupsByRecruiting(isRecruiting);
    }

    @Override
    public List<Group> findGroupsByRegionAndType(String region, String type) {
        return groupMapper.findGroupsByRegionAndType(region, type);
    }

    @Override
    public int updateGroup(Group group) {
        logger.info("Updating group with ID: {}", group.getId());
        logger.info("Updated group details: {}", group);
        return groupMapper.updateGroup(group);
    }


    @Override
    public int deleteGroup(int id) {
        return groupMapper.deleteGroup(id);
    }

    @Override
    public List<Group> findGroupsByIds(List<Integer> groupIds) {
        return groupMapper.findGroupsByIds(groupIds);
    }

    @Override
    public List<Group> findGroupsByIsOnline(String isOnline) {
        return groupMapper.findGroupsByIsOnline(isOnline);
    }

    @Override
    public List<Group> findGroupsByIsOnlineAndTypeAndRegion(String isOnline, String type, String region) {
        return groupMapper.findGroupsByIsOnlineAndTypeAndRegion(isOnline, type, region);
    }

    @Override
    public List<Group> findFilteredGroups(String region, String type, String isOnline) {
        return groupMapper.findFilteredGroups(region, type, isOnline);
    }
}
