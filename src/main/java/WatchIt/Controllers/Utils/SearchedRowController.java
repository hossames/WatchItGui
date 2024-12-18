package WatchIt.Controllers.Utils;

import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import src.DataBase.DataObject;

public class SearchedRowController {
    public ImageView SearchImage;
    public Text SearchName;

    DataObject object;
    public SearchedRowController(DataObject object) {
        this.object = object;
    }

    public void initialize(){
        SearchImage.setImage(object.getImage());
        SearchName.setText(object.getName(2));
    }
}
