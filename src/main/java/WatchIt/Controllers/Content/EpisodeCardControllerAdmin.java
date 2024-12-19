package WatchIt.Controllers.Content;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.ContentControl.*;
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
        if(true) {
            Year.setText(String.valueOf(content.getDate().getYear() + 1900));
        }
    }

    public void OpenInnerPage(){
        try {
        }catch (Exception e){
            e.printStackTrace();
        }
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
