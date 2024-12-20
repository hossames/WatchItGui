package src.DataBase;
import WatchIt.Models.FavoritesModel;
import WatchIt.Models.Model;
import src.ContentControl.*;
import src.AccountControl.*;
import src.Cast.*;
import src.Subscription.*;

/**
 * DataBase Administration Class that is built Using SingleTone Design Pattern
 */
public class DataBase {
    // SingleTone Instance
    private static DataBase dataBase;

    // Regex Strings
    public final String EmailRegex = "[\\w-]+@(gmail|yahoo|outlook)\\.(com|org|io|net)",
            PasswordRegex="^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*\\W)(?=.\\S+$).{8,}$";

    // Genre of Movies and Series
    public String[] genres = {
            "Action", "Adventure", "Comedy", "Drama", "Horror",
            "Romance", "Science Fiction", "Fantasy", "Mystery",
            "Thriller", "Documentary", "Animation", "Family",
            "Musical", "Crime", "Historical", "War", "Western"
    };

    //Accounts Data
    public DataObjectController<Admin> adminsData;
    public DataObjectController<User>usersData;
    public static DataObjectController<Account>accountsData =new DataObjectController<>('a');
    public Account CurrentUser = null;

    //Content Data
    public DataObjectController<Movie> moviesData;
    public DataObjectController<Series> seriesData;
    public DataObjectController<Episode> episodesData;
    public static DataObjectController<Content> contentsData = new DataObjectController<>('c');

    //Cast Data
    public DataObjectController<CastMember>castMemberData;

    //Watch Data
    public static DataObjectController<WatchRecord>watchRecordData;

    //Credit Card Data
    public DataObjectController<CreditCard> creditData;

    //Fav
    public DataObjectController<FavoritesModel>Favorites;

    /**
     * Non-Parameterized Constructor that init all Data Objects and Loading Data From Files
     */
    private DataBase() {
        // Loading Accounts
        usersData = new DataObjectController<>(Models.USER.getFilePath(),Models.USER.getCode(),Models.USER.getType().charAt(0));
        adminsData = new DataObjectController<>(Models.ADMIN.getFilePath(),Models.ADMIN.getCode(),Models.ADMIN.getType().charAt(0));
        // Loading Contents
        moviesData = new DataObjectController<>(Models.MOVIE.getFilePath(),Models.MOVIE.getCode(),Models.MOVIE.getType().charAt(0));
        seriesData = new DataObjectController<>(Models.SERIES.getFilePath(),Models.SERIES.getCode(),Models.SERIES.getType().charAt(0));
        episodesData = new DataObjectController<>(Models.EPISODE.getFilePath(),Models.EPISODE.getCode(),Models.EPISODE.getType().charAt(0));
        // Loading Cast
        castMemberData = new DataObjectController<>(Models.CAST.getFilePath(),Models.CAST.getCode(),Models.CAST.getType().charAt(0));
        // Loading Credit Cards
        creditData = new DataObjectController<>(Models.CREDIT_CARD.getFilePath(),Models.CREDIT_CARD.getCode(),Models.CREDIT_CARD.getType().charAt(0));
        //Fav
        Favorites = new DataObjectController<>(Models.FAVORITES.getFilePath(),Models.FAVORITES.getCode(),Models.FAVORITES.getType().charAt(0));
    }

    /**
     * getting Instance Of DataBase to reach all Data
     * check if dataBase instance is null then Create instance of DataBase otherwise return the same instance
     * @return DataBase
     */
    public static DataBase getInstance() {
        if(dataBase == null) {
            watchRecordData = new DataObjectController<>(Models.WATCH_RECORDS.getFilePath(),Models.WATCH_RECORDS.getCode(),Models.WATCH_RECORDS.getType().charAt(0));
            dataBase = new DataBase();
            return dataBase;
        }
        return dataBase;
    }

    /**
     * Saving All Data in Files
     */
    public void Save(){
        // save accounts data
        adminsData.Write();
        usersData.Write();
        // save content data
        moviesData.Write();
        seriesData.Write();
        episodesData.Write();
        // save cast data
        castMemberData.Write();
        // save watch records
        watchRecordData.Write();
        // save valid credit cards
        creditData.Write();
        Favorites.Write();
    }

    /**
     * Login Form
     * @return boolean that describe if Login is Successful or Failed (True -> Successful / False -> Failed)
     */
    public boolean Login(String Email,String Password){
        if(accountsData.getDataByString(Email,4).isEmpty()||
           !accountsData.getDataByString(Email,4).getFirst().getPassword().equals(Password)){
            return false;
        }
        CurrentUser = accountsData.getDataByString(Email,4).getFirst();
        Model.getInstance().getViewFactory().setType(CurrentUser instanceof Admin);
        return true;
    }
}