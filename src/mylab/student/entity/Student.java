package mylab.student.entity;
// 캡슐화

import mylab.student.exception.InvalidGradeException;

public class Student {
    // 학번
    private int studentId;
    // 이름
    private String studentName;
    // 전공
    private String major;
    // 학년
    private int grade;


    // Getters and Setters
    public int getStudentId() {
        return studentId;
}

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) throws InvalidGradeException {
        this.grade = grade;

        // 학년은 1~4 사이의 값이어야 한다. 범위를 벗어날 경우 오류메시지 출력
        if (grade < 1 || grade > 4) {
            throw new InvalidGradeException(grade + "학년으로 변경\n학년은 1~4 사이의 값이어야 합니다.");
        }
    }
}