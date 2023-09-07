package com.project.students.controller;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.project.students.jpaRepository.UserRepository;
import com.project.students.model.Student;

import jakarta.validation.Valid;


@RestController
public class StudentController {
    
    private UserRepository repository;

    public StudentController(UserRepository repository) {
        this.repository = repository;
    }

// get resource by id
    @GetMapping( "/students/{studentId}")
    public ResponseEntity<Student> getStudentByStudentId(@PathVariable String studentId){
        Student student = repository.findByStudentId(studentId);
        if(student == null){
            // System.out.println(student);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(student);
    }
// post resource 
    @PostMapping("/students")
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student newStudent){
        Student savedStudent = repository.save(newStudent);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{studentId}")
                        .buildAndExpand(savedStudent.getStudentId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }
// updateing resource
    @PutMapping("students/{studentId}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student newStudent, @PathVariable String studentId){
        
        Student studentToBeUpdated = repository.findByStudentId(studentId);
        if(studentToBeUpdated == null){
            return ResponseEntity.notFound().build();
        }
        studentToBeUpdated.setStudentId(studentId);
        studentToBeUpdated.setGender(newStudent.getGender());
        studentToBeUpdated.setNationality(newStudent.getNationality());
        studentToBeUpdated.setPlaceofBirth(newStudent.getPlaceofBirth());
        studentToBeUpdated.setStageId(newStudent.getStageId());
        studentToBeUpdated.setGradeId(newStudent.getGradeId());
        studentToBeUpdated.setSectionId(newStudent.getSectionId());
        studentToBeUpdated.setTopic(newStudent.getTopic());
        studentToBeUpdated.setSemester(newStudent.getSemester());
        studentToBeUpdated.setRelation(newStudent.getRelation());
        studentToBeUpdated.setRaisedhands(newStudent.getRaisedhands());
        studentToBeUpdated.setVisitedResources(newStudent.getVisitedResources());
        studentToBeUpdated.setAnnouncementsView(newStudent.getAnnouncementsView());
        studentToBeUpdated.setDiscussion(newStudent.getDiscussion());
        studentToBeUpdated.setParentAnsweringSurvey(newStudent.getParentAnsweringSurvey());
        studentToBeUpdated.setStudentAbsenceDays(newStudent.getStudentAbsenceDays());
        studentToBeUpdated.setStudentMarks(newStudent.getStudentMarks());
        studentToBeUpdated.setClassIn(newStudent.getClassIn());
        studentToBeUpdated.setParentSchoolSatisfaction(newStudent.getParentSchoolSatisfaction());
        
        repository.save(studentToBeUpdated);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{studentId}")
                        .buildAndExpand(studentToBeUpdated.getStudentId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteUser(@PathVariable String studentId) {
        // repository.deleteByStudentId(studentId);
        repository.removeByStudentId(studentId);
    }

    
}
