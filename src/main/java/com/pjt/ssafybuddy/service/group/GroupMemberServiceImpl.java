package com.pjt.ssafybuddy.service.group;

import com.pjt.ssafybuddy.entity.group.GroupMember;
import com.pjt.ssafybuddy.mapper.GroupMemberMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupMemberServiceImpl implements GroupMemberService {

    private final GroupMemberMapper groupMemberMapper;

    @Override
    public int addMember(GroupMember groupMember) {
        return groupMemberMapper.addMember(groupMember);
    }

    @Override
    public int removeMember(int groupId, String userId) {
        return groupMemberMapper.removeMember(groupId, userId);
    }

    @Override
    public List<GroupMember> findMembersByGroupId(int groupId) {
        return groupMemberMapper.findMembersByGroupId(groupId);
    }

    @Override
    public List<Integer> findGroupIdsByUserId(String userId) {
        return groupMemberMapper.findGroupIdsByUserId(userId);
    }


}
