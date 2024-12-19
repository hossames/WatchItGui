package WatchIt.Models;

public class FavoritesModel {
    public final String Name;
    public final Integer UserId;
    public final Integer Type;
    public FavoritesModel(String Name, Integer UserId,int Type) {
        this.Name = Name;
        this.UserId = UserId;
        this.Type = Type;
    }
    //---------------------------------DataBase Method---------------------------------------//
    @Override
    public String toString() {
        return Name+","+UserId.toString()+","+Type.toString()+System.lineSeparator();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof FavoritesModel fav) {
            return fav.Name.equals(Name) && fav.UserId.equals(UserId)&&fav.Type.equals(Type);
        }
        return false;
    }

}
