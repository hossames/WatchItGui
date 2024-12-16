package WatchIt.Models;

import WatchIt.Views.ViewFactory;
import src.DataBase.DataBase;

public class Model {
    private static Model model;
    private static ViewFactory view;
    public static DataBase db;
    private Model() {
        db = DataBase.getInstance();
        view = new ViewFactory();
    }
    public static Model getInstance() {
        if (model == null) {
            model = new Model();
        }
        return model;
    }
    public ViewFactory getViewFactory() {
        return view;
    }

    public static void reset(){
        model = new Model();
    }
}
