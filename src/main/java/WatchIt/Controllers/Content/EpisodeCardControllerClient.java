package WatchIt.Controllers.Content;

import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.ContentControl.*;
import src.DataBase.DataObject;

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
        Year.setText(String.valueOf(content.getDate().getYear()+1900));
    }

    public void OpenInnerPage(MouseEvent event){
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.InnerPage(content)));
    }

    public void SetFavorite(MouseEvent mouseEvent) {
    }
}
