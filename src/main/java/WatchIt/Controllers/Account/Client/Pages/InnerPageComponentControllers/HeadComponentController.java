package WatchIt.Controllers.Account.Client.Pages.InnerPageComponentControllers;
import WatchIt.Views.ClientView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import src.AccountControl.User;
import src.ContentControl.*;
import src.DataBase.DataBase;
import src.DataBase.DataObject;

import java.io.IOException;

public class HeadComponentController {
    public Text Country;
    public Text Duration;
    public HBox Genre;
    public ImageView Image;
    public Text Language;
    public Text Name;
    public Text Rate;
    public Text ReleaseDate;
    public Text Views;
    public FontAwesomeIconView s1;
    public FontAwesomeIconView s2;
    public FontAwesomeIconView s3;
    public FontAwesomeIconView s4;
    public FontAwesomeIconView s5;
    DataObject content;
    public  HeadComponentController(DataObject content) {
        this.content = content;
    }
    public void initialize() {
        Image.setImage(content.getImage());
        Name.setText(content.getName(2));
        Rate.setText(String.valueOf(((Rateable)content).TotalRate()));
        if(content instanceof Series content) {
            Views.setText(String.valueOf(((Content)(content)).Viewers));
            Duration.setText("40Min");
            Language.setText(content.language);
            ReleaseDate.setText(content.datePublished.toString());
            Country.setText(content.country);
            for(String genre : content.genres) {
                try {
                    Genre.getChildren().add(ClientView.GenreComponent(genre).load());
                }catch (IOException e) {
                    System.out.println("Problem in Genre Component");
                }
            }
        }
        else if(content instanceof Movie content){
            Views.setText(String.valueOf(((Content)(content)).Viewers));
            Duration.setText(Integer.valueOf(content.getDuration()).toString());
            Language.setText(content.language);
            ReleaseDate.setText(String.valueOf(content.datePublished.getYear()+1900));
            Country.setText(content.country);
            for(String genre : content.genres) {
                try {
                    Genre.getChildren().add(ClientView.GenreComponent(genre).load());
                }catch (IOException e) {
                    System.out.println("Problem in Genre Component");
                }
            }
        }
        else if(content instanceof Episode episode){
            Duration.setText(Integer.valueOf(episode.getDuration()).toString());
            DataBase db = DataBase.getInstance();
            Series content = db.seriesData.getDataByString(episode.getName(0),0).get(0);
            Language.setText(content.language);
            Views.setText(String.valueOf(content.Viewers));
            ReleaseDate.setText(String.valueOf(content.datePublished.getYear()+1900));
            Country.setText(content.country);
            for(String genre : content.genres) {
                try {
                    Genre.getChildren().add(ClientView.GenreComponent(genre).load());
                }catch (IOException e) {
                    System.out.println("Problem in Genre Component");
                }
            }
        }
        WatchRecord w = new WatchRecord(-1,Name.getText());
        if(DataBase.watchRecordData.getDataByObject(w)==null)
            DataBase.watchRecordData.addData(w);
        else{
            int rating =(int)DataBase.watchRecordData.getDataByObject(w).Rating.floatValue();
//            if(rating>0)
//                s1.setIcon(FontAwesomeIcon.STAR);
//            if(rating>1)
//                s2.setIcon(FontAwesomeIcon.STAR);
//            if(rating>2)
//                s3.setIcon(FontAwesomeIcon.STAR);
//            if(rating>3)
//                s4.setIcon(FontAwesomeIcon.STAR);
//            if(rating>4)
//                s5.setIcon(FontAwesomeIcon.STAR);
        }
        if(!((User)(DataBase.getInstance().CurrentUser)).getHistory().contains(content.getName(0))){
            ((User)(DataBase.getInstance().CurrentUser)).getHistory().add(content.getName(0));
        }
    }

    public void Rate(MouseEvent mouseEvent) {
//        int i = 1;
//        s1.setIcon(FontAwesomeIcon.STAR);
//        if (mouseEvent.getSource().equals(s1)) {
//            s2.setIcon(FontAwesomeIcon.STAR_ALT);
//            s3.setIcon(FontAwesomeIcon.STAR_ALT);
//            s4.setIcon(FontAwesomeIcon.STAR_ALT);
//            s5.setIcon(FontAwesomeIcon.STAR_ALT);
//            if(content instanceof Rateable movie) {
//                movie.EditRate(i);
//            }
//            return;
//        }
//        i++;
//        s2.setIcon(FontAwesomeIcon.STAR);
//        if (mouseEvent.getSource().equals(s2)) {
//            s3.setIcon(FontAwesomeIcon.STAR_ALT);
//            s4.setIcon(FontAwesomeIcon.STAR_ALT);
//            s5.setIcon(FontAwesomeIcon.STAR_ALT);
//            if(content instanceof Rateable movie) {
//                movie.EditRate(i);
//            }
//            return;
//        }
//        i++;
//        s3.setIcon(FontAwesomeIcon.STAR);
//        if (mouseEvent.getSource().equals(s3)) {
//            s4.setIcon(FontAwesomeIcon.STAR_ALT);
//            s5.setIcon(FontAwesomeIcon.STAR_ALT);
//            if(content instanceof Rateable movie) {
//                movie.EditRate(i);
//            }
//            return;
//        }
//        i++;
//        s4.setIcon(FontAwesomeIcon.STAR);
//        if (mouseEvent.getSource().equals(s4)) {
//            s5.setIcon(FontAwesomeIcon.STAR_ALT);
//            if(content instanceof Rateable movie) {
//                movie.EditRate(i);
//            }
//            return;
//        }
//        i++;
//        s5.setIcon(FontAwesomeIcon.STAR);
//        if(content instanceof Rateable movie) {
//            movie.EditRate(i);
//        }
    }
}
