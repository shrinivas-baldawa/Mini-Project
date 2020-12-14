package All_Files;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Student_Council implements Initializable {
    @FXML
    private ComboBox<String> cb;
    ObservableList<String> teamNames = FXCollections.observableArrayList("Technical Team","Media Team","Cultural Team","Literary Team","IEEE","SIESGSTAreana","Astrionics","DSC","IETE");

    @FXML
    private Label lb2;

    @FXML
    private Label lb3;

    @FXML
    private Label lb4;

    @FXML
    private Label lb1;

    public ObservableList<String> getTeamNames() {
        return teamNames;
    }

    public void setTeamNames(ObservableList<String> teamNames) {
        this.teamNames = teamNames;

    }
    @FXML
    void OnAction(ActionEvent event) {
        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database");
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/FinalDB","postgres","Shrinivas1@");
            System.out.println("Connected to the database");
            String enteredTeamName = cb.getValue();
            PreparedStatement pstmt = c.prepareStatement("Select * from Student_Council where team = ?");
            pstmt.setString(1,enteredTeamName);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                lb1.setText(rs.getString(1));
                lb2.setText(rs.getString(3));
                lb3.setText(rs.getString(4));
                lb4.setText(rs.getString(2));
            }
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cb.setItems(teamNames);
    }
}
