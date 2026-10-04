package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class PayrollManagement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/payroll_management";
        String username = "root";
        String password = "Monika@123";

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con =
                    DriverManager.getConnection(url, username, password);

            System.out.println("Database connected successfully!");

            int choice;

            do {

                System.out.println("\n===== PAYROLL MANAGEMENT SYSTEM =====");
                System.out.println("1. Employee Management");
                System.out.println("2. Salary Calculation");
                System.out.println("3. Payslip Generation");
                System.out.println("4. Tax Deduction");
                System.out.println("5. Total Salary");
                System.out.println("6. Batch Processing");
                System.out.println("7. Exit");

                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {

                // 1. EMPLOYEE MANAGEMENT
                case 1:

                    System.out.println("\n1. Add Employee");
                    System.out.println("2. View Employees");
                    System.out.print("Enter option: ");

                    int empOption = sc.nextInt();

                    if (empOption == 1) {

                        System.out.print("Enter Employee ID: ");
                        int empId = sc.nextInt();

                        System.out.print("Enter Employee Name: ");
                        String empName = sc.next();

                        System.out.print("Enter Department: ");
                        String department = sc.next();

                        System.out.print("Enter Basic Salary: ");
                        double basicSalary = sc.nextDouble();

                        String insert =
                                "INSERT INTO employees " +
                                "(employee_id, employee_name, department, basic_salary) " +
                                "VALUES (?, ?, ?, ?)";

                        PreparedStatement ps =
                                con.prepareStatement(insert);

                        ps.setInt(1, empId);
                        ps.setString(2, empName);
                        ps.setString(3, department);
                        ps.setDouble(4, basicSalary);

                        ps.executeUpdate();

                        System.out.println(
                                "Employee added successfully!");

                    } else if (empOption == 2) {

                        String select =
                                "SELECT * FROM employees";

                        Statement st =
                                con.createStatement();

                        ResultSet rs =
                                st.executeQuery(select);

                        System.out.println("\n--- EMPLOYEE DETAILS ---");

                        while (rs.next()) {

                            System.out.println(
                                    rs.getInt("employee_id")
                                    + " | "
                                    + rs.getString("employee_name")
                                    + " | "
                                    + rs.getString("department")
                                    + " | "
                                    + rs.getDouble("basic_salary")
                            );
                        }
                    }

                    break;


                // 2. SALARY CALCULATION
                case 2:

                    System.out.print("Enter Salary ID: ");
                    int salaryId = sc.nextInt();

                    System.out.print("Enter Employee ID: ");
                    int salaryEmpId = sc.nextInt();

                    System.out.print("Enter Allowance: ");
                    double allowance = sc.nextDouble();

                    System.out.print("Enter Deduction: ");
                    double deduction = sc.nextDouble();

                    String getSalary =
                            "SELECT basic_salary FROM employees " +
                            "WHERE employee_id=?";

                    PreparedStatement getPs =
                            con.prepareStatement(getSalary);

                    getPs.setInt(1, salaryEmpId);

                    ResultSet salaryRs =
                            getPs.executeQuery();

                    if (salaryRs.next()) {

                        double basicSalary =
                                salaryRs.getDouble("basic_salary");

                        double netSalary =
                                basicSalary + allowance - deduction;

                        String insertSalary =
                                "INSERT INTO salaries " +
                                "(salary_id, employee_id, basic_salary, allowance, deduction) " +
                                "VALUES (?, ?, ?, ?, ?)";

                        PreparedStatement salaryPs =
                                con.prepareStatement(insertSalary);

                        salaryPs.setInt(1, salaryId);
                        salaryPs.setInt(2, salaryEmpId);
                        salaryPs.setDouble(3, basicSalary);
                        salaryPs.setDouble(4, allowance);
                        salaryPs.setDouble(5, deduction);

                        salaryPs.executeUpdate();

                        System.out.println("\n--- SALARY CALCULATION ---");
                        System.out.println("Basic Salary: " + basicSalary);
                        System.out.println("Allowance: " + allowance);
                        System.out.println("Deduction: " + deduction);
                        System.out.println("Net Salary: " + netSalary);

                    } else {

                        System.out.println("Employee not found!");
                    }

                    break;


                // 3. PAYSLIP GENERATION
                case 3:

                    System.out.print("Enter Employee ID: ");
                    int payslipEmpId = sc.nextInt();

                    String payslipQuery =
                            "SELECT e.employee_id, e.employee_name, " +
                            "e.department, s.basic_salary, " +
                            "s.allowance, s.deduction " +
                            "FROM employees e " +
                            "JOIN salaries s " +
                            "ON e.employee_id = s.employee_id " +
                            "WHERE e.employee_id=?";

                    PreparedStatement payslipPs =
                            con.prepareStatement(payslipQuery);

                    payslipPs.setInt(1, payslipEmpId);

                    ResultSet payslipRs =
                            payslipPs.executeQuery();

                    if (payslipRs.next()) {

                        double basic =
                                payslipRs.getDouble("basic_salary");

                        double allow =
                                payslipRs.getDouble("allowance");

                        double deduct =
                                payslipRs.getDouble("deduction");

                        double net =
                                basic + allow - deduct;

                        System.out.println("\n===== PAYSLIP =====");
                        System.out.println(
                                "Employee ID: "
                                + payslipRs.getInt("employee_id"));

                        System.out.println(
                                "Employee Name: "
                                + payslipRs.getString("employee_name"));

                        System.out.println(
                                "Department: "
                                + payslipRs.getString("department"));

                        System.out.println(
                                "Basic Salary: " + basic);

                        System.out.println(
                                "Allowance: " + allow);

                        System.out.println(
                                "Deduction: " + deduct);

                        System.out.println(
                                "Net Salary: " + net);

                    } else {

                        System.out.println(
                                "Payslip details not found!");
                    }

                    break;


                // 4. TAX DEDUCTION
                case 4:

                    System.out.print("Enter Employee ID: ");
                    int taxEmpId = sc.nextInt();

                    String taxQuery =
                            "SELECT e.employee_name, " +
                            "s.basic_salary, s.allowance " +
                            "FROM employees e " +
                            "JOIN salaries s " +
                            "ON e.employee_id=s.employee_id " +
                            "WHERE e.employee_id=?";

                    PreparedStatement taxPs =
                            con.prepareStatement(taxQuery);

                    taxPs.setInt(1, taxEmpId);

                    ResultSet taxRs =
                            taxPs.executeQuery();

                    if (taxRs.next()) {

                        double basic =
                                taxRs.getDouble("basic_salary");

                        double allow =
                                taxRs.getDouble("allowance");

                        double gross =
                                basic + allow;

                        double tax;

                        if (gross <= 30000) {
                            tax = 0;
                        } else if (gross <= 50000) {
                            tax = gross * 0.05;
                        } else {
                            tax = gross * 0.10;
                        }

                        System.out.println("\n--- TAX DETAILS ---");
                        System.out.println(
                                "Employee Name: "
                                + taxRs.getString("employee_name"));

                        System.out.println(
                                "Gross Salary: " + gross);

                        System.out.println(
                                "Tax Deduction: " + tax);

                    } else {

                        System.out.println(
                                "Employee salary details not found!");
                    }

                    break;


                // 5. AGGREGATE FUNCTION
                case 5:

                    String totalQuery =
                            "SELECT SUM(basic_salary) " +
                            "AS total_salary FROM employees";

                    Statement totalSt =
                            con.createStatement();

                    ResultSet totalRs =
                            totalSt.executeQuery(totalQuery);

                    if (totalRs.next()) {

                        System.out.println(
                                "Total Basic Salary: "
                                + totalRs.getDouble("total_salary"));
                    }

                    break;


                // 6. BATCH PROCESSING
                case 6:

                    String batchInsert =
                            "INSERT INTO employees " +
                            "(employee_id, employee_name, department, basic_salary) " +
                            "VALUES (?, ?, ?, ?)";

                    PreparedStatement batchPs =
                            con.prepareStatement(batchInsert);

                    batchPs.setInt(1, 10);
                    batchPs.setString(2, "Arun");
                    batchPs.setString(3, "IT");
                    batchPs.setDouble(4, 30000);
                    batchPs.addBatch();

                    batchPs.setInt(1, 11);
                    batchPs.setString(2, "Bala");
                    batchPs.setString(3, "HR");
                    batchPs.setDouble(4, 35000);
                    batchPs.addBatch();

                    batchPs.setInt(1, 12);
                    batchPs.setString(2, "Divya");
                    batchPs.setString(3, "Finance");
                    batchPs.setDouble(4, 40000);
                    batchPs.addBatch();

                    int[] result =
                            batchPs.executeBatch();

                    System.out.println(
                            result.length
                            + " employees inserted using batch processing.");

                    break;


                // 7. EXIT
                case 7:

                    System.out.println(
                            "Payroll Management System exited.");

                    break;


                default:

                    System.out.println("Invalid choice!");
                }

            } while (choice != 7);

            con.close();
            sc.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}