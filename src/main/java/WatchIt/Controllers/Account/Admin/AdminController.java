package WatchIt.Controllers.Account.Admin;

import WatchIt.Views.MainView;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
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
    private MaterialIconView Exit;

    @FXML
    private Text HeadTitle;

    @FXML
    private Text Logout;

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
}
