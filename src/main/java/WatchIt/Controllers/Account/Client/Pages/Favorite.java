package WatchIt.Controllers.Account.Client.Pages;
import WatchIt.Controllers.Account.Client.Searchable;
import javafx.scene.control.ComboBox;
import src.DataBase.DataBase;
import src.DataBase.DataObject;
import src.DataBase.DataObjectController;
public class Favorite extends Searchable {
    public ComboBox<String> SearchType;
    public Favorite(DataObjectController<? extends DataObject> controller) {
        dataObjectController = controller;
    }
    public void initialize() {
        Container.widthProperty().addListener((Observable,oldValue,newValue) -> {
            SetToGrid();
        });
        SearchType.getItems().addAll("movies","series","cast");
        SearchType.getSelectionModel().selectFirst();
        SearchType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equalsIgnoreCase("series")){
                dataObjectController = DataBase.getInstance().seriesData;
            }else if(newValue.equalsIgnoreCase("movies")){
                dataObjectController = DataBase.getInstance().moviesData;
            }else{
                dataObjectController = DataBase.getInstance().castMemberData;
            }
            Search();
            SetToGrid();
        });
        initSearchEngine();
    }
}
