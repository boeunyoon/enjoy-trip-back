package com.pjt.ssafybuddy.mapper;

import com.pjt.ssafybuddy.entity.member.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface MemberMapper {
    Member login(@Param("id") String userId, @Param("pw") String userPw) throws SQLException;
    Member findByMemberId(String memberId) throws SQLException;
    int insert(Member member) throws SQLException;
    void saveRefreshToken(@Param("id") String userId, @Param("token") String refreshToken) throws SQLException;
    void deleteRefreshToken(String userID) throws SQLException;
    String getRefreshToken(String userID) throws SQLException;
    List<Member> findAllExceptUser(String userId) throws SQLException;
    List<String> findAllUserIds() throws SQLException;
    List<Member> findMembersNotInGroup(@Param("groupId") int groupId) throws SQLException;
    void updateProfileImage(@Param("userId") String userId, @Param("imageUrl") String imageUrl) throws SQLException; // Added method
}
