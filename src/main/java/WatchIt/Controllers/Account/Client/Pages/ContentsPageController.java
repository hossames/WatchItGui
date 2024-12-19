package WatchIt.Controllers.Account.Client.Pages;

import WatchIt.Controllers.Account.Client.Helps.HandleGrid;
import WatchIt.Controllers.Account.Client.Searchable;
import WatchIt.Views.MainView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.DataBase.DataBase;
import src.DataBase.DataObject;
import src.DataBase.DataObjectController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContentsPageController extends Searchable {
    @FXML
    private ChoiceBox<String> Country;

    @FXML
    private ChoiceBox<String> Genre;

    @FXML
    private ChoiceBox<String> Language;

    @FXML
    private AnchorPane FullContainer;

    @FXML
    private HBox Popular;

    @FXML
    private HBox Recent;

    @FXML
    private HBox TopRated;

    @FXML
    private ChoiceBox<Integer> Year;


    public void initialize() {
        Container.widthProperty().addListener((observable, oldValue, newValue) -> {
                SetToGrid();
        });
        initSearchEngine();
    }

    public ContentsPageController(DataObjectController<? extends  DataObject> dataObjectController) {
        this.dataObjectController = dataObjectController;
    }
}
