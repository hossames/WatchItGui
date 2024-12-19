package WatchIt.Controllers.Content;

import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.DataBase.DataObject;

import java.util.Date;

public class EpisodeCardControllerClient {
    public ImageView ContentImage;

    public Text ContentName;

    public Text Year;

    DataObject content;

    public EpisodeCardControllerClient(DataObject content) {
        this.content = content;
    }

    public void initialize() {
        ContentImage.setImage(content.getImage());
        ContentName.setText(content.getName(2));
        Date date = new Date();
        int days = date.getDay()+date.getMonth()*30+date.getYear()*365-content.getDate().getDay()-content.getDate().getMonth()*30-content.getDate().getYear()*365;
        if(days<4){
            Year.setText("New");
        }else if(days<7){
            Year.setText(String.valueOf(days)+" days ago");
        }else if(days<30){
            Year.setText(String.valueOf(days/7)+" weeks ago");
        }else if(days<365){
            Year.setText(String.valueOf(days/30)+" months ago");
        }else{
            Year.setText(String.valueOf(days/365)+" years ago");
        }
    }

    public void OpenInnerPage(MouseEvent event){
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.InnerPage(content)));
    }

    public void setFav(MouseEvent mouseEvent) {
    }
}
