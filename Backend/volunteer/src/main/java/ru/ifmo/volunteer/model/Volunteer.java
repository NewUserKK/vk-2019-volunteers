package ru.ifmo.volunteer.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Volunteer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String patronymic;

    private LocalDate birthday;

    private String socialLink;

    private String educationOrEmployment;

    private String specialization;

    private Boolean hasVolunteeringExperience;

    private String volunteerExperienceDescription;

    private Boolean hasChildrenExperience;

    private String childrenExperienceDescription;

    private String additionalSkills;

    private String expectations;

    private Boolean hasAllergies;

    private Boolean hasFoodPreferences;

    private String size;

    private String photoLink;

    private String sourceVolunteerProgram;

    private String whyInteresting;

    private Boolean notify;

    private String login;

    private String email;

    private String phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getSocialLink() {
        return socialLink;
    }

    public void setSocialLink(String socialLink) {
        this.socialLink = socialLink;
    }

    public String getEducationOrEmployment() {
        return educationOrEmployment;
    }

    public void setEducationOrEmployment(String educationOrEmployment) {
        this.educationOrEmployment = educationOrEmployment;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Boolean getHasVolunteeringExperience() {
        return hasVolunteeringExperience;
    }

    public void setHasVolunteeringExperience(Boolean hasVolunteeringExperience) {
        this.hasVolunteeringExperience = hasVolunteeringExperience;
    }

    public String getVolunteerExperienceDescription() {
        return volunteerExperienceDescription;
    }

    public void setVolunteerExperienceDescription(String volunteerExperienceDescription) {
        this.volunteerExperienceDescription = volunteerExperienceDescription;
    }

    public Boolean getHasChildrenExperience() {
        return hasChildrenExperience;
    }

    public void setHasChildrenExperience(Boolean hasChildrenExperience) {
        this.hasChildrenExperience = hasChildrenExperience;
    }

    public String getChildrenExperienceDescription() {
        return childrenExperienceDescription;
    }

    public void setChildrenExperienceDescription(String childrenExperienceDescription) {
        this.childrenExperienceDescription = childrenExperienceDescription;
    }

    public String getAdditionalSkills() {
        return additionalSkills;
    }

    public void setAdditionalSkills(String additionalSkills) {
        this.additionalSkills = additionalSkills;
    }

    public String getExpectations() {
        return expectations;
    }

    public void setExpectations(String expectations) {
        this.expectations = expectations;
    }

    public Boolean getHasAllergies() {
        return hasAllergies;
    }

    public void setHasAllergies(Boolean hasAllergies) {
        this.hasAllergies = hasAllergies;
    }

    public Boolean getHasFoodPreferences() {
        return hasFoodPreferences;
    }

    public void setHasFoodPreferences(Boolean hasFoodPreferences) {
        this.hasFoodPreferences = hasFoodPreferences;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public String getSourceVolunteerProgram() {
        return sourceVolunteerProgram;
    }

    public void setSourceVolunteerProgram(String sourceVolunteerProgram) {
        this.sourceVolunteerProgram = sourceVolunteerProgram;
    }

    public String getWhyInteresting() {
        return whyInteresting;
    }

    public void setWhyInteresting(String whyInteresting) {
        this.whyInteresting = whyInteresting;
    }

    public Boolean getNotify() {
        return notify;
    }

    public void setNotify(Boolean notify) {
        this.notify = notify;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
