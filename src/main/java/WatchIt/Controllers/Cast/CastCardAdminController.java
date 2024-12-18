package WatchIt.Controllers.Cast;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.AccountControl.User;
import src.Cast.CastMember;
import src.ContentControl.Content;
import src.ContentControl.Movie;
import src.ContentControl.Series;
import src.DataBase.DataBase;
import src.DataBase.DataObjectController;

public class CastCardAdminController {
    public ImageView CastImage;
    public Text CastName,WorkType;
    CastMember castMember;
    public CastCardAdminController(CastMember castMember) {
        this.castMember = castMember;
    }
    public void initialize() {
        CastImage.setImage(castMember.getImage());
        CastName.setText(castMember.getName(2));
        WorkType.setText(castMember.WorkType);
    }
    public void setFavorite(MouseEvent mouseEvent) {

    }
    public void DeleteContent(MouseEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Delete Content");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to delete this Cast Member?");
        alert.showAndWait();
        String ans = alert.getResult().getText();
        if(ans.equals("OK")) {
            DataBase.getInstance().castMemberData.removeData(castMember);
        }
    }

}
