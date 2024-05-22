package com.pjt.ssafybuddy.service.group;

import com.pjt.ssafybuddy.entity.group.GroupMember;

import java.util.List;

public interface GroupMemberService {
    int addMember(GroupMember groupMember);
    int removeMember(int groupId, String userId);
    List<GroupMember> findMembersByGroupId(int groupId);
    List<Integer> findGroupIdsByUserId(String userId);
}
