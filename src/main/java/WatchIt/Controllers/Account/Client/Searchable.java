package WatchIt.Controllers.Account.Client;

import WatchIt.Controllers.Account.Client.Helps.HandleGrid;
import WatchIt.Views.MainView;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import src.DataBase.DataObject;
import src.DataBase.DataObjectController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Searchable {
    public List<DataObject> nodeList = new ArrayList<>();
    public DataObjectController<? extends DataObject> dataObjectController;
    public VBox SearchResultContainer;
    public ScrollPane Container;
    public GridPane View;
    public ScrollPane SearchResultScroll;
    public  TextField SearchField;

    public void Search() {
        SearchResultContainer.getChildren().clear();
        SearchResultScroll.setMinHeight(0);
        SearchResultScroll.setMaxHeight(0);
        nodeList = dataObjectController.ConvertToDataObject().getDataThatContains(SearchField.getText(),3);
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

    @FXML
    void User(MouseEvent event) {
    }

    public void SetToGrid(){
        View.getChildren().clear();
        for(var node : nodeList) {
            HandleGrid.setToGrid(View, (int) Container.getWidth(), node.getNode());
        }
    }

    public void initSearchEngine() {
        nodeList = dataObjectController.ConvertListDataObject();
        SearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            Search();
        });
        SearchField.addEventHandler(KeyEvent.KEY_TYPED,(KeyEvent event)->{
            if(event.getCharacter().charAt(0)==System.lineSeparator().charAt(0)){
                SetToGrid();
            }
        });
        SearchField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if((newValue||SearchResultScroll.isFocused()) && nodeList.size()>0){
                SearchResultScroll.setVisible(true);
            }else{
                SearchResultScroll.setVisible(false);
            }
        });
    }

}
