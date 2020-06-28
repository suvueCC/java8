package com.learn;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

class Student {
    String name;
    Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

class Teacher {
    String name;
    Integer age;
    List<Student> students;

    public Teacher(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Student> getStudent() {
        return students;
    }

    public void setStudent(List<Student> students) {
        this.students = students;
    }
}

public class Main {

    public static void main(String[] args) {
        // write your code here
        final Teacher t1 = new Teacher("徐国为老师", 34);
        final Teacher t2 = new Teacher("李志刚老师", 34);
        final List<Student> class1 = Arrays.asList(new Student("张三", 22), new Student("李四", 22));
        final List<Student> class2 = Arrays.asList(new Student("王五", 22), new Student("赵六", 22));
        t1.setStudent(class1);
        t2.setStudent(class2);
        final List<Teacher> teachers = Arrays.asList(t1, t2);
        teachers.stream()
                .flatMap(teacher -> teacher.getStudent().stream()
                        .map(student -> teacher.getName() + " == " + student.getName()))
                .forEach(System.out::println);


        String aaa = "11.00";
        System.out.println(aaa.getClass().toString());
        System.out.println(new BigDecimal(aaa).getClass().toString());
    }
}
