package com.pjt.ssafybuddy.service.member;

import com.pjt.ssafybuddy.entity.member.Member;
import com.pjt.ssafybuddy.mapper.MemberMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberMapper memberMapper;

    @Override
    public Member login(String userId, String userPw) throws SQLException {
        Member member = memberMapper.login(userId, userPw);
        if (member == null) {
            return null;
        }
        return member;
    }

    @Override
    public Member findByMemberId(String userId) throws SQLException {
        return memberMapper.findByMemberId(userId);
    }

    @Override
    public int regist(Member member) throws SQLException {
        return memberMapper.insert(member);
    }

    @Override
    public void saveRefreshToken(String userId, String refreshToken) throws SQLException {
        memberMapper.saveRefreshToken(userId, refreshToken);
    }

    @Override
    public void deleteRefreshToken(String userId) throws SQLException {
        memberMapper.deleteRefreshToken(userId);
    }

    @Override
    public String getRefreshToken(String userId) throws SQLException {
        return memberMapper.getRefreshToken(userId);
    }

    @Override
    public List<Member> findAllExceptUser(String userId) throws SQLException {
        return memberMapper.findAllExceptUser(userId);
    }

    @Override
    public List<Member> findMembersNotInGroup(int groupId) throws SQLException {
        return memberMapper.findMembersNotInGroup(groupId);
    }

    @Override
    public List<String> findAllUserIds() throws SQLException {
        return memberMapper.findAllUserIds();
    }

    @Override
    public void updateProfileImage(String userId, String imageUrl) throws SQLException {
        memberMapper.updateProfileImage(userId, imageUrl);
    }
}
