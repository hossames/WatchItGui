package WatchIt.Controllers.Content;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.ContentControl.*;
import src.DataBase.DataObject;

public class ContentCardControllerAdmin {
    public ImageView ContentImage;

    public Text ContentName;

    public Text Genre;

    public Text Year;

    DataObject content;

    public ContentCardControllerAdmin(DataObject content) {
        this.content = content;
    }

    public void initialize() {
        ContentImage.setImage(content.getImage());
        ContentName.setText(content.getName(2));
        Year.setText(String.valueOf(content.getDate().getYear()+1900));
        if(content instanceof Content content1){
            StringBuilder genres = new StringBuilder();
            for (var genre : content1.genres)
                genres.append(genre).append(" ");
            Genre.setText(genres.toString());
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
