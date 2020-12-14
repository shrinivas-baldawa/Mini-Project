package All_Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class Contact_Update {
    @FXML
    private TextField prn_tf1;

    @FXML
    private TextField contact_tf2;

    @FXML
    private Label success_lb1;

    @FXML
    void onSubmit(ActionEvent event) {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalDB", "postgres", "Shrinivas1@");
            System.out.println("Connected to the database");
            String check_prn = prn_tf1.getText();
            String nContact = contact_tf2.getText();
            String sql = "SELECT prn FROM student where prn='" + check_prn + "'";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (nContact.length() == 10) {
                if (rs.next()) {
                    System.out.println("Updating Records");
                    PreparedStatement pstmt = c.prepareStatement("UPDATE student SET contact = ? WHERE prn = ?");
                    pstmt.setString(1, nContact);
                    pstmt.setString(2, check_prn);
                    pstmt.executeUpdate();
                    System.out.println("Records updated successfully");
                    pstmt.close();
                    c.close();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Contact Updated Successful");
                    alert.setTitle("Confirmation");
                    alert.showAndWait();
                } else
                    success_lb1.setText("Invalid PRN");
            }
            else
                success_lb1.setText("Invalid Contact Number");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
