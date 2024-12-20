package src.AccountControl;
import WatchIt.Views.AdminView;
import javafx.scene.Node;
import src.DataBase.DataObject;
public class Account extends DataObject {
    protected String userName,firstName,lastName,email,FavoriteName;
    protected String password;
    protected Long ID;
    public static long cnt= 1;
    public Account(){
        userName = null;
        firstName = null;
        lastName = null;
        email = null;
        password = null;
        FavoriteName = null;
    }
    public Account(Account account){
        ID = account.ID;
        userName = account.userName;
        firstName = account.firstName;
        lastName = account.lastName;
        email = account.email;
        password = account.password;
        FavoriteName = account.FavoriteName;
    }
    public Account(String userName,String firstName,String lastName,String email,String password,String FavoriteName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.FavoriteName = FavoriteName;
        this.ID = cnt;
        cnt++;
    }
    public Account(String userName,String firstName,String lastName,String email,String password,String FavoriteName,Long id) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.FavoriteName = FavoriteName;
        ID=id;
        cnt = Math.max(cnt,id+1);
    }
    public String getFavoriteName(){ return this.FavoriteName;}
    public String getUserName() {
        return userName;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setFirstName(String firstName) {
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public String getEmail(){
        return this.email;
    }
    //--------------------------------------DataBase Methods-----------------------------------------//

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Account a) {
            return a.ID.equals(this.ID);
        }
        return false;
    }

    @Override
    public String getName(int op){
        if(op==0)
            return this.userName;
        if(op==1)
            return this.firstName;
        if(op==2)
            return this.lastName;
        if(op==3)
            return firstName + " " + lastName;
        return email;
    }

    @Override
    public Long getId(int op){
        return this.ID;
    }

    @Override
    public String toString(){
        return  ID.toString() + "," + userName + "," + firstName + "," + lastName + "," + email
                + "," + password + "," + FavoriteName + System.lineSeparator();
    }

    @Override
    public Node getNode(){
        try{
            return AdminView.UserCard(this).load();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
