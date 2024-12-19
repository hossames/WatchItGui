package WatchIt.Controllers.Account.Client.Pages;
import WatchIt.Controllers.Account.Client.Searchable;
import src.DataBase.DataObject;
import src.DataBase.DataObjectController;
public class OtherPageController extends Searchable {
    public OtherPageController(DataObjectController<? extends DataObject> controller) {
        dataObjectController = controller;
    }
    public void initialize() {
        Container.widthProperty().addListener((Observable,oldValue,newValue) -> {
            SetToGrid();
        });
        initSearchEngine();
    }
}
