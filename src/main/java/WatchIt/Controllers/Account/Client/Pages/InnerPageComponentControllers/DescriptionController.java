package WatchIt.Controllers.Account.Client.Pages.InnerPageComponentControllers;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class DescriptionController {
    String story;
    public DescriptionController(String story){
        this.story=story;
    }
    @FXML
    private Text Description;
    @FXML
    public void initialize(){
        Description.setText(story);
    }
}
