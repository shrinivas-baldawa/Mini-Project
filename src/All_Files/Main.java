package All_Files;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainLoginNew.fxml"));
        primaryStage.setTitle("SIES Graduate School Of Technology, Navi Mumbai");
        primaryStage.setScene(new Scene(root, 620, 390));
        primaryStage.show();
    }

    @FXML
    void onSubmit(ActionEvent event){
        Connection c = null;
        String getUsername="",getPassword="";
        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalDB","postgres","Shrinivas1@");
            System.out.println("Connected to Database");
            PreparedStatement pstmt = c.prepareStatement("SELECT username,password FROM login WHERE username = ? AND password = ?");
            pstmt.setString(1,username.getText());
            pstmt.setString(2, password.getText());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                getUsername = rs.getString("username");
                getPassword = rs.getString("password");
            }
            if(getUsername.equals(username.getText()) && getPassword.equals(password.getText())){
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminLoginNew.fxml"));
                    System.out.println("Admin Login Successful");
                    Parent root1 = fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle("Admin Login");
                    stage.setScene(new Scene(root1));
                    stage.show();
                }
                catch (Exception e){
                    e.printStackTrace();
                    System.out.println("Window could not be loaded");
                }
            }
            else{
                try{
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StudentLoginNew.fxml"));
                    System.out.println("Student Login Successful");
                    Parent root1 = fxmlLoader.load();
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
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    @FXML
    void onCancel(ActionEvent event) {
        username.setText("");
        password.setText("");
    }
}
