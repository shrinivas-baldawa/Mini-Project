package All_Files;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Admin_Update {
    @FXML
    void onAddressClick(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Address_UpdateNew.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Address Updation");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            System.out.println("Window could not be loaded");
        }
    }

    @FXML
    void onContactClick(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Contact_UpdateNew.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Contact Updation");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            System.out.println("Window could not be loaded");
        }

    }

    @FXML
    void onEmailClick(ActionEvent event) {
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Email_UpdateNew.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Email Updation");
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch (Exception e){
            System.out.println("Window could not be loaded");
        }
    }
}
