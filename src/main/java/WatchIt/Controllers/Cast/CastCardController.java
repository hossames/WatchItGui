package WatchIt.Controllers.Cast;
import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.AccountControl.User;
import src.Cast.CastMember;
import src.ContentControl.Content;
import src.DataBase.DataBase;
import src.DataBase.DataObjectController;

public class CastCardController {
    public ImageView CastImage;
    public Text CastName,Type;
    CastMember castMember;
    public CastCardController(CastMember castMember) {
        this.castMember = castMember;
    }
    public void initialize() {
        CastImage.setImage(castMember.getImage());
        CastName.setText(castMember.getName(2));
        Type.setText(castMember.WorkType);
    }
    public void Click(MouseEvent mouseEvent){
        if(DataBase.getInstance().CurrentUser instanceof User) {
            DataObjectController<Content> db = new DataObjectController<>('\0', DataBase.contentsData.getDataByString(castMember.Contents, 1));
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
