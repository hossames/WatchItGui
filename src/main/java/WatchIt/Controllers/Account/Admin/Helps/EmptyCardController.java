package WatchIt.Controllers.Account.Admin.Helps;

import WatchIt.Models.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;

public class EmptyCardController {
    FXMLLoader fxmlLoader;
    public EmptyCardController(FXMLLoader fxmlLoader){
        this.fxmlLoader = fxmlLoader;
    }
    public void Add(MouseEvent e){
        Model.getInstance().getViewFactory().Show(fxmlLoader);
    }
}
