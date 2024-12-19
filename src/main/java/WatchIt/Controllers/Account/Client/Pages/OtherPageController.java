package WatchIt.Controllers.Account.Client.Pages;

import WatchIt.Controllers.Account.Client.Helps.HandleGrid;
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

public class OtherPageController {
    List<DataObject> nodeList = new ArrayList<>();
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
    void Search() {
        SearchResultContainer.getChildren().clear();
        SearchResultScroll.setMinHeight(0);
        SearchResultScroll.setMaxHeight(0);
        nodeList = dataObjectController.ConvertToDataObject().getDataThatContains(SearchField.getText(),2);
        for(var node : nodeList) {
            try {
                SearchResultScroll.setMinHeight(Math.min(SearchResultScroll.getMinHeight()+80,SearchResultContainer.getPrefHeight()));
                SearchResultScroll.setMinHeight(Math.min(SearchResultScroll.getMinHeight()+80,SearchResultContainer.getPrefHeight()));
                SearchResultContainer.getChildren().add(MainView.SearchedRow(node).load());
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }


    public void SetToGrid(){
        View.getChildren().clear();
        for(var node : nodeList)
            HandleGrid.setToGrid(View,(int)Container.getWidth(),node.getNode());
    }

    public void initialize() {
        Container.widthProperty().addListener((observable, oldValue, newValue) -> {
            SetToGrid();
        });
        nodeList =dataObjectController.ConvertListDataObject();
        SearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            Search();
        });
        SearchField.addEventHandler(KeyEvent.KEY_TYPED,(KeyEvent event)->{
            if(event.getCharacter().charAt(0)==System.lineSeparator().charAt(0)){
                SetToGrid();
            }
        });
        SearchField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if((newValue||SearchResultContainer.isFocused()) && nodeList.size()>0){
                SearchResultScroll.setVisible(true);
            }else{
                SearchResultScroll.setVisible(false);
            }
        });
    }

    public OtherPageController(DataObjectController<? extends  DataObject> dataObjectController) {
        this.dataObjectController = dataObjectController;
    }
}
