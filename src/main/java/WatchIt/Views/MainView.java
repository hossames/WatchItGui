package WatchIt.Views;

import WatchIt.Application;
import WatchIt.Controllers.Cast.CastCardAdminController;
import WatchIt.Controllers.Enter.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import src.Cast.CastMember;

import java.io.IOException;
import java.util.List;

public class MainView<T> {
    public static FXMLLoader LoginScene() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Enter/Login.fxml"));
        fxmlLoader.setController(new LoginController());
        return fxmlLoader;
    }
    public static FXMLLoader CastCard(CastMember castMember) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Cast/CastCard.fxml"));
        fxmlLoader.setController(new CastCardAdminController(castMember));
        return fxmlLoader;
    }
    public static Scene getScene(FXMLLoader fxmlLoader){
        try{
            Scene scene = new Scene(fxmlLoader.load());
            return scene;
        }catch (IOException e){
            System.out.println("Error: While Getting Scene");
            System.out.println(e);
        }
        return null;
    }
    public static FXMLLoader RegisterScene(){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Enter/Register.fxml"));
        return fxmlLoader;
    }
    public static FXMLLoader ForgetScene(String Email){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Enter/ForgetPassword.fxml"));
        fxmlLoader.setController(new ForgetPasswordController(Email));
        return fxmlLoader;
    }
    public static FXMLLoader FavoriteNameScene(String Email){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Enter/CheckFavoriteName.fxml"));;
        fxmlLoader.setController(new CheckFavoriteNameController(Email));
        return fxmlLoader;
    }
    public static FXMLLoader GenreChoose(String FirstName, String LastName,String UserName,String Email,String Password,String FavName){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Enter/GenreChoose.fxml"));;
        fxmlLoader.setController(new GenreController(FirstName,LastName,UserName,Email,Password,FavName));
        return fxmlLoader;
    }
    public static FXMLLoader SubscriptionPlan(String FirstName, String LastName, String UserName, String Email, String Password, String FavName, List<String> Genres){
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Enter/choosePlan.fxml"));;
        fxmlLoader.setController(new SubscriptionPlanController(FirstName,LastName,UserName,Email,Password,FavName,Genres));
        return fxmlLoader;
    }
    public static FXMLLoader TitleBar() {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(Application.class.getResource("/Fxml/Utils/titleBar.fxml"));
        return fxmlLoader;
    }
}
