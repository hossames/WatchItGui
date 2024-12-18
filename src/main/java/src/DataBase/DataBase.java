package src.DataBase;
import WatchIt.Models.Model;
import src.ContentControl.*;
import src.AccountControl.*;
import src.Cast.*;
import src.Subscription.*;

import java.util.*;

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
    public static DataObjectController<Content> contentsData = new DataObjectController<Content>('c');

    //Cast Data
    public DataObjectController<CastMember>castMemberData;

    //Watch Data
    public static DataObjectController<WatchRecord>watchRecordData;

    //Credit Card Data
    public DataObjectController<CreditCard> creditData;

    /**
     * Non-Parameterized Constructor that init all Data Objects and Loading Data From Files
     */
    private DataBase() {
        // Loading Accounts
        usersData = new DataObjectController<User>("./users.txt","nslw5SWw3oSWSiw2ndW",'U');
        adminsData = new DataObjectController<Admin>("./admins.txt","nslw6SW",'A');
        // Loading Contents
        moviesData = new DataObjectController<Movie>("./movies.txt","nslw4SWiioSoSndni",'M');
        seriesData = new DataObjectController<Series>("./series.txt","nslw4SWiioSoSndnsiind",'S');
        episodesData = new DataObjectController<Episode>("./episodes.txt","nslSSiind",'E');
        // Loading Cast
        castMemberData = new DataObjectController<CastMember>("./CastMembers.txt","nslw6SWoSnd",'C');
        // Loading Credit Cards
        creditData = new DataObjectController<CreditCard>("./creditCard.txt","nsw4SWnd",'R');
    }

    /**
     * getting Instance Of DataBase to reach all Data
     * check if dataBase instance is null then Create instance of DataBase otherwise return the same instance
     * @return DataBase
     */
    public static DataBase getInstance() {
        if(dataBase == null) {
            watchRecordData = new DataObjectController<WatchRecord>("./watchRecord.txt","nslfSnd",'W');
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