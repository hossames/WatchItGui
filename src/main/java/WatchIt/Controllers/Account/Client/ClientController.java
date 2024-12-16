package WatchIt.Controllers.Account.Client;
import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import WatchIt.Views.MainView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import de.jensd.fx.glyphs.materialicons.MaterialIconView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class ClientController {

    @FXML
    private BorderPane ClientBorderPane;

    @FXML
    private FontAwesomeIconView CoffeeIcon;

    @FXML
    private Text ContentName;

    @FXML
    private Text ContentName12;

    @FXML
    private MaterialIconView Exit;

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
    private Text Logout;

    @FXML
    private Text Settings;

    @FXML
    private MaterialIconView Trend;

    @FXML
    private Text TrendText;

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

    @FXML
    void OpenInnerPage(MouseEvent event) {

    }

    public void initialize(){
        try {
            ClientBorderPane.setTop(MainView.TitleBar().load());
            Container.setContent(node);
            ClientBorderPane.widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    Container.setMinWidth(t1.doubleValue()-((AnchorPane)ClientBorderPane.getLeft()).getWidth());
                    Container.setMaxWidth(t1.doubleValue()-((AnchorPane)ClientBorderPane.getLeft()).getWidth());
                    System.out.println("--------"+Container.widthProperty().get());
                }
            });
            Container.widthProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                    if(Container.getContent() instanceof  VBox vBox) {
                        vBox.setMaxWidth(t1.doubleValue());
                        vBox.setMinWidth(t1.doubleValue());
                    } else if (Container.getContent() instanceof  AnchorPane anchorPane) {
                        System.out.println("ok");
                        anchorPane.setMaxWidth(t1.doubleValue());
                        anchorPane.setMinWidth(t1.doubleValue());
                    }
                }
            });
            Home.addEventHandler(MouseEvent.MOUSE_CLICKED,(MouseEvent e)->{
                Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.MainPage()));
            });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
