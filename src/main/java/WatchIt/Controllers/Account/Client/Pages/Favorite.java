package WatchIt.Controllers.Account.Client.Pages;
import WatchIt.Controllers.Account.Client.Searchable;
import WatchIt.Models.FavoritesModel;
import javafx.scene.control.ComboBox;
import src.DataBase.DataBase;
import src.DataBase.DataObject;
import src.DataBase.DataObjectController;

import java.util.ArrayList;
import java.util.List;

public class Favorite extends Searchable {
    List<DataObjectController<? extends DataObject>> favorites=new ArrayList<>();
    public ComboBox<String> SearchType;
    public Favorite(DataObjectController<? extends DataObject> controller) {
        dataObjectController = controller;
    }
    public void initialize() {
        Container.widthProperty().addListener((Observable,oldValue,newValue) -> {
            SetToGrid();
        });
        List<String>Searched=new ArrayList<>();
        DataBase.getInstance().Favorites.getDateByNum(DataBase.getInstance().CurrentUser.getId(0),0)
                .stream().forEach(item -> {Searched.add(item.Name);});

        favorites.add(new DataObjectController<>('\0', DataBase.getInstance().moviesData.getDataByString(Searched,2)));
        favorites.add(new DataObjectController<>('\0', DataBase.getInstance().seriesData.getDataByString(Searched,2)));
        favorites.add(new DataObjectController<>('\0', DataBase.getInstance().castMemberData.getDataByString(Searched,2)));
        favorites.add(new DataObjectController<>('\0', DataBase.getInstance().episodesData.getDataByString(Searched,2)));
        dataObjectController = favorites.get(0);
        SetToGrid();
        SearchType.getItems().addAll("movies","series","cast","episodes");
        SearchType.getSelectionModel().selectFirst();
        SearchType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equalsIgnoreCase("series")){

                dataObjectController = favorites.get(1);
            }else if(newValue.equalsIgnoreCase("movies")){
                dataObjectController = favorites.get(0);
            }else if(newValue.equalsIgnoreCase("cast")){
                dataObjectController = favorites.get(2);
            }else{
                dataObjectController = favorites.get(3);
            }
            Search();
            SetToGrid();
        });
        initSearchEngine();
    }
}
