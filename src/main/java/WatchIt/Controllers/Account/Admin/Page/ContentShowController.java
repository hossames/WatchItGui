package WatchIt.Controllers.Account.Admin.Page;

import WatchIt.Controllers.Account.Admin.Searchable;
import javafx.fxml.FXMLLoader;
import src.DataBase.DataObject;
import src.DataBase.DataObjectController;


public class ContentShowController extends Searchable {
    public ContentShowController(DataObjectController<? extends DataObject> controller, FXMLLoader fxmlLoader) {
        dataObjectController = controller;
        this.fxmlLoader =fxmlLoader;
    }
    public void initialize() {
        Container.widthProperty().addListener((Observable,oldValue,newValue) -> {
                SetToGrid();
        });
        initSearchEngine();
    }
}
