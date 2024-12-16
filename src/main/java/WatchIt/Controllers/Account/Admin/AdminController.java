package WatchIt.Controllers.Account.Admin;
import WatchIt.Models.Model;
import WatchIt.Views.AdminView;
import WatchIt.Views.MainView;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import src.AccountControl.Account;
import src.Cast.CastMember;
import src.ContentControl.*;
import src.DataBase.DataBase;

import java.io.IOException;

public class AdminController {
    public BorderPane AdminBorderPane;
    public Button DashBoard;
    public Button Episodes;
    public Button Movies;
    public Button Seriess;
    public Button Users;
    public Button CastMembers;
    FXMLLoader fxmlLoader;
    public AdminController(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }
    public void routing(MouseEvent mouseEvent){

    }
    public void initialize() {
        try {
            AdminBorderPane.setTop(MainView.TitleBar().load());
            AdminBorderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void Profile(MouseEvent e){
        Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.Profile()));
    }
}
