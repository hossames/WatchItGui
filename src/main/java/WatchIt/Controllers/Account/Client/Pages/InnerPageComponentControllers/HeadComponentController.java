package WatchIt.Controllers.Account.Client.Pages.InnerPageComponentControllers;

import WatchIt.Models.FavoritesModel;
import WatchIt.Views.ClientView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import src.AccountControl.User;
import src.ContentControl.*;
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

    @FXML
    private  FontAwesomeIconView Heart;
    DataObject dataObject;
    public HeadComponentController(DataObject dataObject) {
        this.dataObject = dataObject;
    }
    public void initialize(){
        if(dataObject instanceof Content content) {
            WatchRecord watchRecord = DataBase.watchRecordData.getDataByObject(new WatchRecord(-1F, content.contentTitle));
            if (watchRecord == null) {
                content.AddRate(-1);
            } else {
                int cnt = 0;
                System.out.println(watchRecord.Rating);
                for (var star : Stars.getChildren()) {
                    if (cnt == watchRecord.Rating)
                        break;
                    FontAwesomeIconView fontAwesomeIconView = (FontAwesomeIconView) star;
                    fontAwesomeIconView.setIcon(FontAwesomeIcon.STAR);
                    cnt++;
                }
            }
            Country.setText(content.country);
            Language.setText(content.language);
            Name.setText(content.contentTitle);
            if (content instanceof Movie movie) {
                Duration.setText(String.valueOf(movie.getDuration()) + 'm');
            } else {
                Duration.setText("40m");
            }
            Image.setImage(content.getImage());
            Rate.setText(String.valueOf(content.TotalRate()));
            ReleaseDate.setText(DateFormatSymbols.getInstance().getMonths()[content.datePublished.getMonth()] + "/" + String.valueOf(1900 + content.datePublished.getYear()));
            content.genres.stream().forEach(gen -> {
                try {
                    Genre.getChildren().add(ClientView.GenreComponent(gen).load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            Views.setText(String.valueOf(content.Viewers));
        }else if(dataObject instanceof Episode episode){
            Name.setText(episode.getName(1));
            ReleaseDate.setText(DateFormatSymbols.getInstance().getMonths()[episode.getDate().getMonth()] + "/" + String.valueOf(1900 + episode.getDate().getYear()));
            Image.setImage(episode.getImage());
            Rate.setText(String.valueOf(episode.TotalRate()));
            Duration.setText(String.valueOf(episode.getDuration()));
            Series content = DataBase.getInstance().seriesData.getDataByString(episode.getSeriesName(),0).getFirst();
            Country.setText(content.country);
            Language.setText(content.language);
            Views.setText(String.valueOf(episode.RateCounter));
            content.genres.stream().forEach(gen -> {
                try {
                    Genre.getChildren().add(ClientView.GenreComponent(gen).load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            WatchRecord watchRecord = DataBase.watchRecordData.getDataByObject(new WatchRecord(-1F, episode.getName(2)));
            System.out.println(watchRecord);
            if (watchRecord == null) {
                episode.AddRate(-1);
            } else {
                int cnt = 0;
                for (var star : Stars.getChildren()) {
                    if (cnt == watchRecord.Rating)
                        break;
                    FontAwesomeIconView fontAwesomeIconView = (FontAwesomeIconView) star;
                    fontAwesomeIconView.setIcon(FontAwesomeIcon.STAR);
                    cnt++;
                }
            }
        }
        FavoritesModel favoritesModel = new FavoritesModel(dataObject.getName(2), DataBase.getInstance().CurrentUser.getId(0).intValue(),0);
        if(DataBase.getInstance().Favorites.getDataByObject(favoritesModel)!=null){
            Heart.setIcon(FontAwesomeIcon.HEART);
        }
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
        ((Rateable)dataObject).EditRate(rate);
    }

    @FXML
    void setWatchLater(MouseEvent event) {
        User user = (User)(DataBase.getInstance().CurrentUser);
        for(var item : user.getWatchLater()) {
            if (item.equals(dataObject.getName(2))) {
                return;
            }
        }
        user.setWatchLater(dataObject.getName(2));
    }
    public void setFavorite(MouseEvent event) throws InterruptedException {
        FavoritesModel favoritesModel = new FavoritesModel(dataObject.getName(2), DataBase.getInstance().CurrentUser.getId(0).intValue(),0);
        if(DataBase.getInstance().Favorites.getDataByObject(favoritesModel)==null){
            DataBase.getInstance().Favorites.addData(favoritesModel);
            Heart.setIcon(FontAwesomeIcon.HEART);
            System.out.println("Maked Heart");
        }else{
            System.out.println("Maked Heart Alt");

            Thread.sleep(100);
            DataBase.getInstance().Favorites.removeData(favoritesModel);
            Heart.setIcon(FontAwesomeIcon.HEART_ALT);
        }
    }

}
