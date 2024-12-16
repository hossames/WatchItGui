package WatchIt.Controllers.Account.Client.Pages.InnerPageComponentControllers;

import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.ContentControl.Episode;
import src.DataBase.DataObject;

import java.text.DateFormatSymbols;

public class EpisodeComponentController {
    public Text EpisodeTitle,EpisodeNum,date;
    DataObject ep;
    public EpisodeComponentController(DataObject content) {
        ep = content;
    }
    public void initialize() {
        Episode Ep = (Episode) ep;
        EpisodeTitle.setText(Ep.getEpisodeTitle());
        EpisodeNum.setText(Integer.valueOf(Ep.getEpisodeNumber()).toString());
        date.setText(String.valueOf(Ep.getreleaseDate().getYear()+1900)+"/"+String.valueOf(Ep.getreleaseDate().getDay())+
                new DateFormatSymbols().getMonths()[Ep.getreleaseDate().getMonth()]);
    }
    public void GoToPage(MouseEvent event){
    }
}
