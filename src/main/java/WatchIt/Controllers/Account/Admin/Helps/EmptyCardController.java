package WatchIt.Controllers.Account.Admin.Helps;

import WatchIt.Models.Model;
import javafx.scene.input.MouseEvent;

public class EmptyCardController {
    int type;
    public void Add(MouseEvent e){
        this.type = Model.getInstance().getViewFactory().currentPage;
    }
}
