package WatchIt.Controllers.Account.Admin.Page;

import WatchIt.Models.Model;
import WatchIt.Views.AdminView;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import src.AccountControl.Admin;
import src.ContentControl.Episode;
import src.ContentControl.Series;
import src.DataBase.DataBase;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CreateEpisodeController {
    public Button Add;
    public TextField Duration;
    public TextField EpisodeNumber;
    public TextField EpisodeTitle;
    public ComboBox<String> SeriesName;
    public DatePicker dateOfProduction;
    List<String> Converter(){
        List<String> list1 = new ArrayList<>();
        for(Series series: DataBase.getInstance().seriesData.getData()){
            list1.add(series.getName(2));
        }
        return list1;
    }

    public void initialize(){
        SeriesName.getItems().addAll(Converter());
    }
    public void Done(MouseEvent event) {
        Episode episode = new Episode(SeriesName.getSelectionModel().getSelectedItem(),EpisodeTitle.getText(),
                Integer.parseInt(EpisodeNumber.getText()),Integer.parseInt(Duration.getText()),
                Date.from(dateOfProduction.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
        ((Admin)DataBase.getInstance().CurrentUser).addEpisode(episode);
        Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.EpisodePage()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Create Episode");
        alert.setHeaderText(null);
        alert.setContentText("Create Episode Successful");
        alert.showAndWait();
    }
    public void GoToNext(KeyEvent keyEvent) {
        if(keyEvent.getCharacter().charAt(0)==System.lineSeparator().charAt(0)) {
            if(EpisodeTitle.isFocused()){
                EpisodeNumber.requestFocus();
            }
            else if(EpisodeNumber.isFocused()){
                Duration.requestFocus();
            }else {
                dateOfProduction.requestFocus();
            }
        }
    }

}
