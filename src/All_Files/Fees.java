package All_Files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;

public class Fees implements Initializable {

    @FXML
    private Label lb_1;

    @FXML
    private ComboBox<String> cb_1;
    ObservableList<String> installm = FXCollections.observableArrayList("0","1","2","3");

    @FXML
    private ComboBox<String> cb_2;
    ObservableList<String> mop = FXCollections.observableArrayList("Credit Card","Net Banking","Demand Draft","Cheque");

    @FXML
    private Label lb_2;

    @FXML
    void onCancel(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentLogin.fxml"));
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

    public static String f_id;
    public void onClick(javafx.event.ActionEvent event) {
        System.out.println(cb_1.getValue());
        System.out.println(cb_2.getValue());
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalDB","postgres","Shrinivas1@");
            System.out.println("Connected to the database");
            String t_amt = lb_2.getText();
            String installments = String.valueOf(cb_1.getValue());
            String method_payment = String.valueOf(cb_2.getValue());
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            while (salt.length() < 6) { // length of the random string.
                int index = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(index));
            }
            f_id = salt.toString();
            PreparedStatement pstmt = c.prepareStatement("INSERT INTO fees(total_amount,installments,method_of_payment,fees_id) VALUES(?,?,?,?)");
            pstmt.setString(1,t_amt);
            pstmt.setString(2,installments);
            pstmt.setString(3,method_payment);
            pstmt.setString(4,f_id);
            pstmt.executeUpdate();
            System.out.println("Record Inserted Successfully");
            pstmt.close();
            c.close();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Records Inserted Successful");
            alert.setContentText(f_id+" Please Note This ID for further enquires");
            alert.setTitle("Confirmation");
            alert.showAndWait();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb_1.setItems(installm);
        cb_2.setItems(mop);
    }
}
