package src.AccountControl;

import src.Cast.CastMember;
import src.ContentControl.Episode;
import src.DataBase.DataBase;
import src.DataBase.DataObjectController;
import src.Subscription.Subscription;
import src.ContentControl.Movie;
import src.ContentControl.Series;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Account {
    public Admin(String userName,String firstName,String lastName,String email,String password,String FavoriteName) {
        super(userName,firstName,lastName,email,password,FavoriteName);
    }
    public Admin(Long Id,String userName,String firstName,String lastName,String email,String password,String FavoriteName) {
        super(userName,firstName,lastName,email,password,FavoriteName,Id);
    }
    public Admin(Account account) {
        super(account);
    }
    public static long calculateRevenue(){
        DataObjectController<User>data=DataBase.getInstance().usersData;
        long revenue = 0, numOfBasic = 0 , numOfStandard = 0, numOfPremium = 0;
        for (User user : data.getData()){
            String plan = user.getSubscriptionPlan().getPlan();
            if(plan.equals("Basic"))numOfBasic++;
            else if(plan.equals("Standard"))numOfStandard++;
            else numOfPremium++;
        }
        revenue+=(numOfBasic* Subscription.prices[0])+(numOfStandard*Subscription.prices[1])+(numOfPremium*Subscription.prices[2]);
        return revenue;
    }
    public static long CalculateRevenue(int year,int month){
        DataObjectController<User>data=DataBase.getInstance().usersData;
        long revenue = 0, numOfBasic = 0 , numOfStandard = 0, numOfPremium = 0;
        for (User user : data.getData()){
            //System.out.println(user.getSubscriptionPlan().getStartDate().getYear());
            if(user.getSubscriptionPlan().getStartDate().getYear()==year-1900
                    &&user.getSubscriptionPlan().getStartDate().getMonth()==month) {
                String plan = user.getSubscriptionPlan().getPlan();
                if (plan.equals("Basic")) numOfBasic++;
                else if (plan.equals("Standard")) numOfStandard++;
                else numOfPremium++;
            }
        }
        revenue+=(numOfBasic* Subscription.prices[0])+(numOfStandard*Subscription.prices[1])+(numOfPremium*Subscription.prices[2]);
        return revenue;
    }
    public static List<Long> plansAnalysis(){
        DataObjectController<User>data=DataBase.getInstance().usersData;
        long numOfBasic = 0 , numOfStandard = 0, numOfPremium = 0;
        for (User user : data.getData()){
            String plan = user.getSubscriptionPlan().getPlan();
            if(plan.equals("Basic"))numOfBasic++;
            else if(plan.equals("Standard"))numOfStandard++;
            else numOfPremium++;
        }
        List<Long>l = new ArrayList<>();
        l.add(numOfBasic);
        l.add(numOfStandard);
        l.add(numOfPremium);
        return l;
    }
    public void addMovie(Movie movie){
        DataBase.getInstance().moviesData.addData(movie);
    }
    public void addSeries(Series series){
        DataBase.getInstance().seriesData.addData(series);
    }
    public void addCast(CastMember castMember){
        DataBase.getInstance().castMemberData.addData(castMember);
    }
    public void addEpisode(Episode episode){
        DataBase.getInstance().episodesData.addData(episode);
    }
    public void removeMovie(Movie movie){
        DataBase.getInstance().moviesData.removeData(movie);
    }
    public void removeSeries(Series series){
        DataBase.getInstance().seriesData.removeData(series);
    }
    public void removeEpisode(Episode episode){
        DataBase.getInstance().episodesData.removeData(episode);
    }
    public void removeCast(CastMember cast){
        DataBase.getInstance().castMemberData.removeData(cast);
    }
    public void removeAccount(Account account){
        if(account instanceof Admin admin){
            DataBase.getInstance().adminsData.removeData(admin);
        }else if(account instanceof  User user) {
            DataBase.getInstance().usersData.removeData(user);
        }
    }
    //--------------------------------------DataBase Methods-----------------------------------------//
    @Override
    public String toString(){
        return super.toString();
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Admin other){
            return other.getId(0).equals(this.getId(0));
        }
        return false;
    }
}
