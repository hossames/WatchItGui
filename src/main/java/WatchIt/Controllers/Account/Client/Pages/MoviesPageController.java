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
import src.DataBase.DataObjectController;

import java.util.ArrayList;
import java.util.List;

public class MoviesPageController {
    List<Node> nodeList = new ArrayList<>();
    @FXML
    private ScrollPane Container;

    @FXML
    private ChoiceBox<?> Country;

    @FXML
    private ChoiceBox<?> Genre;

    @FXML
    private ChoiceBox<?> Language;

    @FXML
    private AnchorPane FullContainer;

    @FXML
    private HBox Popular;

    @FXML
    private HBox Recent;

    @FXML
    private VBox SearchResultContainer;

    @FXML
    private ScrollPane SearchResultScroll;

    @FXML
    private HBox TopRated;

    @FXML
    private GridPane View;

    @FXML
    private ChoiceBox<?> Year;

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
        System.out.println(Container.getWidth()+" "+((AnchorPane)(nodeList.get(0))).getWidth());
        for(var node : nodeList)
            HandleGrid.setToGrid(View,(int)Container.getWidth(),node);
    }

    public void initialize() {
        Container.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                SetToGrid();
            }
        });
        nodeList = DataObjectController.MakeDataObjectController(DataBase.getInstance().moviesData.ConvertListDataObject());
        SearchResultScroll.visibleProperty().bind(SearchField.focusedProperty().or(SearchResultScroll.focusedProperty()));
    }
}
