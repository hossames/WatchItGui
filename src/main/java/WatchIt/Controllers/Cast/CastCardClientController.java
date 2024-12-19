package WatchIt.Controllers.Cast;
import WatchIt.Models.FavoritesModel;
import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.Cast.CastMember;
import src.ContentControl.Content;
import src.DataBase.DataBase;
import src.DataBase.DataObjectController;

import java.util.List;

public class CastCardClientController {
    public ImageView CastImage;
    public Text CastName,WorkType;
    public FontAwesomeIconView Heart;
    CastMember castMember;
    public CastCardClientController(CastMember castMember) {
        this.castMember = castMember;
    }
    public void initialize() {
        CastImage.setImage(castMember.getImage());
        CastName.setText(castMember.getName(2));
        WorkType.setText(castMember.WorkType);
        FavoritesModel favoritesModel = new FavoritesModel(castMember.getName(2), DataBase.getInstance().CurrentUser.getId(0).intValue(),1);
        if(DataBase.getInstance().Favorites.getDataByObject(favoritesModel)!=null){
            Heart.setIcon(FontAwesomeIcon.HEART);
        }
    }
    public void SetFavorite(MouseEvent mouseEvent) {
        FavoritesModel favoritesModel = new FavoritesModel(castMember.getName(2), DataBase.getInstance().CurrentUser.getId(0).intValue(),1);
        if(DataBase.getInstance().Favorites.getDataByObject(favoritesModel)==null){
            DataBase.getInstance().Favorites.addData(favoritesModel);
            Heart.setIcon(FontAwesomeIcon.HEART);
        }else{
            DataBase.getInstance().Favorites.removeData(favoritesModel);
            Heart.setIcon(FontAwesomeIcon.HEART_ALT);

        }
    }
    public void OpenInnerPage(MouseEvent mouseEvent) {
        List<Node> nodeList = DataObjectController.MakeNodeList(DataBase.contentsData.getDataByString(castMember.Contents, 2));
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.CastPage(castMember, nodeList)));
    }

}
