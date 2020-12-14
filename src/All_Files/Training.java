package All_Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Training {
    @FXML
    private TextField comapny;

    @FXML
    private TextField duration;

    @FXML
    private TextField prn;

    @FXML
    void onSubmit(ActionEvent event) {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalDB","postgres","Shrinivas1@");
            System.out.println("Connected to the database");
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO internship(company,duration,st_prn) VALUES(?,?,?)");
            pstmt.setString(1,comapny.getText());
            pstmt.setString(2,duration.getText());
            pstmt.setString(3,prn.getText());
            pstmt.executeUpdate();
            System.out.println("Inserted Successfully");
            pstmt.close();
            c.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Records Inserted Successful");
            alert.setTitle("Confirmation");
            alert.showAndWait();
        }
        catch (SQLException | ClassNotFoundException se ){
            se.printStackTrace();
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        comapny.setText("");
        duration.setText("");
        prn.setText("");
    }
}
