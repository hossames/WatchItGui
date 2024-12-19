package WatchIt.Controllers.Cast;
import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.AccountControl.User;
import src.Cast.CastMember;
import src.ContentControl.Content;
import src.DataBase.DataBase;
import src.DataBase.DataObjectController;

import java.util.List;

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
    public void OpenInnerPage(MouseEvent mouseEvent) {
        List<Node> nodeList = DataObjectController.MakeNodeList(DataBase.contentsData.getDataByString(castMember.Contents, 2));
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.CastPage(castMember, nodeList)));
    }

}
