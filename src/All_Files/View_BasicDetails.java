package All_Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class View_BasicDetails {
    @FXML
    private TextField prn_tf1;

    @FXML
    private Label tf_1;

    @FXML
    private Label tf_2;

    @FXML
    private Label tf_3;

    @FXML
    private Label tf_4;

    @FXML
    private Label tf_5;

    @FXML
    private Label tf_6;

    @FXML
    private Label tf_7;

    @FXML
    private Label success_failure;

    @FXML
    void onOK(ActionEvent event) {
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://ec2-18-206-20-102.compute-1.amazonaws.com:5432/ddeuc850qat336","gnmwvfusjgondu","d24dd511d794c7214bec9c67be92740279caabac4cb8e08f4769f49de27b580e");
            System.out.println("Connected to the database");
            String check_prn = prn_tf1.getText();
            PreparedStatement pstmt = c.prepareStatement("SELECT * FROM student WHERE prn = ?");
            pstmt.setString(1,check_prn);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                tf_1.setText(rs.getString(2));
                tf_2.setText(rs.getString(8));
                tf_3.setText(rs.getString(7));
                tf_4.setText(rs.getString(3));
                tf_5.setText(rs.getString(6));
                tf_6.setText(rs.getString(5));
                tf_7.setText(rs.getString(4));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
