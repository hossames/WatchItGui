package WatchIt.Controllers.Account.Client;
import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import WatchIt.Views.MainView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import src.DataBase.DataBase;

public class ClientController {

    @FXML
    private BorderPane ClientBorderPane;

    @FXML
    private FontAwesomeIconView CoffeeIcon;

    @FXML
    private Text ContentName;

    @FXML
    private Text Favorites;

    @FXML
    private FontAwesomeIconView Film;

    @FXML
    private FontAwesomeIconView Gear;

    @FXML
    private Text Genre;

    @FXML
    private Text HeadTitle;

    @FXML
    private FontAwesomeIconView Heart;

    @FXML
    private MaterialIconView History;

    @FXML
    private Text HistoryText;

    @FXML
    private Text Home;

    @FXML
    private Text Settings;

    @FXML
    private Text Year;

    @FXML
    private FontAwesomeIconView watchLater;

    @FXML
    private Text watchLaterText;

    @FXML
    private ScrollPane Container;

    Node node;
    public ClientController(Node node) {
        this.node = node;
    }

    public void initialize(){
        CoffeeIcon.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.MainPage()));
        });
        watchLaterText.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.WatchLater()));
        });
        HistoryText.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.History()));
        });
        Home.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.MainPage()));
        });
        Settings.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            Model.getInstance().getViewFactory().Show(ClientView.setNode(MainView.SettingsPage()));
        });
        Favorites.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
            Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.Favorite()));
        });
        try {
            ClientBorderPane.setTop(MainView.TitleBar().load());
            ClientBorderPane.setCenter(node);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    void LogOut(MouseEvent event) {
        DataBase.getInstance().CurrentUser=null;
        Model.getInstance().getViewFactory().Show(MainView.LoginScene());
    }
}
