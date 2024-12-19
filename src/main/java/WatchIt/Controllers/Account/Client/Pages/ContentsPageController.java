package WatchIt.Controllers.Account.Client.Pages;

import WatchIt.Controllers.Account.Client.Helps.HandleGrid;
import WatchIt.Controllers.Account.Client.Searchable;
import WatchIt.Controllers.Utils.sortDate;
import WatchIt.Controllers.Utils.sortRate;
import WatchIt.Controllers.Utils.sortWatch;
import WatchIt.Views.MainView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import src.ContentControl.Content;
import src.DataBase.DataBase;
import src.DataBase.DataObject;
import src.DataBase.DataObjectController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ContentsPageController extends Searchable {
    @FXML
    private ComboBox<String> Country;

    @FXML
    private ComboBox<String> Genre;

    @FXML
    private ComboBox<String> Language;

    @FXML
    private ComboBox<String> Year;

    @FXML
    private AnchorPane FullContainer;

    @FXML
    private HBox Popular;

    @FXML
    private HBox Recent;

    @FXML
    private HBox TopRated;


    public void initialize() {
        String[] languages = new String[]{"Any","English","France","Spanish","Arabic","Russian","Japanese","German","Mandarin Chinese","Hindi","Portuguese"};
        Language.getItems().addAll(languages);
        Language.getSelectionModel().selectFirst();
        String[] countries = {"Any","United States", "India", "China", "France", "United Kingdom", "Japan", "Germany", "Spain", "Brazil", "Canada", "Russia"};
        Country.getItems().addAll(countries);
        Country.getSelectionModel().selectFirst();
        Year.getItems().add("Any");
        for (int i = 1960 ; i<2025 ;i++){
            Year.getItems().add(String.valueOf(i));
        }
        Year.getSelectionModel().selectFirst();
        Genre.getItems().add("Any");
        Genre.getItems().addAll(DataBase.getInstance().genres);
        Genre.getSelectionModel().selectFirst();
        Language.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ApplyFilters();
        });
        Genre.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ApplyFilters();
        });
        Year.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ApplyFilters();
        });
        Country.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            ApplyFilters();
        });
        Container.widthProperty().addListener((observable, oldValue, newValue) -> {
                SetToGrid();
        });
        initSearchEngine();
    }

    public void ApplyFilters() {
        String language = Language.getValue().toLowerCase();
        String country = Country.getValue().toLowerCase();
        String genre = Genre.getValue().toLowerCase();
        String year = Year.getValue().toLowerCase();
        nodeList.clear();
        dataObjectController.getData().stream().forEach(item -> {
            Content content = (Content) item;
            boolean GenreSelected = genre.equals("any");
            if(!GenreSelected) {
                for (var genreItem : content.genres) {
                    if (genreItem.toLowerCase().equals(genre)) {
                        GenreSelected = true;
                        break;
                    }
                }
            }
            boolean languageSelected = language.equals("any")||content.language.toLowerCase().equals(language);
            boolean YearSelected = year.equals("any");
            if(!YearSelected) {
                int yearValue = Integer.parseInt(year);
                if(content.datePublished.getYear()+1900==yearValue) {
                    YearSelected = true;
                }
            }
            boolean CountrySelected = country.equals("any")||content.country.toLowerCase().equals(country);
            if(CountrySelected&&GenreSelected&&languageSelected&&YearSelected) {
                nodeList.add(content);
            }
        });
        if(Recent.getStyleClass().contains("selected")){
            nodeList.sort(new sortDate());
        }
        if(TopRated.getStyleClass().contains("selected")){
            nodeList.sort(new sortRate());
        }
        if(Popular.getStyleClass().contains("selected")){
            nodeList.sort(new sortWatch());
        }
        SetToGrid();
    }
    public void SortBy(MouseEvent event){
        if(event.getSource().equals(Recent)){
            Recent.getStyleClass().add("selected");
            TopRated.getStyleClass().remove("selected");
            Popular.getStyleClass().remove("selected");
            nodeList.sort(new sortDate());
        }
        else if(event.getSource().equals(TopRated)){
            TopRated.getStyleClass().add("selected");
            Recent.getStyleClass().remove("selected");
            Popular.getStyleClass().remove("selected");
            nodeList.sort(new sortRate());
        }
        else if(event.getSource().equals(Popular)){
            Popular.getStyleClass().add("selected");
            Recent.getStyleClass().remove("selected");
            TopRated.getStyleClass().remove("selected");
            nodeList.sort(new sortWatch());
        }
        SetToGrid();
    }
    public ContentsPageController(DataObjectController<? extends  DataObject> dataObjectController) {
        this.dataObjectController = dataObjectController;
    }
}
