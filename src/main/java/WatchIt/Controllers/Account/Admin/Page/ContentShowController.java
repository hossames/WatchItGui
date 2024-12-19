package WatchIt.Controllers.Account.Admin.Page;

import WatchIt.Controllers.Account.Client.Helps.HandleGrid;
import WatchIt.Controllers.Account.Client.Searchable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.DataBase.DataBase;
import src.DataBase.DataObject;
import src.DataBase.DataObjectController;

import java.util.ArrayList;
import java.util.List;

public class ContentShowController extends Searchable {
    public ContentShowController(DataObjectController<? extends DataObject> controller) {
        dataObjectController = controller;
    }
    public void initialize() {
        Container.widthProperty().addListener((Observable,oldValue,newValue) -> {
                SetToGrid();
        });
        initSearchEngine();
    }
}
