package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface MemberMapper {
    public Member login(@Param("id") String userId, @Param("pw") String userPw) throws SQLException;
    public Member findByMemberId(String memberId) throws SQLException;
    public int insert(Member member) throws SQLException;
    public void saveRefreshToken(@Param("id") String userId, @Param("token") String refreshToken) throws SQLException;
    public void deleteRefreshToken(String userID) throws SQLException;
    public String getRefreshToken(String userID) throws SQLException;
    public List<Member> findAllExceptUser(String userId) throws SQLException;
}
