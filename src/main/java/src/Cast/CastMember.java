package src.Cast;
import WatchIt.Application;
import WatchIt.Views.AdminView;
import WatchIt.Views.ClientView;
import WatchIt.Views.MainView;
import javafx.scene.Node;
import javafx.scene.image.Image;
import src.AccountControl.User;
import src.DataBase.*;

import javax.swing.plaf.IconUIResource;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
public class CastMember extends DataObject {
    public String firstName;
    public String lastName;
    public static long cnt = 1;
    public String WorkType;
    public Long CastMemberId;
    public Date dateOfBirth;  
    public String gender;
    public Image portrait;
    public List<String> Contents;
    public String nationality;
    public String socialMediaLink;
    public CastMember(String firstName, String lastName, String gender, String nationality, String socialMediaLink,String WorkType,List<String>Contents, Date dateOfBirth) {
        this.firstName = firstName;
        this.WorkType = WorkType;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.Contents = Contents;
        this.nationality = nationality;
        this.socialMediaLink = socialMediaLink;
        try {
            portrait = new Image(Application.class.getResourceAsStream("/images/"+getName(2)+".jpg"));
        }catch (Exception e){
            portrait = new Image(Application.class.getResourceAsStream("/images/portirait.jpg"));
        }
        CastMemberId = cnt++;
    }
    public CastMember(Long Id ,String firstName, String lastName, String gender, String nationality, String socialMediaLink,String WorkType,List<String>Contents, Date dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.WorkType = WorkType;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.Contents =Contents;
        this.nationality = nationality;
        this.socialMediaLink = socialMediaLink;
        CastMemberId = Id;
        try {
            portrait = new Image(Application.class.getResourceAsStream("/Images/"+getName(2)+".jpg"));
        }catch (Exception e) {
            portrait = new Image(Application.class.getResourceAsStream("/Images/portirait.jpg"));
        }
        cnt = Id+1;
    }
    public Image getImage(){
        return portrait;
    }
   public void joinContent(String Content){
        Contents.add(Content);
   }
   //--------------------------------------DataBase Methods-----------------------------------------//
    @Override
    public boolean equals(Object o) {
        if(o instanceof CastMember castMember){
            return castMember.firstName.equals(firstName)&&castMember.lastName.equals(lastName)&&castMember.dateOfBirth.equals(dateOfBirth)&&castMember.gender.equals(gender)&&castMember.nationality.equals(nationality);
        }
        return false;
    }
    @Override
    public Node getNode(){
        try {
            if(DataBase.getInstance().CurrentUser instanceof User)
                return ClientView.CastCard(this).load();
            else
                return AdminView.CastCard(this).load();
        }catch (Exception e){
            System.out.println("-----------------------");
            e.printStackTrace();
            System.out.println("Error while making scene of CastCard");
            return null;
        }
    }
   @Override
   public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(CastMemberId.toString()).append(",").append(firstName).append(",").append(lastName).append(",").append(gender).append(",").append(nationality).append(",").append(socialMediaLink).append(",");
        sb.append(WorkType).append(",");
        sb.append(Integer.valueOf(Contents.size()).toString()).append(",");
        for (var item : Contents) {
            sb.append(item);
            sb.append(",");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(System.lineSeparator());
        sb.append(DateFormat.getInstance().format(dateOfBirth));
        sb.append(System.lineSeparator());
        return sb.toString();
   }
    @Override
    public String getName(int op){
        if(op==0)
            return this.firstName;
        if(op==1)
            return this.lastName;
        return this.firstName+" "+this.lastName;
    }
    @Override
    public Long getId(int op){
        return this.CastMemberId;
    }
}
