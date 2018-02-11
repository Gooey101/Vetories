package com.example.christophergu.vetories;

public class Vetory {

    private String name;
    private String storyTitle;
    private String storyContent;
    private String gender;
    private String dob;
    private String veteranExperience;
    private String email;
    private String phoneNumber;

    public Vetory() {}

    public Vetory(String name, String storyTitle, String storyContent, String gender,
                  String dob, String veteranExperience, String email, String phoneNumber) {
        this.name = name;
        this.storyTitle = storyTitle;
        this.storyContent = storyContent;
        this.gender = gender;
        this.dob = dob;
        this.veteranExperience = veteranExperience;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getName() { return name; }
    public String getStoryTitle() { return storyTitle; }
    public String getStoryContent() { return storyContent; }
    public String getGender() { return gender; }
    public String getDOB() { return dob; }
    public String getVeteranExperience() { return veteranExperience; }
    public String getEmail() { return email; }
    public String getPhone() { return phoneNumber; }
}