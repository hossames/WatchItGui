package WatchIt.Controllers.Utils;

import WatchIt.Models.Model;
import WatchIt.Views.ClientView;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import src.DataBase.DataObject;

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
        Model.getInstance().getViewFactory().Show(ClientView.setNode(ClientView.InnerPage(object)));
    }
}
