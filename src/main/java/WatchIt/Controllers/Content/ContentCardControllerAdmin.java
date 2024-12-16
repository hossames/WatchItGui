package WatchIt.Controllers.Content;

import WatchIt.Models.Model;
import WatchIt.Views.AdminView;
import WatchIt.Views.ClientView;
import WatchIt.Views.MainView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.ContentControl.*;
import src.DataBase.DataBase;
import src.DataBase.DataObject;

public class ContentCardControllerAdmin {
    public ImageView ContentImage;

    public Text ContentName;
    DataObject content;
    public ContentCardControllerAdmin(DataObject content) {
        this.content = content;
    }
    public void initialize() {
        ContentImage.setImage(content.getImage());
        ContentName.setText(content.getName(2));
        long rate = ((Rateable)content).TotalRate();
    }
    public void DeleteContent(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Content");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this content?");
        alert.showAndWait();
        String ans = alert.getResult().getText();
        if(ans.equals("OK")) {
            if(content instanceof Movie) {

            }
            else if(content instanceof Series){

            }
            else{
            }
        }
    }
}
