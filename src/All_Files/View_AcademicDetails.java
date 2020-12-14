package All_Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class View_AcademicDetails {
    @FXML
    private Label lb_1;

    @FXML
    private Label lb_2;

    @FXML
    private Label lb_3;

    @FXML
    private Label lb_4;

    @FXML
    private Label success_failure;

    @FXML
    private TextField tf_1;

    @FXML
    void onClickOK(ActionEvent event) {
        Connection c = null;
        try{
            String check_cid = tf_1.getText();
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalDB","postgres","Shrinivas1@");
            System.out.println("Connected to the database");
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM marks WHERE cet_id = ?");
            pstmt.setString(1,check_cid);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                lb_1.setText(rs.getString(3));
                lb_2.setText(rs.getString(4));
                lb_3.setText(rs.getString(1));
                lb_4.setText(rs.getString(2));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            success_failure.setText("Invalid CET ID");
        }
        catch (Exception e){
            e.printStackTrace();
            success_failure.setText("Invalid CET ID");
        }
    }
}

