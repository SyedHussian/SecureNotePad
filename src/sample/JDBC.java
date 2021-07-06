package sample;

import java.sql.*;

public class JDBC {

    String url = "jdbc:mysql://localhost:3306/securenotepad";
    String user = "root";
    String password = "lLongClaw";


    public void insert(String fname, String lname, String email, String pass) {
        try {
            // 1. get connection to database
            Connection myConn = DriverManager.getConnection(url, user, password);

            // 2. Execute SQL query
            final String insertSql = "insert into accountinfo "
                    + " (fname, lname, email, pass)"
                    + " values (?, ?, ?, ?)";

            // 3. create statement
            final PreparedStatement ps = myConn.prepareStatement(insertSql);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, pass);

            // 4. Process the result set
            ps.executeUpdate();

            AlertBox.display("Successfully", "Successfully created a account.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean checkforDup(String email) {
        try {
            // 1. get connection to database
            Connection myConn = DriverManager.getConnection(url, user, password);

            // 2. Execute SQL query
            final String queryCheck = "Select Exists "
                    + " (Select * from accountinfo"
                    + " where email = ? )";

            // 3. create statement
            final PreparedStatement ps = myConn.prepareStatement(queryCheck);
            ps.setString(1, email);

            // 4. Process the result set
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                final int count = resultSet.getInt(1);
                if (count == 1) {
                    AlertBox.display("Exist!", "This email already exists!");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean verifyForAcc(String email, String pass) {
        try {
            // 1. get connection to database
            Connection myConn = DriverManager.getConnection(url, user, password);

            // 2. Execute SQL query
            final String queryCheck = "Select Exists "
                    + " (Select * from accountinfo"
                    + " where email = ? and pass = ?)";

            // 3. create statement
            final PreparedStatement ps = myConn.prepareStatement(queryCheck);
            ps.setString(1, email);
            ps.setString(2, pass);

            // 4. Process the result set
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                final int count = resultSet.getInt(1);
                if (count != 1) {
                    AlertBox.display("Incorrect email or password!", "Incorrect email or password. Please try again.");
                    return false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    // save file in mysql

    public void saveDoc(String textInput, String title, String email) {
        try {
            // 1. get connection to database
            Connection myConn = DriverManager.getConnection(url, user, password);

            // 2. Execute SQL query
            final String insertSql = "insert into folder "
                    + " (email, textInput, title)"
                    + " values (?, ?, ?)";

            // 3. create statement
            final PreparedStatement ps = myConn.prepareStatement(insertSql);
            ps.setString(1, email);
            ps.setString(2, textInput);
            ps.setString(3, title);

            // 4. Process the result set
            ps.executeUpdate();

            AlertBox.display("Successfully", "File Successfully saved.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
