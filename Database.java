import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Database {
    // Database details
    final String DRIVER = "com.mysql.cj.jdbc.Driver";
    final String DB_URL = "jdbc:mysql://localhost/jjndm";
    final String DB_USER = "root";
    final String DB_PASS = "password";

    // Declaring connection
    Connection conn = null;
    // Declaring Statements
    PreparedStatement psAddMovie, psAddTicket, psRemoveMovie, psGetMovies, psSubtractSeat, psCheckSeat, psCheckCinema,
            psCheckRevenue, psCheckMovies;

    Database() {
        // Loading MySQL driver
        try {
            Class.forName(DRIVER);
            System.out.println("Loading MySQL driver...");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Unable to load MySQL driver!", "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }

        // Connecting to MySQL database
        try {
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
            // Add Movie Query
            psAddMovie = conn
                    .prepareStatement(
                            "INSERT INTO movies (movName, timeSlot, availSeat, movPrice, cinemaNo) VALUES (?, ?, ?, ?, ?)");
            // Remove Movie Query
            psRemoveMovie = conn.prepareStatement("DELETE FROM movies WHERE movUID = ?");
            // Add Ticket Query
            psAddTicket = conn.prepareStatement(
                    "INSERT INTO tickets (movUID, movName, movPrice, seatNo, timeSlot) VALUES (?, ?, ?, ?, ?)");
            // Get Movies Query; returns all movies in database
            psGetMovies = conn.prepareStatement("SELECT * FROM movies", ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            // SubtractSeat Query; subtracts 1 from available seat of selected movie
            psSubtractSeat = conn.prepareStatement("UPDATE movies SET availSeat = (availSeat - 1) WHERE movUID = ?");
            // CheckSeat Query; checks if seat is already taken; if taken, returns 1, else
            // returns 0
            psCheckSeat = conn.prepareStatement(
                    "SELECT CASE WHEN EXISTS (SELECT tickUID FROM tickets WHERE movUID = ? AND seatNo = ?) THEN 1 ELSE 0 END");
            // CheckCinema Query; checks if cinema number is already taken, if taken,
            // returns 1, else returns 0
            psCheckCinema = conn.prepareStatement(
                    "SELECT CASE WHEN EXISTS (SELECT movUID FROM movies WHERE cinemaNo = ?) THEN 1 ELSE 0 END");
            // Check Revenue Query; returns movPrice of all tickets in database
            psCheckRevenue = conn.prepareStatement("SELECT movPrice FROM tickets");
            // Check Movies Query; checks if a movie exists in database; returns 1 if there
            // are movies, else returns 0
            psCheckMovies = conn.prepareStatement(
                    "SELECT CASE WHEN EXISTS (SELECT * FROM movies) THEN 1 ELSE 0 END");

            System.out.println("Connecting to MySQL database...");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to connect to MySQL database!", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    // Adds movie to database
    // Be sure to run cinemaExists first
    public void addMovie(String movName, String timeSlot, int availSeat, float movPrice, int cinemaNo) {
        try {
            // Applying parameters
            psAddMovie.setString(1, movName);
            psAddMovie.setString(2, timeSlot);
            psAddMovie.setInt(3, availSeat);
            psAddMovie.setFloat(4, movPrice);
            psAddMovie.setInt(5, cinemaNo);
            // Executing query
            psAddMovie.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    // Checks if cinema number is already taken
    // To be used by Maddcinema; call this function first, if returned true, dont
    // call addMovie(). Else, you may call addMovie()
    public boolean cinemaExists(int cinemaNo) {
        try {
            // Applying parameters
            psCheckCinema.setInt(1, cinemaNo);
            // Retrieving result
            ResultSet rs = psCheckCinema.executeQuery();
            // Going to first result row
            rs.next();
            if (rs.getInt(1) == 1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    // Removes movie to database
    // Be sure to run moviesExists first
    public void removeMovie(int movUID) {
        try {
            // Applying parameters
            psRemoveMovie.setInt(1, movUID);
            // Executing Query
            psRemoveMovie.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    // Be sure to run moviesExists and seatExists first
    public void addTicket(int movUID, String movName, float movPrice, int seatNo, String timeSlot) {
        try {
            // Applying Parameters
            psAddTicket.setInt(1, movUID);
            psAddTicket.setString(2, movName);
            psAddTicket.setFloat(3, movPrice);
            psAddTicket.setInt(4, seatNo);
            psAddTicket.setString(5, timeSlot);
            psAddTicket.executeUpdate();
            // Subtracts seats of specified movie
            subtractSeat(movUID);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    // Checks if a movie exists on database; returns TRUE if a movie exists, else
    // returns FALSE
    public boolean moviesExists() {
        try {
            // Retrieving result
            ResultSet rs = psCheckMovies.executeQuery();
            // Going to first result row
            rs.next();
            if (rs.getInt(1) == 1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    // Checks if seat is already taken; returns TRUE if already taken, else FALSE
    // To be used by Maddticket; call this function first, if returned true, dont
    // call addTicket(). Else, you may call addTicket()
    public boolean seatExists(int movUID, int seatNo) {
        try {
            // Applying parametrs
            psCheckSeat.setInt(1, movUID);
            psCheckSeat.setInt(2, seatNo);
            // Retrieving result
            ResultSet rs = psCheckSeat.executeQuery();
            // Going to first result row
            rs.next();
            if (rs.getInt(1) == 1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
            return false;
        }
    }

    // Subtracts available seats of certain movie
    public void subtractSeat(int movUID) {
        try {
            // Applying Parameters
            psSubtractSeat.setInt(1, movUID);
            // Executing Query
            psSubtractSeat.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }

    // Returns all movies in database
    public ResultSet getMovies() {
        try {
            // Retrieving Result
            ResultSet rs = psGetMovies.executeQuery();
            return rs;
            // while (rs.next()) {
            // String movName = rs.getString("movName");
            // String timeSlot = rs.getString("timeSlot");
            // int availSeat = rs.getInt("availSeat");
            // float movPrice = rs.getFloat("movPrice");
            // int cinemaNo = rs.getInt("cinemaNo");

            // System.out.println(movName + " " + timeSlot + " " + availSeat + " " +
            // movPrice + " " + cinemaNo);
            // }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }

    // Returns all movPrices of tickets in database
    public ResultSet getRevenue() {
        try {
            return psCheckRevenue.executeQuery();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "An error has occured", "Error",
                    JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
            return null;
        }
    }

}
