package src.DataBase;

public enum Models {
    USER(new String[]{"./users.txt","nslw5SWw3oSWSiw2ndW","U"}),
    ADMIN(new String[]{"./admins.txt","nslw6SW","A"}),
    MOVIE(new String[]{"./movies.txt","nslw4SWiioSoSndni","M"}),
    SERIES(new String[]{"./series.txt","nslw4SWiioSoSndnsiind","S"}),
    EPISODE(new String[]{"./episodes.txt","nslSSiind","E"}),
    CAST(new String[]{"./CastMembers.txt","nslw6SWoSnd","C"}),
    CREDIT_CARD(new String[]{"./creditCard.txt","nsw4SWnd","R"}),
    FAVORITES(new String[]{"./Favorites.txt","nsSii","F"}),
    WATCH_RECORDS(new String[]{"./watchRecord.txt","nslfSnd","W"});
    private final String Code,Type,FilePath;
    Models(String[] s){
        FilePath = s[0];
        Code = s[1];
        Type = s[2];
    }
    public String getCode(){
        return Code;
    }
    public String getType(){
        return Type;
    }
    public String getFilePath(){
        return FilePath;
    }
}
