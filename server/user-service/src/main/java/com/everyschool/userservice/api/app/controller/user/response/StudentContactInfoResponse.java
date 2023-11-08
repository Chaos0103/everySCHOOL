package com.everyschool.userservice.api.app.controller.user.response;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudentContactInfoResponse {

    private String studentKey;
    private String name;
    private Integer studentNumber;
    private List<Parent> parents = new ArrayList<>();

    @Builder
    public StudentContactInfoResponse(String studentKey, String name, Integer studentNumber) {
        this.studentKey = studentKey;
        this.name = name;
        this.studentNumber = studentNumber;
    }

    @Data
    public static class Parent {

        private String parentKey;
        private String name;
        private String parentType;

        @Builder
        public Parent(String parentKey, String name, String parentType) {
            this.parentKey = parentKey;
            this.name = name;
            this.parentType = parentType;
        }
    }
}
