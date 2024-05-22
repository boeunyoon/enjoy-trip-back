package com.pjt.ssafybuddy.controller.group;

import com.pjt.ssafybuddy.entity.group.Group;
import com.pjt.ssafybuddy.entity.group.GroupMember;
import com.pjt.ssafybuddy.service.group.GroupService;
import com.pjt.ssafybuddy.service.group.GroupMemberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/group")
@CrossOrigin("*")
public class GroupController {
    private final GroupService groupService;
    private final GroupMemberService groupMemberService;

    @PostMapping("/create")
    public ResponseEntity<?> createGroup(@RequestBody Group group) {
        try {
            System.out.println("Received Group: " + group);
            groupService.createGroup(group);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/{groupId}/addMember")
    public ResponseEntity<?> addMember(@PathVariable int groupId, @RequestBody Map<String, String> payload) {
        try {
            String userId = payload.get("userId");
            Group group = groupService.findGroupById(groupId);
            if (group != null) {
                GroupMember groupMember = new GroupMember();
                groupMember.setGroupId(groupId);
                groupMember.setUserId(userId);
                groupMemberService.addMember(groupMember);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }


    @DeleteMapping("/{groupId}/removeMember/{userId}")
    public ResponseEntity<?> removeMember(@PathVariable int groupId, @PathVariable String userId, @RequestParam String requesterId) {
        try {
            Group group = groupService.findGroupById(groupId);
            if (group != null && group.getGroupOwner().equals(requesterId)) {
                groupMemberService.removeMember(groupId, userId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @DeleteMapping("/{groupId}")
    public ResponseEntity<?> deleteGroup(@PathVariable int groupId, @RequestParam String requesterId) {
        try {
            Group group = groupService.findGroupById(groupId);
            if (group != null && group.getGroupOwner().equals(requesterId)) {
                groupService.deleteGroup(groupId);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Group>> getGroupsByUserId(@PathVariable String userId) {
        try {
            List<Integer> groupIds = groupMemberService.findGroupIdsByUserId(userId);
            List<Group> groups = groupService.findGroupsByIds(groupIds);
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{groupId}/members")
    public ResponseEntity<List<GroupMember>> getGroupMembers(@PathVariable int groupId) {
        try {
            List<GroupMember> members = groupMemberService.findMembersByGroupId(groupId);
            return new ResponseEntity<>(members, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/region/{region}")
    public ResponseEntity<List<Group>> getGroupsByRegion(@PathVariable String region) {
        try {
            List<Group> groups = groupService.findGroupsByRegion(region);
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Group>> getGroupsByType(@PathVariable String type) {
        try {
            List<Group> groups = groupService.findGroupsByType(type);
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/recruiting/{isRecruiting}")
    public ResponseEntity<List<Group>> getGroupsByRecruiting(@PathVariable boolean isRecruiting) {
        try {
            List<Group> groups = groupService.findGroupsByRecruiting(isRecruiting);
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/region/{region}/type/{type}")
    public ResponseEntity<List<Group>> getGroupsByRegionAndType(@PathVariable String region, @PathVariable String type) {
        try {
            List<Group> groups = groupService.findGroupsByRegionAndType(region, type);
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Group>> getAllGroups() {
        try {
            List<Group> groups = groupService.findAllGroups();
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @PutMapping("/{groupId}")
    public ResponseEntity<?> updateGroup(@PathVariable int groupId, @RequestBody Group group, @RequestParam String requesterId) {
        try {
            Group existingGroup = groupService.findGroupById(groupId);
            if (existingGroup != null && existingGroup.getGroupOwner().equals(requesterId)) {
                // Update the fields of the existing group with the new values
                existingGroup.setGroupName(group.getGroupName());
                existingGroup.setType(group.getType());
                existingGroup.setRegion(group.getRegion());
                existingGroup.setIsOnline(group.getIsOnline());
                existingGroup.setRecruitMaxNumber(group.getRecruitMaxNumber());
                existingGroup.setContent(group.getContent());

                // Save the updated group
                groupService.updateGroup(existingGroup);
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/groups")
    public ResponseEntity<List<Group>> getFilteredGroups(
            @RequestParam(required = false) String region,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String isOnline) {
        try {
            List<Group> groups = groupService.findFilteredGroups(region, type, isOnline);
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/isOnline/{isOnline}")
    public ResponseEntity<List<Group>> getGroupsByIsOnline(@PathVariable String isOnline) {
        try {
            List<Group> groups = groupService.findGroupsByIsOnline(isOnline);
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Group>> getGroupsByIsOnlineAndTypeAndRegion(
            @RequestParam(required = false) String isOnline,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String region) {
        try {
            List<Group> groups = groupService.findGroupsByIsOnlineAndTypeAndRegion(isOnline, type, region);
            return new ResponseEntity<>(groups, HttpStatus.OK);
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<?> getGroupById(@PathVariable int groupId) {
        try {
            Group group = groupService.findGroupById(groupId);
            if (group != null) {
                return new ResponseEntity<>(group, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Group not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    private <T> ResponseEntity<T> exceptionHandling(Exception e) {
        e.printStackTrace();
        return new ResponseEntity("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
