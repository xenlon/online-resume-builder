package com.github.xenlon.onlineresumebuilder;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Resume{
    @JsonProperty("random_string")
    private String randomString;
    @JsonProperty("personal_detail")
    private PersonalDetail personalDetail;
    private ArrayList<Education> education;
    private ArrayList<Experience> experience;
    private ArrayList<Project> project;
    private Skill skill;

    @Getter
    static class Skill{
        @JsonProperty("technical_skill")
        private ArrayList<String> technicalSkill;
        @JsonProperty("soft_skill")
        private ArrayList<String> softSkill;
        private ArrayList<String> interest;

        public String toString(ArrayList<String> arr){
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < arr.size(); i++){
                if(i == arr.size()-1) {
                    str.append(arr.get(i));
                }
                else {
                    str.append(arr.get(i)).append(", ");
                }
            }
            return str.toString();
        }
    }

    @Getter
    static class Experience{
        @JsonProperty("company_name")
        private String companyName;
        private String location;
        @JsonProperty("job_role")
        private String jobRole;
        @JsonProperty("duration_start")
        private String durationStart;
        @JsonProperty("duration_end")
        private String durationEnd;
        private ArrayList<String> responsibility;
    }

    @Getter
    static class Education{
        @JsonProperty("college_name")
        private String collegeName;
        @JsonProperty("duration_start")
        private String durationStart;
        @JsonProperty("duration_end")
        private String durationEnd;
        private String percentage;
        @JsonProperty("degree_name")
        private String degreeName;
    }

    @Getter
    static class PersonalDetail{
        @JsonProperty("full_name")
        private String fullName;
        @JsonProperty("phone_number")
        private String phoneNumber;
        @JsonProperty("email_address")
        private String emailAddress;
        @JsonProperty("linkedin_url")
        private String linkedinURL;
        @JsonProperty("github_url")
        private String githubURL;
    }

    @Getter
    static class Project{
        @JsonProperty("project_name")
        private String projectName;
        @JsonProperty("duration_start")
        private String durationStart;
        @JsonProperty("duration_end")
        private String durationEnd;
        private ArrayList<String> responsibility;
    }
}



