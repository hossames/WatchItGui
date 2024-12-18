package WatchIt.Controllers.Cast;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.AccountControl.User;
import src.Cast.CastMember;
import src.ContentControl.Content;
import src.DataBase.DataBase;
import src.DataBase.DataObjectController;

public class CastCardClientController {
    public ImageView CastImage;
    public Text CastName,WorkType;
    CastMember castMember;
    public CastCardClientController(CastMember castMember) {
        this.castMember = castMember;
    }
    public void initialize() {
        CastImage.setImage(castMember.getImage());
        CastName.setText(castMember.getName(2));
        WorkType.setText(castMember.WorkType);
    }
    public void setFavorite(MouseEvent mouseEvent) {

    }
    public void OpenInnerPage(MouseEvent mouseEvent){
        if(DataBase.getInstance().CurrentUser instanceof User) {
            DataObjectController<Content> db = new DataObjectController<>('\0', DataBase.contentsData.getDataByString(castMember.Contents, 1));
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
