package WatchIt.Controllers.Account.Admin.Page;

import WatchIt.Models.Model;
import WatchIt.Views.AdminView;
import WatchIt.Views.MainView;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import src.Cast.CastMember;
import src.ContentControl.Series;
import src.DataBase.DataBase;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CreateSeriesController {
    public Button Add,AddGenre,AddCastMember;
    public ChoiceBox<String> CastMembers;
    public TextField ContentTitle,numberOfEpisodes;
    public TextField Country;
    public TextArea Story;
    public ChoiceBox<String> Genres;
    public TextField Language;
    public TextField Budget;
    public TextField Revenue;
    public DatePicker dateOfProduction,LastAirDate;
    public CheckBox onGoing;
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
        LastAirDate.setValue(LocalDate.now());
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
        Series series = new Series(ContentTitle.getText(),Language.getText(),Country.getText(),Story.getText(),
                Integer.parseInt(Budget.getText()),Integer.parseInt(Revenue.getText()),
                Integer.parseInt(numberOfEpisodes.getText()),((onGoing.isSelected())?1:0),genres,castmembers,
                Date.from(dateOfProduction.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()),
                Date.from(LastAirDate.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        DataBase.getInstance().seriesData.addData(series);
        Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.SeriesPage()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create series");
        alert.setHeaderText(null);
        alert.setContentText("Create Series Successful");
        alert.showAndWait();
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
                numberOfEpisodes.requestFocus();
            }else if (numberOfEpisodes.isFocused()) {
                Story.requestFocus();
            } else {
                dateOfProduction.requestFocus();
            }
        }
    }
    public void OnGoing(){
        LastAirDate.setVisible(!LastAirDate.isVisible());
    }
}
