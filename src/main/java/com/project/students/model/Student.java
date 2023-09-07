package com.project.students.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;

@Entity
public class Student {
    
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY)
    private long id;

    @Pattern(regexp = "^\\STDN\\d{5}", message = "Should be Start with 'STDN' followed by 5 digits" )
    private String studentId;

    @Pattern(regexp = "^[M|F]{1}$", message ="Must be M or F")
    private String gender;
    
    private String nationality;
    private String placeofBirth;
    private String stageId;
    private String gradeId;
    private String sectionId;
    private String topic;
    private String semester;
    private String relation;
    private long raisedhands;
    private long visitedResources;
    private long announcementsView;
    private long discussion;
    private String parentAnsweringSurvey;
    private String parentSchoolSatisfaction;
    private String studentAbsenceDays;
    private long studentMarks;

    private String classIn;

    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getNationality() {
        return nationality;
    }
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    public String getPlaceofBirth() {
        return placeofBirth;
    }
    public void setPlaceofBirth(String placeofBirth) {
        this.placeofBirth = placeofBirth;
    }
    public String getStageId() {
        return stageId;
    }
    public void setStageId(String stageId) {
        this.stageId = stageId;
    }
    public String getGradeId() {
        return gradeId;
    }
    public void setGradeId(String gradeId) {
        this.gradeId = gradeId;
    }
    public String getSectionId() {
        return sectionId;
    }
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }
    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String getRelation() {
        return relation;
    }
    public void setRelation(String relation) {
        this.relation = relation;
    }
    public long getRaisedhands() {
        return raisedhands;
    }
    public void setRaisedhands(long raisedhands) {
        this.raisedhands = raisedhands;
    }
    public long getVisitedResources() {
        return visitedResources;
    }
    public void setVisitedResources(long visitedResources) {
        this.visitedResources = visitedResources;
    }
    public long getAnnouncementsView() {
        return announcementsView;
    }
    public void setAnnouncementsView(long announcementsView) {
        this.announcementsView = announcementsView;
    }
    public long getDiscussion() {
        return discussion;
    }
    public void setDiscussion(long discussion) {
        this.discussion = discussion;
    }
    public String getParentAnsweringSurvey() {
        return parentAnsweringSurvey;
    }
    public void setParentAnsweringSurvey(String parentAnsweringSurvey) {
        this.parentAnsweringSurvey = parentAnsweringSurvey;
    }
    public String getParentSchoolSatisfaction() {
        return parentSchoolSatisfaction;
    }
    public void setParentSchoolSatisfaction(String parentSchoolSatisfaction) {
        this.parentSchoolSatisfaction = parentSchoolSatisfaction;
    }
    public String getStudentAbsenceDays() {
        return studentAbsenceDays;
    }
    public void setStudentAbsenceDays(String studentAbsenceDays) {
        this.studentAbsenceDays = studentAbsenceDays;
    }
    public long getStudentMarks() {
        return studentMarks;
    }
    public void setStudentMarks(long studentMarks) {
        this.studentMarks = studentMarks;
    }
    public String getClassIn() {
        return classIn;
    }
    public void setClassIn(String classIn) {
        this.classIn = classIn;
    }
    
}
