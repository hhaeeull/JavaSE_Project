package mylab.student.control;
import mylab.student.entity.Student;
import mylab.student.exception.InvalidGradeException;

public class StudentTest {
    public static void main(String[] args) throws InvalidGradeException {
        // 학생 객체 생성
        Student student = new Student();

        // 학생 정보 설정
        student.setStudentId(12345);
        student.setStudentName("김민수");
        student.setMajor("컴퓨터공학");
        student.setGrade(3);

        // 학생 정보 출력
        System.out.println(student.getStudentName() + "/ " + student.getMajor() + "/ " + student.getGrade() + "학년");

        // 학년을 5로 변경 시도
        try {
            student.setGrade(5);
        } catch (InvalidGradeException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
