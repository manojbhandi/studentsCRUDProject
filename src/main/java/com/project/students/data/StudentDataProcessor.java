package com.project.students.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.project.students.model.Student;

public class StudentDataProcessor implements ItemProcessor<StudentInput,Student> {

    private static final Logger log = LoggerFactory.getLogger(StudentDataProcessor.class);
    
    @Override
    public Student process(final StudentInput studentInput) throws Exception {
        
        Student student = new Student();

        student.setStudentId(studentInput.getStudentID());
        student.setGender(studentInput.getGender());
        student.setNationality(studentInput.getNationality());
        student.setPlaceofBirth(studentInput.getPlaceofBirth());
        student.setStageId(studentInput.getStageID());
        student.setGradeId(studentInput.getGradeID());
        student.setSectionId(studentInput.getSectionID());
        student.setTopic(studentInput.getTopic());
        student.setSemester(studentInput.getSemester());
        student.setRelation(studentInput.getRelation());
        student.setRaisedhands(Long.parseLong(studentInput.getRaisedhands()));
        student.setVisitedResources(Long.parseLong(studentInput.getVisitedResources()));
        student.setAnnouncementsView(Long.parseLong(studentInput.getAnnouncementsView()));
        student.setDiscussion(Long.parseLong(studentInput.getDiscussion()));
        student.setParentAnsweringSurvey(studentInput.getParentAnsweringSurvey());
        student.setStudentAbsenceDays(studentInput.getStudentAbsenceDays());
        student.setStudentMarks(Long.parseLong(studentInput.getStudentMarks()));
        student.setClassIn(studentInput.getClassIn());
        student.setParentSchoolSatisfaction(studentInput.getParentSchoolSatisfaction());
        return student;
    
    }
}
