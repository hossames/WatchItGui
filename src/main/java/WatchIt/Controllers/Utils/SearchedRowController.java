package WatchIt.Controllers.Utils;

import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.AccountControl.User;
import src.Cast.CastMember;
import src.ContentControl.Content;
import src.ContentControl.Rateable;
import src.DataBase.DataBase;
import src.DataBase.DataObject;
import src.DataBase.DataObjectController;

public class SearchedRowController {
    public ImageView SearchImage;
    public Text SearchName;

    DataObject object;
    public SearchedRowController(DataObject object) {
        this.object = object;
    }
    @FXML
    public void initialize(){
        SearchImage.setImage(object.getImage());
        SearchName.setText(object.getName(2));
    }
    @FXML
    void click(MouseEvent event) {
        if(DataBase.getInstance().CurrentUser instanceof User) {
            if(object instanceof Rateable) {
                Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.InnerPage(object)));
            }else if(object instanceof CastMember castMember) {
                Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.CastPage(castMember,
                        DataObjectController.MakeNodeList(DataBase.contentsData.getDataByString(castMember.Contents,2)))));
            }
        }
    }
}
