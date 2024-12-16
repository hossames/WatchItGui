package WatchIt.Controllers.Account.Admin.Page;

import WatchIt.Models.Model;
import WatchIt.Views.AdminView;
import WatchIt.Views.MainView;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import src.Cast.CastMember;
import src.ContentControl.Movie;
import src.DataBase.DataBase;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CreateMovieController {
    public Button AddGenre;
    public ChoiceBox<String> CastMembers;
    public TextField ContentTitle,Duration;
    public TextField Country;
    public ChoiceBox<String> Genres;
    public TextField Language;
    public TextField Budget;
    public TextField Revenue;
    public DatePicker dateOfProduction;
    List<String>genres = new ArrayList<>(),castmembers=new ArrayList<>();
    List<String> Converter(){
        List<String> list1 = new ArrayList<>();
        for(CastMember castMember: DataBase.getInstance().castMemberData.getData()){
            list1.add(castMember.getName(2));
        }
        return list1;
    }

    public void initialize(){
        Genres.setItems(FXCollections.observableList(Arrays.stream(DataBase.getInstance().genres).toList()));
        CastMembers.setItems(FXCollections.observableList(Converter()));
    }
    public void Add(MouseEvent event) {
        Button s = (Button)event.getSource();
        if(s.equals(AddGenre)){
            if(!genres.contains(Genres.getSelectionModel().getSelectedItem())) {
                genres.add(Genres.getSelectionModel().getSelectedItem());
            }
        }else{
            if(!castmembers.contains(CastMembers.getSelectionModel().getSelectedItem())) {
                castmembers.add(CastMembers.getSelectionModel().getSelectedItem());
            }
        }
    }
    public void Done(MouseEvent event) {
        Movie movie = new Movie(ContentTitle.getText(),Language.getText(),Country.getText(),
                Integer.parseInt(Budget.getText()),Integer.parseInt(Revenue.getText()),
                Integer.parseInt(Duration.getText()),genres,castmembers,
                Date.from(dateOfProduction.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        DataBase.getInstance().moviesData.addData(movie);
    }
    public void GoToNext(KeyEvent keyEvent) {
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0)) {
            if(ContentTitle.isFocused()){
                Language.requestFocus();
            }
            else if(Language.isFocused()){
                Country.requestFocus();
            }else if (Country.isFocused()){
                Budget.requestFocus();
            }else if (Budget.isFocused()){
                Revenue.requestFocus();
            } else if (Revenue.isFocused()) {
                Duration.requestFocus();
            } else {
                dateOfProduction.requestFocus();
            }
        }
    }

}
