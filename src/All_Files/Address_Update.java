package All_Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class Address_Update {
    @FXML
    public Label lb_success;
    @FXML
    public TextField prn;

    @FXML
    public TextField new_address;
    @FXML
    void onSubmit(ActionEvent event) {
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalDB","postgres","Shrinivas1@");
            System.out.println("Connected to the database");
            String check_prn = prn.getText();
            String nAddress = new_address.getText();
            String sql = "SELECT prn FROM student where prn='"+check_prn+"'";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if(rs.next())
            {
                lb_success.setText("PRN Exists In Table");
                System.out.println("Updating Records");
                PreparedStatement pstmt = c.prepareStatement("UPDATE student SET address = ? WHERE prn = ?");
                pstmt.setString(1,nAddress);
                pstmt.setString(2,check_prn);
                pstmt.executeUpdate();
                System.out.println("Records updated successfully");
                pstmt.close();
                c.close();
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setContentText("Address Updated Successful");
                alert.setTitle("Confirmation");
                alert.showAndWait();
            }
            else
                lb_success.setText("Invalid PRN");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
