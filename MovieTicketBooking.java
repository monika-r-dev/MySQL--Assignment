package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MovieTicketBooking {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String url = "jdbc:mysql://localhost:3306/movie_booking";
        String username = "root";
        String password = "Monika@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, username, password);

            int choice;

            do {
                System.out.println("\n===== MOVIE TICKET BOOKING SYSTEM =====");
                System.out.println("1. Movie Listing");
                System.out.println("2. Theater Management");
                System.out.println("3. Seat Booking");
                System.out.println("4. Ticket Generation");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                choice = sc.nextInt();

                switch (choice) {

                case 1:

                    String movieQuery = "SELECT * FROM movies";

                    PreparedStatement movieStmt =
                            con.prepareStatement(movieQuery);

                    ResultSet movieRs = movieStmt.executeQuery();

                    System.out.println("\n--- MOVIE LIST ---");

                    while (movieRs.next()) {

                        System.out.println(
                                movieRs.getInt("movie_id") + " | " +
                                movieRs.getString("movie_name") + " | " +
                                movieRs.getString("language") + " | " +
                                movieRs.getInt("duration") + " minutes"
                        );
                    }

                    break;

                case 2:

                    String theaterQuery = "SELECT * FROM theaters";

                    PreparedStatement theaterStmt =
                            con.prepareStatement(theaterQuery);

                    ResultSet theaterRs = theaterStmt.executeQuery();

                    System.out.println("\n--- THEATER LIST ---");

                    while (theaterRs.next()) {

                        System.out.println(
                                theaterRs.getInt("theater_id") + " | " +
                                theaterRs.getString("theater_name") + " | " +
                                theaterRs.getString("location")
                        );
                    }

                    break;

                case 3:

                    System.out.print("Enter Show ID: ");
                    int showId = sc.nextInt();

                    System.out.print("Enter Customer Name: ");
                    String customerName = sc.next();

                    System.out.print("Enter Seat Number: ");
                    String seatNumber = sc.next();

                    String checkSeat =
                            "SELECT * FROM bookings WHERE show_id=? AND seat_number=?";

                    PreparedStatement checkStmt =
                            con.prepareStatement(checkSeat);

                    checkStmt.setInt(1, showId);
                    checkStmt.setString(2, seatNumber);

                    ResultSet seatRs = checkStmt.executeQuery();

                    if (seatRs.next()) {

                        System.out.println("Seat already booked!");

                    } else {

                        String insert =
                                "INSERT INTO bookings " +
                                "(booking_id, show_id, customer_name, seat_number, booking_status) " +
                                "VALUES (?, ?, ?, ?, ?)";

                        PreparedStatement bookingStmt =
                                con.prepareStatement(insert);

                        System.out.print("Enter Booking ID: ");
                        int bookingId = sc.nextInt();

                        bookingStmt.setInt(1, bookingId);
                        bookingStmt.setInt(2, showId);
                        bookingStmt.setString(3, customerName);
                        bookingStmt.setString(4, seatNumber);
                        bookingStmt.setString(5, "BOOKED");

                        bookingStmt.executeUpdate();

                        System.out.println("Seat booked successfully!");
                    }

                    break;

                case 4:

                    System.out.print("Enter Booking ID: ");
                    int bookingId = sc.nextInt();

                    String ticketQuery =
                            "SELECT * FROM bookings WHERE booking_id=?";

                    PreparedStatement ticketStmt =
                            con.prepareStatement(ticketQuery);

                    ticketStmt.setInt(1, bookingId);

                    ResultSet ticketRs = ticketStmt.executeQuery();

                    if (ticketRs.next()) {

                        System.out.println("\n===== TICKET =====");
                        System.out.println("Booking ID: "
                                + ticketRs.getInt("booking_id"));
                        System.out.println("Show ID: "
                                + ticketRs.getInt("show_id"));
                        System.out.println("Customer Name: "
                                + ticketRs.getString("customer_name"));
                        System.out.println("Seat Number: "
                                + ticketRs.getString("seat_number"));
                        System.out.println("Status: "
                                + ticketRs.getString("booking_status"));

                    } else {

                        System.out.println("Booking not found!");
                    }

                    break;

                case 5:

                    System.out.println("Program exited.");
                    break;

                default:

                    System.out.println("Invalid choice!");
                }

            } while (choice != 5);

            con.close();
            sc.close();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
