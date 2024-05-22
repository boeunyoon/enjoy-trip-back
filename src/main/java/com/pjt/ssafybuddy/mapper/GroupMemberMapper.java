package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.entity.group.GroupMember;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GroupMemberMapper {
    int addMember(GroupMember groupMember);
    int removeMember(@Param("groupId") int groupId, @Param("userId") String userId);
    List<GroupMember> findMembersByGroupId(int groupId);
    List<Integer> findGroupIdsByUserId(String userId);
}
