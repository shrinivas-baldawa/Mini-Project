package All_Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AdminLogin {
    @FXML
    private Button view;

    @FXML
    private Button update;

    @FXML
    void onClickUpdate(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin_UpdateNew.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Admin Login");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            System.out.println("Window could not be loaded");
        }
    }

    @FXML
    void onClickView(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Admin_ViewNew.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Admin Login");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            System.out.println("Window could not be loaded");
        }
    }
}
