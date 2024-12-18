package WatchIt.Controllers.Account.Admin.Page;

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

public class ContentShowController {
    DataObjectController<? extends  DataObject> objectController;
    List<Node> nodeList = new ArrayList<>();
    @FXML
    private ScrollPane Container;

    @FXML
    private VBox SearchResultContainer;

    @FXML
    private ScrollPane SearchResultScroll;

    @FXML
    private GridPane View;

    @FXML
    private TextField SearchField;

    @FXML
    void Search(MouseEvent event) {

    }

    public ContentShowController(DataObjectController<? extends DataObject> controller) {
        objectController = controller;
    }
    public void SetToGrid(){
        View.getChildren().clear();
        for(var node : nodeList)
            HandleGrid.setToGrid(View,(int)Container.getWidth(),node);
        System.out.println(Container.getHeight());
    }

    public void initialize() {
        Container.widthProperty().addListener((Observable,oldValue,newValue) -> {
                SetToGrid();
        });
        nodeList = DataObjectController.MakeNodeList(objectController.ConvertListDataObject());
        SearchResultScroll.visibleProperty().bind(SearchField.focusedProperty().or(SearchResultScroll.focusedProperty()));
    }
}
