package br.com.ab.factories;

import java.io.IOException;
import java.util.HashMap;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author drink
 */
public class ViewFactory {
    
    private static ViewFactory instance = null;
    private HashMap<String,FXMLLoader> loaders;
    
    private ViewFactory(){
        loaders = new HashMap<>();
    }
    
    public static ViewFactory getInstance(){
        if (instance==null) {
            instance = new ViewFactory();
        }
        return instance;
    }
    public FXMLLoader getLoader(String viewName){
        return this.getLoader("/br/com/ab/view/", viewName);
    }
    public FXMLLoader getLoader(String pathToView,String view){
        if (!loaders.containsKey(pathToView+view+".fxml")){
            loaders.put(pathToView+view+".fxml", makeLoader(pathToView+view+".fxml"));
        }
        return this.loaders.get(pathToView+view+".fxml");
    }
    private FXMLLoader makeLoader(String path) {
         return new FXMLLoader(getClass().getResource(path));
    }
    public <T> T loadView(FXMLLoader loader) throws IOException{
        return (T) loader.load();
    }
    public <T> T loadView(String viewName) throws IOException{
        return loadView(getLoader(viewName));
    }
    public <T> T loadController(FXMLLoader loader) throws IOException{
        if (loader.getController()==null) {
            loadView(loader);
        }
        return (T)loader.getController();
    }
    public <T> T loadController(String viewName) throws IOException {
        return (T) loadController(getLoader(viewName));
    }
}
