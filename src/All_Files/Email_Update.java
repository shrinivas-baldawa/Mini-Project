package All_Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Email_Update {
    @FXML
    private Label success_lb1;

    @FXML
    private TextField prn_tf1;

    @FXML
    private TextField email_tf2;

    @FXML
    void onSubmit(ActionEvent event) {
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalDB","postgres","Shrinivas1@");
            System.out.println("Connected to the database");
            String check_prn = prn_tf1.getText();
            String nEmail = email_tf2.getText();
            String sql = "SELECT prn FROM student where prn='"+check_prn+"'";
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
            Matcher m = p.matcher(email_tf2.getText());
            if(m.find() && m.group().equals(email_tf2.getText())){
                if(rs.next())
                {
                    System.out.println("Updating Records");
                    PreparedStatement pstmt = c.prepareStatement("UPDATE student SET email_id = ? WHERE prn = ?");
                    pstmt.setString(1,nEmail);
                    pstmt.setString(2,check_prn);
                    pstmt.executeUpdate();
                    System.out.println("Records updated successfully");
                    pstmt.close();
                    c.close();
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("Email ID Updated Successful");
                    alert.setTitle("Confirmation");
                    alert.showAndWait();
                }
                else
                    success_lb1.setText("Invalid PRN");
            }
            else
                success_lb1.setText("Email ID not valid");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
