package WatchIt.Controllers.Account.Admin.Helps;

import WatchIt.Models.Model;
import WatchIt.Views.AdminView;
import javafx.scene.input.MouseEvent;

public class EmptyCardController {
    int type;
    public void Add(MouseEvent e){
        this.type = Model.getInstance().getViewFactory().currentPage;
        if(type == 0){
            Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.AddMovie()));
        }
        else if(type == 1){
            Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.AddCast()));
        } else if (type==3) {
            Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.AddEpisode()));
        }
        else if(type == 4){
            Model.getInstance().getViewFactory().Show(AdminView.AdminScene(AdminView.AddSeries()));
        }
    }
}
