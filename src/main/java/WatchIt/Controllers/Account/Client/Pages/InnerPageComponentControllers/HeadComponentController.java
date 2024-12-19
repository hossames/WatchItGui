package WatchIt.Controllers.Account.Client.Pages.InnerPageComponentControllers;

import WatchIt.Views.ClientView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import src.AccountControl.User;
import src.ContentControl.Content;
import src.ContentControl.Movie;
import src.ContentControl.WatchRecord;
import src.DataBase.DataBase;
import src.DataBase.DataObject;

import java.io.IOException;
import java.text.DateFormatSymbols;

public class HeadComponentController {

    @FXML
    private Text Country;

    @FXML
    private Text Duration;

    @FXML
    private HBox Genre;

    @FXML
    private ImageView Image;

    @FXML
    private Text Language;

    @FXML
    private Text Name;

    @FXML
    private Text Rate;

    @FXML
    private Text ReleaseDate;

    @FXML
    private HBox Stars;

    @FXML
    private Text Views;

    DataObject dataObject;
    Content content;
    public HeadComponentController(DataObject dataObject) {
        this.dataObject = dataObject;
    }
    public void initialize(){
       content = (Content) dataObject;
       WatchRecord watchRecord = DataBase.watchRecordData.getDataByObject(new WatchRecord(-1F, content.contentTitle));
        if(watchRecord==null){
            content.AddRate(-1);
        }else{
            int cnt=0;
            System.out.println(watchRecord.Rating);
            for (var star : Stars.getChildren()) {
                if(cnt==watchRecord.Rating)
                    break;
                FontAwesomeIconView fontAwesomeIconView = (FontAwesomeIconView) star;
                fontAwesomeIconView.setIcon(FontAwesomeIcon.STAR);
                cnt++;
            }
        }
        Country.setText(content.country);
        Language.setText(content.language);
        Name.setText(content.contentTitle);
        if(content instanceof Movie movie){
            Duration.setText(String.valueOf(movie.getDuration())+'m');
        }else {
            Duration.setText("40m");
        }
        Image.setImage(content.getImage());
        Rate.setText(String.valueOf(content.TotalRate()));
        ReleaseDate.setText(DateFormatSymbols.getInstance().getMonths()[content.datePublished.getMonth()]+"/"+String.valueOf(1900+content.datePublished.getYear()));
        content.genres.stream().forEach(gen -> {
            try {
                Genre.getChildren().add(ClientView.GenreComponent(gen).load());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Views.setText(String.valueOf(content.Viewers));
    }

    @FXML
    void Click(MouseEvent event) {
        float rate=0;
        boolean ok = false;
        for (var star : Stars.getChildren()) {
            FontAwesomeIconView fontAwesomeIconView = (FontAwesomeIconView) star;
            if(ok==false) {
                fontAwesomeIconView.setIcon(FontAwesomeIcon.STAR);
                rate++;
            }
            else{
                fontAwesomeIconView.setIcon(FontAwesomeIcon.STAR_ALT);
            }
            if (event.getSource().equals(star)) {
                ok=true;
            }
        }
        content.EditRate(rate);
    }

    @FXML
    void SetWatchLater(MouseEvent event) {
        User user = (User)(DataBase.getInstance().CurrentUser);
        for(var item : user.getWatchLater()) {
            if (item.equals(dataObject.getName(2))) {
                return;
            }
        }
        user.getWatchLater().add(dataObject.getName(2));
    }
    @FXML
    void setFavorite(MouseEvent event) {

    }

}
