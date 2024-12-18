package WatchIt.Views;

import WatchIt.Application;
import WatchIt.Controllers.Account.Admin.AdminController;
import WatchIt.Controllers.Account.Admin.Helps.EmptyCardController;
import WatchIt.Controllers.Account.Admin.Page.ContentShowController;
import WatchIt.Controllers.Cast.CastCardAdminController;
import WatchIt.Controllers.Content.ContentCardControllerAdmin;
import javafx.fxml.FXMLLoader;
import src.Cast.CastMember;
import src.DataBase.DataBase;
import src.DataBase.DataObject;

public class AdminView{
    public static FXMLLoader AdminScene(FXMLLoader fxmlLoader1) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Admin.fxml"));
        fxmlLoader.setController(new AdminController(fxmlLoader1));
        return fxmlLoader;
    }
    public static FXMLLoader DashBoard() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/DashBoard.fxml"));
        return fxmlLoader;
    }
    public static FXMLLoader MoviesPage(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Pages/ContentShow.fxml"));
        fxmlLoader.setController(new ContentShowController(DataBase.getInstance().moviesData));
        return fxmlLoader;
    }
    public static FXMLLoader CastCard(CastMember castMember){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Cast/CastCardAdmin.fxml"));
        fxmlLoader.setController(new CastCardAdminController(castMember));
        return fxmlLoader;
    }
    public static FXMLLoader EmptyCard(FXMLLoader fxmlLoader1) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Account/Admin/Helps/EmptyCard.fxml"));
        fxmlLoader.setController(new EmptyCardController(fxmlLoader1));
        return fxmlLoader;
    }
    public static FXMLLoader ContentCard(DataObject content){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Content/ContentCardAdmin.fxml"));
        fxmlLoader.setController(new ContentCardControllerAdmin(content));
        return fxmlLoader;
    }
}
