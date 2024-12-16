package WatchIt.Controllers.Account.Client.Pages.InnerPageComponentControllers;

import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import javafx.scene.control.Hyperlink;
import src.DataBase.DataBase;
import src.DataBase.DataObject;
import src.DataBase.DataObjectController;

import java.util.ArrayList;
import java.util.List;

public class GenreComponentController {
    public Hyperlink Genre;
    String genre;
    public GenreComponentController(String genre){
        this.genre=genre;
    }
    public void initialize(){
        Genre.setText(genre);
    }
    public void GoToGenrePage(){
        List<DataObject>list = new ArrayList<>();
        DataBase.contentsData.getData().stream().filter(item -> item.genres.contains(genre)).forEach(list::add);
        try {
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
