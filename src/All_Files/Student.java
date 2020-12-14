package All_Files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Student implements Initializable {

    @FXML
    private ComboBox<String> blood_grp;
    ObservableList<String> bg = FXCollections.observableArrayList("A+ve","A-ve","B+ve","B-ve","O+ve","O-ve","AB+ve","AB-ve");

    @FXML
    private ComboBox<String> branch;
    ObservableList<String> branchName = FXCollections.observableArrayList("Information Technology","Computer Science","Mechanical","Electronics And Telecommunication","Printing And Packaging Technology");

    @FXML
    private TextField tf_1,tf_2,tf_5,tf_6;

    @FXML
    private Label lb_prn;

    @FXML
    private DatePicker dob1;

    public void onSubmitButton(javafx.event.ActionEvent event){
        Connection c = null;
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalDB","postgres","Shrinivas1@");
            System.out.println("Connected to the database");
            String name = tf_1.getText();
            String contact = tf_2.getText();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
            String dob = (dob1.getValue()).format(formatter);
            String b_grp = blood_grp.getValue();
            String address = tf_5.getText();
            String email_id = tf_6.getText();
            String branchname = branch.getValue();
            Pattern p = Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+");
            Matcher m = p.matcher(email_id);
            if(contact.length() == 10){
                if(m.find() && m.group().equals(email_id)){
                    String countQuery = "SELECT COUNT(*) FROM student";
                    Statement stmt = c.createStatement();
                    ResultSet rs = stmt.executeQuery(countQuery);
                    rs.next();
                    int count = rs.getInt(1);
                    String prn = "120A"+count;
                    PreparedStatement pstmt = c.prepareStatement("INSERT INTO student(prn,name,email_id,dob,blood_group,address,contact,branch) VALUES(?,?,?,?,?,?,?,?)");
                    pstmt.setString(1,prn);
                    pstmt.setString(2,name);
                    pstmt.setString(3,email_id);
                    pstmt.setString(4,dob);
                    pstmt.setString(5,b_grp);
                    pstmt.setString(6,address);
                    pstmt.setString(7,contact);
                    pstmt.setString(8,branchname);
                    pstmt.executeUpdate();
                    System.out.println("Record Inserted Successfully");
                    pstmt.close();
                    c.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Records Inserted Successful");
                    alert.setContentText("Your PRN is "+prn+". Please note this PRN for further enquires");
                    alert.setTitle("Confirmation");
                    alert.showAndWait();
                }
                else
                    lb_prn.setText("Enter Valid Email ID");
            }
            else
                lb_prn.setText("Enter Valid Contact Number");
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void onCancelButton(javafx.event.ActionEvent event){
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        branch.setItems(branchName);
        blood_grp.setItems(bg);
    }
}
