package WatchIt.Controllers.Account.Admin;

import WatchIt.Models.Model;
import WatchIt.Views.AdminView;
import WatchIt.Views.MainView;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import src.DataBase.DataBase;

import java.io.IOException;

public class AdminController {
    @FXML
    private BorderPane AdminBorderPane;

    @FXML
    private Text Cast;

    @FXML
    private ScrollPane Container;

    @FXML
    private Text DashBoard;

    @FXML
    private Text Episode;

    @FXML
    private Text HeadTitle;

    @FXML
    private Text Movies;

    @FXML
    private Text Series;

    @FXML
    private Text Settings;

    @FXML
    private Text Users;

    FXMLLoader fxmlLoader;
    public AdminController(FXMLLoader fxmlLoader) {
        this.fxmlLoader = fxmlLoader;
    }
    public void initialize() {
        try {
            AdminBorderPane.setTop(MainView.TitleBar().load());
            AdminBorderPane.setCenter(fxmlLoader.load());
            DashBoard.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent event) -> {
                Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.DashBoard()));
            });
            Movies.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
                Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.MoviesPage()));
            });
            Series.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
                Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.SeriesPage()));
            });
            Cast.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
                Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.CastPage()));
            });
            Episode.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
                Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.EpisodePage()));
            });
            Users.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
                Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.UsersPage()));
            });
            Settings.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
                Model.getInstance().getViewFactory().Show(AdminView.AdminScene(MainView.SettingsPage()));
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    void LogOut(MouseEvent event) {
        DataBase.getInstance().CurrentUser=null;
        Model.getInstance().getViewFactory().Show(MainView.LoginScene());
    }
}
