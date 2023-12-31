package com.everyschool.userservice.api.controller.client;

import com.everyschool.userservice.api.controller.client.response.StudentParentInfo;
import com.everyschool.userservice.api.controller.client.response.UserResponse;
import com.everyschool.userservice.api.controller.client.response.UserInfo;
import com.everyschool.userservice.api.service.user.AccountService;
import com.everyschool.userservice.api.service.user.StudentParentQueryService;
import com.everyschool.userservice.api.service.user.UserQueryService;
import com.everyschool.userservice.utils.TokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MSA 통신용 API
 *
 * @author 임우택
 */
@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/client/v1")
public class UserClientController {

    private final AccountService accountService;
    private final UserQueryService userQueryService;
    private final StudentParentQueryService studentParentQueryService;
    private final TokenUtils tokenUtils;

    /**
     * 토큰으로 회원 정보 조회 API
     *
     * @return 회원 정보
     */
    @GetMapping("/user-info")
    public UserInfo searchUserInfo() {
        log.debug("call UserClientController#searchUserInfo");

        String userKey = tokenUtils.getUserKey();
        log.debug("userKey={}", userKey);

        UserInfo userInfo = userQueryService.searchUserInfo(userKey);
        log.debug("UserInfo={}", userInfo);

        return userInfo;
    }

    /**
     * 고유키로 회원 정보 조회 API
     *
     * @return 회원 정보
     */
    @GetMapping("/user-info/{userKey}")
    public UserInfo searchUserInfo(@PathVariable String userKey) {
        log.debug("call UserClientController#searchUserInfo");

        UserInfo userInfo = userQueryService.searchUserInfo(userKey);
        log.debug("UserInfo={}", userInfo);

        return userInfo;
    }

    /**
     * 학생 PK로 학생 정보 조회 API
     *
     * @param studentIds 학생 PK 리스트
     * @return 학생 정보
     */
    @PostMapping("/student-info")
    public List<UserResponse> searchByStudentIdIn(@RequestBody List<Long> studentIds) {
        log.debug("call UserClientController#searchByStudentIdIn");

        List<UserResponse> response = userQueryService.searchByStudentIdIn(studentIds);
        log.debug("results={}", response);

        return response;
    }

    /**
     * 학급 PK로 가족 관계 조회
     *
     * @param schoolClassId 학급 PK
     * @return 조회된 가족 관계 정보
     */
    @GetMapping("/student-parent/{schoolClassId}")
    public List<StudentParentInfo> searchStudentParentBySchoolClassId(@PathVariable Long schoolClassId) {
        log.debug("call UserClientController#searchStudentParentBySchoolClassId");

        List<StudentParentInfo> infos = studentParentQueryService.searchStudentParentBySchoolClassId(schoolClassId);
        log.debug("results={}", infos);

        return infos;
    }

    @GetMapping("/user-fcm-info/{userKey}")
    public String searchUserFcmTokenInfo(@PathVariable String userKey) {
        log.debug("call UserClientController#searchUserFcmTokenInfo");
        log.debug("userKey={}", userKey);

        String fcmToken = accountService.getFcmToken(userKey);
        log.debug("fcmToken={}", fcmToken);

        return fcmToken;
    }

    @GetMapping("/user-info/{userId}/user-id")
    public UserInfo searchUserInfoById(@PathVariable Long userId) {
        log.debug("call UserClientController#searchUserInfoById");
        log.debug("userId={}", userId);

        UserInfo userInfo = userQueryService.searchUserInfoById(userId);
        log.debug("result={}", userId);

        return userInfo;
    }

    @GetMapping("/user-fcm-info/{userId}/user-id")
    public String searchFcmTokenByUserId(@PathVariable Long userId) {

        String fcmToken = accountService.getFcmTokenById(userId);
        log.debug("fcmToken={}", fcmToken);

        return fcmToken;
    }

    @GetMapping("/user-info/{userId}/user-response")
    public UserResponse searchUserById(@PathVariable(name = "userId") Long userId) {

        UserResponse response = userQueryService.searchUserById(userId);
        log.debug("response={}", response);

        return response;
    }

    @GetMapping("/user/{schoolClassId}/name/{parentId}")
    public String searchUsername(@PathVariable Long schoolClassId, @PathVariable Long parentId) {

        String name = userQueryService.searchUsername(schoolClassId, parentId);
        log.debug("name={}", name);

        return name;
    }

    @PostMapping("/user-info/user-responses")
    public List<UserResponse> searchUserById(@RequestBody List<Long> userIds) {

        List<UserResponse> responses = userQueryService.searchUserInfos(userIds);
        log.debug("result size={}", responses.size());

        return responses;
    }
}
