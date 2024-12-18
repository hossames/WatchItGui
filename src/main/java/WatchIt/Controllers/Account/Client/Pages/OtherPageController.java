package WatchIt.Controllers.Account.Client.Pages;

import WatchIt.Controllers.Account.Client.Helps.HandleGrid;
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

public class OtherPageController {
    List<Node> nodeList = new ArrayList<>();
    DataObjectController<? extends DataObject> dataObjectController;
    @FXML
    private ScrollPane Container;

    @FXML
    private AnchorPane FullContainer;

    @FXML
    private VBox SearchResultContainer;

    @FXML
    private ScrollPane SearchResultScroll;

    @FXML
    private GridPane View;

    @FXML
    private TextField SearchField;

    @FXML
    void Notifications(MouseEvent event) {

    }

    @FXML
    void Search(MouseEvent event) {

    }

    @FXML
    void User(MouseEvent event) {
    }

    public void SetToGrid(){
        View.getChildren().clear();
        for(var node : nodeList)
            HandleGrid.setToGrid(View,(int)Container.getWidth(),node);
    }

    public void initialize() {
        Container.widthProperty().addListener((observable, oldValue, newValue) -> {
            SetToGrid();
        });
        nodeList = DataObjectController.MakeNodeList(dataObjectController.ConvertListDataObject());
        SearchResultScroll.visibleProperty().bind(SearchField.focusedProperty().or(SearchResultScroll.focusedProperty()));
    }

    public OtherPageController(DataObjectController<? extends  DataObject> dataObjectController) {
        this.dataObjectController = dataObjectController;
    }
}
