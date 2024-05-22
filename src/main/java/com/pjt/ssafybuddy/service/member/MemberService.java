package com.pjt.ssafybuddy.service.member;

import com.pjt.ssafybuddy.entity.member.Member;

import java.sql.SQLException;
import java.util.List;

public interface MemberService {
    public Member login(String userId, String userPw) throws SQLException;
    public Member findByMemberId(String userId) throws SQLException;
    public int regist(Member member) throws SQLException;
    public void saveRefreshToken(String userId, String refreshToken) throws SQLException;
    public void deleteRefreshToken(String userId) throws SQLException;
    public String getRefreshToken(String userId) throws SQLException;
    public List<Member> findAllExceptUser(String userId) throws SQLException;
}
