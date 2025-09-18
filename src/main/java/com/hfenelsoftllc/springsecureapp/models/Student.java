package com.hfenelsoftllc.springsecureapp.models;

public class Student {


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Mark getMarks() {
        return marks;
    }

    public void setMarks(Mark marks) {
        this.marks = marks;
    }



    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", marks='" + marks + '\'' +
                '}';
    }

    int id;
    String name;
    Mark marks;
    public Student(int id, String name, Mark marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }
}
