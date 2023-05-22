package org.example;

public class Student {
    private long id;
    private String fullName;
    private int age;
    private String gender;
    private String address;
    private float average;

    public Student() {
    }

    public Student(long id, String fullName, int age, String gender, String address, float average) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.average = average;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s", this.id, this. fullName, this.age, this.gender, this.address, this.average);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}
