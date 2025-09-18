package com.hfenelsoftllc.springsecureapp.services;

import com.hfenelsoftllc.springsecureapp.models.Student;
import com.hfenelsoftllc.springsecureapp.models.Mark;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;


@Component
public class StudentService {
    private final static List<Student> students = new ArrayList<>();
    private static int studentCounter =0;

    static {
        students.add(new Student(++studentCounter, "Adams", Mark.PASS));
        students.add(new Student(++studentCounter, "Eve", Mark.FAIL));
        students.add(new Student(++studentCounter, "Jim", Mark.RETAIN));
    }

    //private final static List<Mark> marks = new ArrayList<>();
//    public static List<Student> getStudents() {
//        return students;
//    }

    public List<Student> findAll() {
        return students;
    }
    public Student findById(int id) {
        Predicate<? super Student> predicate = student -> student.getId() == id;
        return students.stream().filter(predicate).findFirst().orElse(null);
    }

    public Student findByName(String name) {
        Predicate<? super Student> predicate = student -> student.getName().equals(name);
        return students.stream().filter(predicate).findFirst().orElse(null);
    }

    public Student Save(Student student) {
        student.setId(++studentCounter);
        students.add(student);
        return student;
    }

    public void update(int id, Student student) {
        var studentExist = findById(id);
        if (studentExist != null) {
            student.setName(student.getName());
            student.setMarks(student.getMarks());
        }
        students.add(student);
    }

//    public static List<Mark> getMarks() {
//
//        return marks;
//    }

//    public static Student getStudent(int id) {
//        for (Student student : students) {
//            if (student.getId() == id) {
//                return student;
//            }
//        }
//    }

//    public static Mark getMark(int id) {
//        for (Mark mark : marks) {
//            if(students.stream().anyMatch(student -> student.getId() == id)) {
//                return mark;
//            }
//        }
//    }
}
