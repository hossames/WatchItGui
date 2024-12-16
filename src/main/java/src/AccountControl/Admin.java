package src.AccountControl;

import src.DataBase.DataBase;
import src.DataBase.DataObjectController;
import src.Subscription.Subscription;
import src.ContentControl.Movie;
import src.ContentControl.Series;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Account {
    public static long accNum = 20,Profit=20000;
    public Admin(String userName,String firstName,String lastName,String email,String password,String FavoriteName) {
        super(userName,firstName,lastName,email,password,FavoriteName);
    }
    public Admin(Long Id,String userName,String firstName,String lastName,String email,String password,String FavoriteName) {
        super(userName,firstName,lastName,email,password,FavoriteName,Id);
    }
    public Admin(Account account) {
        super(account);
    }
    public void editUser(long userID , User user){
        DataObjectController<User> data= DataBase.getInstance().usersData;
        data.removeData(userID,0);
        data.addData(user);
    }
    public void deleteUser(long userID){
        DataObjectController<User>data=DataBase.getInstance().usersData;
        data.removeData(userID,0);
    }
    public void addMovie(Movie movie){
        DataObjectController<Movie> data = DataBase.getInstance().moviesData;
        data.addData(movie);
    }
    public void addSeries(Series series){
        DataObjectController<Series> data = DataBase.getInstance().seriesData;
        data.addData(series);
    }
    public void deleteMovie(String movie){
        DataObjectController<Movie> data = DataBase.getInstance().moviesData;
        data.removeData(movie,0);
    }
    public void deleteSeries(String series){
        DataObjectController<Series> data = DataBase.getInstance().seriesData;
        data.removeData(series,0);
    }
    // episode is not done yet
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
