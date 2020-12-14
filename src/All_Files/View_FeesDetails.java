package All_Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class View_FeesDetails {
    @FXML
    private Label success_failure;

    @FXML
    private Label installments;

    @FXML
    private Label method_pay;

    @FXML
    private TextField feed_id;

    @FXML
    void onClick(ActionEvent event) {
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalDB","postgres","Shrinivas1@");
            System.out.println("Connected to the database");
            String fees_id = feed_id.getText();
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM fees WHERE fees_id = ?");
            pstmt.setString(1,fees_id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                installments.setText(rs.getString(2));
                method_pay.setText(rs.getString(3));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
