package lab8.entity;

import lab8.utils.DateTimeUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.StringJoiner;

public class Student implements Serializable {
    private long id;
    private String name;
    private String address;
    private String tel;
    private Date DOB;
    private Date enterDate;
    private double mathScores;
    private Integer age;
    private Integer ageLevel;

    public Student() {
    }

    public Student(long id, String name, String address, String tel, Date DOB, Date enterDate, double mathScores) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.tel = tel;
        this.DOB = DOB;
        this.enterDate = enterDate;
        this.mathScores = mathScores;
    }

    public Integer getAge() {
        if (this.DOB != null) {
            int age = DateTimeUtils.getAge(this.DOB);
//            System.err.println(age);
            return age < 0 ? null : age;
        }
        return null;
    }

    public Integer getAgeLevel() {
        if (this.enterDate != null) {
            int ageLevel = DateTimeUtils.getAge(this.enterDate);
//            System.err.println(ageLevel);
            return ageLevel < 0 ? null : ageLevel;
        }
        return null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public double getMathScores() {
        return mathScores;
    }

    public void setMathScores(double mathScores) {
        this.mathScores = mathScores;
    }

    public void outputInfoStudent() {
        System.out.println("Student ID                         : " + getId());
        System.out.println("Name of the student                : " + getName());
        System.out.println("Address of the student             : " + getAddress());
        System.out.println("Cellphone number of the student    : " + getTel());
        System.out.println("Date of birth of the student       : " + getDOB());
        System.out.println("Enter date of the student          : " + getEnterDate());
        System.out.println("The age of the student             : " + getAge());
        System.out.println("The level of the student           : " + getAgeLevel());
        System.out.println("Math scores of the student         : " + getMathScores());
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", DOB=" + DOB +
                ", enterDate=" + enterDate +
                ", mathScores=" + mathScores +
                ", age=" + getAge() +
                ", ageLevel=" + getAgeLevel() +
                '}';
    }
}
