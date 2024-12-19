package WatchIt.Controllers.Content;
import WatchIt.Models.Model;
import WatchIt.Views.AdminView;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import src.ContentControl.*;
import src.DataBase.DataBase;
import src.DataBase.DataObject;

public class EpisodeCardControllerAdmin {
    public ImageView ContentImage;

    public Text ContentName;

    public Text Year;

    DataObject content;

    public EpisodeCardControllerAdmin(DataObject content) {
        this.content = content;
    }

    public void initialize() {
        ContentImage.setImage(content.getImage());
        ContentName.setText(content.getName(2));
        Year.setText(String.valueOf(content.getDate().getYear() + 1900));
    }

    public void OpenInnerPage(){
    }

    public void DeleteContent() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Content");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this content?");
        alert.showAndWait();
        String ans = alert.getResult().getText();
        if(ans.equals("OK")) {
           DataBase.getInstance().episodesData.removeData((Episode)content);
           Model.getInstance().getViewFactory().Show(AdminView.EpisodePage());
        }
    }
}
