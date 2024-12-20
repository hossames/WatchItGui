package WatchIt.Controllers.Cast;

import WatchIt.Controllers.Account.Client.Helps.HandleGrid;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import src.Cast.CastMember;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;

public class CastPageController {
    CastMember castMember;
    List<Node> nodeList;
    Desktop desktop= Desktop.getDesktop();
    public CastPageController(CastMember castMember, List<Node> nodeList){
        this.castMember=castMember;
        this.nodeList= nodeList;
    }
    @FXML
    private Text Age;

    @FXML
    private Text Gender;

    @FXML
    private Text Name;

    @FXML
    private Text Nationality;

    @FXML
    private ImageView portrait;

    @FXML
    private Hyperlink socialMediaLink;

    @FXML
    private GridPane View;

    @FXML
    private ScrollPane Container;

    public void initialize(){
        int age = new Date().getYear()-castMember.dateOfBirth.getYear();
        Age.setText(String.valueOf(age));
        Gender.setText(castMember.gender);
        Name.setText(castMember.getName(2));
        Nationality.setText(castMember.nationality);
        socialMediaLink.setText(castMember.socialMediaLink);
        portrait.setImage(castMember.getImage());
        SetToGrid();
        Container.widthProperty().addListener((observable, oldValue, newValue) -> {
            SetToGrid();
        });
        socialMediaLink.addEventHandler(MouseEvent.MOUSE_CLICKED , (MouseEvent event)->{
            try {
                desktop.browse(new URI(socialMediaLink.getText()));
            } catch (IOException | URISyntaxException e) {
                System.out.println("link not available");
            }
        });
    }
    public void SetToGrid(){
        View.getChildren().clear();
        for(var node : nodeList)
            HandleGrid.setToGrid(View,(int)Container.getWidth(),node);
    }
}
