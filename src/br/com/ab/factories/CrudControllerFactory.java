package br.com.ab.factories;

import br.com.ab.contracts.CrudConfigurerInterface;
import br.com.ab.contracts.CrudInterface;
import java.io.IOException;
import java.util.HashMap;

/**
 *
 * @author drink
 */
public class CrudControllerFactory {
    //instância singleton desta própria classe
    private static CrudControllerFactory instance = null;
    // instância do viewFactory
    private ViewFactory viewFactory;
    // instância do crudcontroller
    private CrudInterface crudController;
    // espécie de cache para configuradores
    private HashMap<String,CrudConfigurerInterface> crudConfigurer;
    
    private CrudControllerFactory() {
        crudConfigurer = new HashMap<>();
    }
    
    public static CrudControllerFactory getInstance(){
        if (instance==null) {
            instance = new CrudControllerFactory();    
        }
        return instance;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public void setViewFactory(ViewFactory viewFactory) {
        this.viewFactory = viewFactory;
    }
    
    public void makeController() throws IOException, Exception{
        if (viewFactory==null){
            viewFactory = ViewFactory.getInstance();
        }
        if (viewFactory==null){
            throw new Exception("Não foi possível obter o view factory");
        }
        this.crudController = viewFactory.loadController("CrudView");
    }

    public void configurar(String name) throws Exception {
        if (crudConfigurer.isEmpty()) {
            throw new Exception("Não há configuradores disponível!");
        }
        crudConfigurer.get(name).configurar(crudController);
    }

    public HashMap<String, CrudConfigurerInterface> getCrudConfigurer() {
        return crudConfigurer;
    }

    public CrudInterface getCrudController() {
        return crudController;
    }
    
    
    
    
}
