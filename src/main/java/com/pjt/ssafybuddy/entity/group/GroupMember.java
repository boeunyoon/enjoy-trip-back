package com.pjt.ssafybuddy.entity.group;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GroupMember {
    private int groupId;
    private String userId;
    private boolean isAdmin;

    // Manually added setter for isAdmin
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    // Manually added getter for isAdmin
    public boolean isIsAdmin() {
        return isAdmin;
    }
}
