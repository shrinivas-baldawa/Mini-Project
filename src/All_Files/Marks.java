package All_Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Marks {
    @FXML
    private Button bt_1;

    @FXML
    private TextField tf_1;

    @FXML
    private Button bt_2;

    @FXML
    private TextField tf_2;

    @FXML
    private TextField tf_3;

    @FXML
    private TextField tf_4;

    @FXML
    private TextField tf_5;

    @FXML
    void onCancel(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentLoginNew.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Student Login");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            System.out.println("Window could not be loaded");
        }
    }

    @FXML
    void onSubmit(ActionEvent event) {
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalDB","postgres","Shrinivas1@");
            System.out.println("Connected to the database");
            String tenth_marks = tf_1.getText();
            String twelfth_marks = tf_2.getText();
            String cet_id = tf_3.getText();
            String cet_result = tf_4.getText();
            String jee_result = tf_5.getText();
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO marks(tenth_marks,twelth_marks,cet_id,cet_result,jee_result) VALUES(?,?,?,?,?)");
            pstmt.setString(1,tenth_marks);
            pstmt.setString(2,twelfth_marks);
            pstmt.setString(3,cet_id);
            pstmt.setString(4,cet_result);
            pstmt.setString(5,jee_result);
            pstmt.executeUpdate();
            System.out.println("Record Inserted Successfully");
            pstmt.close();
            c.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Records Inserted Successful");
            alert.setTitle("Confirmation");
            alert.showAndWait();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
