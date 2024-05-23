package com.pjt.ssafybuddy.service.member;

import com.pjt.ssafybuddy.entity.member.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberService {
    Member login(String userId, String userPw) throws SQLException;
    Member findByMemberId(String userId) throws SQLException;
    int regist(Member member) throws SQLException;
    void saveRefreshToken(String userId, String refreshToken) throws SQLException;
    void deleteRefreshToken(String userId) throws SQLException;
    String getRefreshToken(String userId) throws SQLException;
    List<Member> findAllExceptUser(String userId) throws SQLException;
    List<Member> findMembersNotInGroup(int groupId) throws SQLException;
    List<String> findAllUserIds() throws SQLException;
    void updateProfileImage(String userId, String imageUrl) throws SQLException; // Added method
}
