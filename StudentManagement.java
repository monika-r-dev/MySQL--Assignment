package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class StudentManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/mysql_assignment";
        String username = "root";
        String password = "Monika@123";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            System.out.println("Database connected successfully!");

            int choice;

            do {

                System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
                System.out.println("1. Student Registration");
                System.out.println("2. Course Enrollment");
                System.out.println("3. Attendance Management");
                System.out.println("4. Marks Entry");
                System.out.println("5. Result Generation");
                System.out.println("6. Exit");

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {

                case 1:

                    System.out.print("Enter Student ID: ");
                    int studentId = sc.nextInt();

                    System.out.print("Enter Student Name: ");
                    String studentName = sc.next();

                    System.out.print("Enter Email: ");
                    String email = sc.next();

                    String studentInsert =
                            "INSERT INTO students(student_id, student_name, email) VALUES(?,?,?)";

                    PreparedStatement psStudent =
                            con.prepareStatement(studentInsert);

                    psStudent.setInt(1, studentId);
                    psStudent.setString(2, studentName);
                    psStudent.setString(3, email);

                    psStudent.executeUpdate();

                    System.out.println("Student registered successfully!");

                    break;


                case 2:

                    System.out.print("Enter Enrollment ID: ");
                    int enrollmentId = sc.nextInt();

                    System.out.print("Enter Student ID: ");
                    int enrollStudentId = sc.nextInt();

                    System.out.print("Enter Course ID: ");
                    int courseId = sc.nextInt();

                    String enrollmentInsert =
                            "INSERT INTO enrollments(enrollment_id, student_id, course_id) VALUES(?,?,?)";

                    PreparedStatement psEnrollment =
                            con.prepareStatement(enrollmentInsert);

                    psEnrollment.setInt(1, enrollmentId);
                    psEnrollment.setInt(2, enrollStudentId);
                    psEnrollment.setInt(3, courseId);

                    psEnrollment.executeUpdate();

                    System.out.println("Course enrolled successfully!");

                    break;


                case 3:

                    System.out.print("Enter Attendance ID: ");
                    int attendanceId = sc.nextInt();

                    System.out.print("Enter Student ID: ");
                    int attendanceStudentId = sc.nextInt();

                    System.out.print("Enter Attendance Percentage: ");
                    double attendancePercentage = sc.nextDouble();

                    String attendanceInsert =
                            "INSERT INTO attendance(attendance_id, student_id, attendance_percentage) VALUES(?,?,?)";

                    PreparedStatement psAttendance =
                            con.prepareStatement(attendanceInsert);

                    psAttendance.setInt(1, attendanceId);
                    psAttendance.setInt(2, attendanceStudentId);
                    psAttendance.setDouble(3, attendancePercentage);

                    psAttendance.executeUpdate();

                    System.out.println("Attendance saved successfully!");

                    break;


                case 4:

                    System.out.print("Enter Mark ID: ");
                    int markId = sc.nextInt();

                    System.out.print("Enter Student ID: ");
                    int markStudentId = sc.nextInt();

                    System.out.print("Enter Course ID: ");
                    int markCourseId = sc.nextInt();

                    System.out.print("Enter Mark: ");
                    double mark = sc.nextDouble();

                    String marksInsert =
                            "INSERT INTO marks(mark_id, student_id, course_id, mark) VALUES(?,?,?,?)";

                    PreparedStatement psMarks =
                            con.prepareStatement(marksInsert);

                    psMarks.setInt(1, markId);
                    psMarks.setInt(2, markStudentId);
                    psMarks.setInt(3, markCourseId);
                    psMarks.setDouble(4, mark);

                    psMarks.executeUpdate();

                    System.out.println("Marks entered successfully!");

                    break;


                case 5:

                    System.out.print("Enter Student ID: ");
                    int resultStudentId = sc.nextInt();

                    String resultQuery =
                            "SELECT s.student_id, s.student_name, c.course_name, m.mark " +
                            "FROM students s " +
                            "JOIN marks m ON s.student_id = m.student_id " +
                            "JOIN courses c ON m.course_id = c.course_id " +
                            "WHERE s.student_id = ?";

                    PreparedStatement psResult =
                            con.prepareStatement(resultQuery);

                    psResult.setInt(1, resultStudentId);

                    ResultSet rs = psResult.executeQuery();

                    System.out.println("\n===== RESULT =====");

                    while (rs.next()) {

                        System.out.println(
                                "Student ID: " + rs.getInt("student_id")
                                + " | Name: " + rs.getString("student_name")
                                + " | Course: " + rs.getString("course_name")
                                + " | Mark: " + rs.getDouble("mark")
                        );
                    }

                    break;


                case 6:

                    System.out.println("Program exited.");

                    break;


                default:

                    System.out.println("Invalid choice!");

                }

            } while (choice != 6);

            con.close();
            sc.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
