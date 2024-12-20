package src.DataBase;

import javafx.scene.Node;
import src.AccountControl.Account;
import src.AccountControl.User;
import src.ContentControl.Content;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Used to search on Data Object , Load ,Save,Add and Remove Data from it
 * @param <T> Data Object Type
 */
public class DataObjectController <T> {
    List<T>data;
    final String Code;
    final File file;
    public final char type;
    /**
     *  NoN-Parametrized Constructor that used to initialize object that Not saved in files
     */

    public DataObjectController(char type) {
        data = new ArrayList<>();
        this.type=type;
        Code = null;
        file = null;
    }
    public DataObjectController(char type , List<T> data) {
        this.data = data;
        this.type=type;
        Code = null;
        file = null;
    }

    /**
     * Parametrized Constructor that used to initialize objects which is saved in files
     * @param filePath path of file that contains data and in end of program will be saved there
     * @param Code Slang code to Load Data
     * @param type of T entered which is refers to Instance Creator Class
     */
    public DataObjectController(String filePath,String Code,char type){
        data = new ArrayList<>();
        file= new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException e){
                System.out.println("Error while creating "+file.getName()+" file");
            }
        }
        this.Code=Code;
        this.type=type;
        Read();
    }

    /**
     * used to Run Slang code then make Instance by data and push it in data List
     */
    private void Read(){
        Slang sLangCode = new Slang(file,Code);
        while(sLangCode.notNull()){
            InstanceCreator<T>creator = new InstanceCreator<T>(sLangCode.Run(sLangCode.Code),data,type);
        }
    }

    /**
     * Saving data at the end of Program
     */
    public void Write(){
        try {
            FileOutputStream fos = new FileOutputStream(file);
            for (T item : data) {
                fos.write(item.toString().getBytes());
            }
        }catch (FileNotFoundException e){
            System.out.println("File not found "+file.getName());
        }catch (IOException e){
            System.out.println("Error while saving "+file.getName());
        }
    }

    /**
     * @param name the input you search
     * @param op check the function getId in the module (watchRecordData -> WatchRecord)
     * @return List of module Type
     */
    public List<T> getDataByString(String name,int op){
        List<T>SearchedData = new ArrayList<>();
        data.stream().filter(item -> ((DataObject)item).getName(op).equalsIgnoreCase(name)).forEach(SearchedData::add);
        return SearchedData;
    }

    /**
     * @param nameList list of names the input you search
     * @param op check the function getId in the module (watchRecordData -> WatchRecord)
     * @return List of module Type
     */
    public List<T> getDataByString(List<String> nameList,int op){
        List<T>SearchedData = new ArrayList<>();
        for(var name : nameList)
            data.stream().filter(item -> ((DataObject)item).getName(op).equalsIgnoreCase(name)).forEach(SearchedData::add);
        return SearchedData;
    }

    /**
     * Data By full obj
     * @param obj the obj you search
     * @return that Obj
     */
    public T getDataByObject(T obj){
        return data.stream().filter(item -> (item).equals(obj)).findFirst().orElse(null);
    }

    /**
     * Search Specific Element by its ID
     * @param Id of element
     * @return the element its found otherwise return null
     */
    public T getDataById(Long Id){
        int l = 0,r = data.size()-1;
        while (l<=r){
            int m = (l+r)/2;
            if(((DataObject)(data.get(m))).getId(0)>Id){
                r = m-1;
            }else if(((DataObject)(data.get(m))).getId(0)<Id){
                l = m+1;
            }else
                return data.get(m);
        }
        return null;
    }

    /**
     * @param Num the input you search
     * @param op check the function getId in the module (watchRecordData -> WatchRecord)
     * @return List of module Type
     */
    public List<T> getDateByNum(Long Num,int op){
        List<T>SearchedData = new ArrayList<>();
        data.stream().filter(item -> ((DataObject)item).getId(op).equals(Num)).forEach(SearchedData::add);
        return SearchedData;
    }

    /**
     * function that used to get the users that contains searchText
     * @param searchText is the substring of user you search
     * @return User[]
     */
    public List<T> getDataThatContains(String searchText,int op){
        List<T>ret=new ArrayList<>();
        data.stream().filter(item -> ((DataObject)item).getName(op).toLowerCase().contains(searchText.toLowerCase())).forEach(ret::add);
        return ret;
    }

    /**
     * function that return all users in application
     * @return List
     */
    public List<T> getData() {
        return data;
    }

    /**
     * function that add new user to application
     * @param item the user that will be added
     */
    public void addData(T item){
        data.add(item);
        if(type=='U'||type=='A'){
            DataBase.accountsData.addData((Account)item);
        }
        else if(type=='M'||type=='S'){
            DataBase.contentsData.addData((Content) item);
        }
    }

    /**
     * remove all data that equal to Name
     * @param name the name you search
     * @param op go to module to understand
     * @return List of Module Type
     */
    public List<T> removeData(String name,int op){
        List<T> item = getDataByString(name,op);
        for (T item1 : item) {
            data.remove(item1);
            if(type=='U'||type=='A')
                DataBase.accountsData.removeData(((DataObject)item1).getId(0),0);
            else if(type=='S'||type=='M')
                DataBase.contentsData.removeData(((DataObject)item1).getId(0),0);
        }

        return item;
    }

    /**
     * remove all data that equal to Name
     * @param Num the name you search
     * @param op  -> -1 if your search specific element (ID)
     *            -> otherwise check function getId in module
     */
    public List<T> removeData(long Num,int op){
        if(op == -1) {
            List<T> items = new ArrayList<>();
            items.add(getDataById(Num));
            data.remove(getDataById(Num));
            if (type == 'U' || type == 'A')
                DataBase.accountsData.removeData(Num,0);
            else if (type == 'S' || type == 'M')
                DataBase.contentsData.removeData(Num,0);
            return items;
        }else{
            List<T> items = getDateByNum(Num,op);
            for (T item1 : items) {
                data.remove(item1);
                if (type == 'U' || type == 'A')
                    DataBase.accountsData.removeData((Account) item1);
                else if (type == 'S' || type == 'M')
                    DataBase.contentsData.removeData((Content) item1);
            }
            return items;
        }
    }

    /**
     * remove full obj
     * @param obj object you want to remove
     */
    public void removeData(T obj){
        data.remove(obj);
        if (type == 'U' || type == 'A') {
            if(type == 'U'){
                DataBase.getInstance().Favorites.removeData(((User)obj).getId(0),0);
            }
            DataBase.accountsData.removeData((Account) obj);
        }
        else if (type == 'S' || type == 'M') {
            Content o = (Content)obj;
            for(var cast : o.cast){
                DataBase.getInstance().castMemberData.getDataByString(cast,2).getFirst().Contents.remove(o.contentTitle);
            }
            DataBase.contentsData.removeData((Content) obj);
        }
    }

    /**
     * remove all Expired Credit Card / Subscriptions
     */
    public void removeAllExpired(){
        Date newDate = new Date();
        int i = 0;
        while(i<data.size()){
            if(((DataObject)(data.get(i))).getDate().compareTo(newDate)<0)
                data.remove(i);
            else
                i++;
        }
    }

    public List<DataObject> ConvertListDataObject(){
        List<DataObject>list=new ArrayList<>();
        for(T item : data){
            DataObject DO = (DataObject)item;
            list.add(DO);
        }
        return list;
    }

    public DataObjectController<DataObject> ConvertToDataObject(){
        return new DataObjectController<>('\0',ConvertListDataObject());
    }

    public static List<Node> MakeNodeList(List<? extends DataObject>list){
        List<Node>nodeList=new ArrayList<>();
        for(DataObject dobj : list){
            nodeList.add(dobj.getNode());
        }
        return nodeList;
    }
}
