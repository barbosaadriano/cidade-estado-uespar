/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cidadeestado;

import br.com.ab.controller.TelaDePesquisaController;
import br.com.ab.dao.TableModelInterface;
import br.com.ab.model.Cidade;
import br.com.ab.model.Estado;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author drink
 */
public class CidadeEstado extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        FXMLLoader loaderPesquisa = 
                new FXMLLoader(
                 getClass().getResource(
                         "/br/com/ab/view/TelaDePesquisa.fxml"
                 )
                );
        TelaDePesquisaController tpc =
                (TelaDePesquisaController)
                loaderPesquisa.getController();
        
        Parent root = loaderPesquisa.load();
        TableModelInterface tm = new FalseDaoCidade();
       tpc.configure(tm);
        
        Scene scene = new Scene(root);        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       launch(args);
       /**
        Estado e = new Estado();
        e.setId(1);
        e.setNome("PARANÁ");
        e.setUf("PR");
        
        Cidade c = new Cidade();
        c.setId(1);
        c.setNome("Palotina");
        c.setEstado(e);
        
        System.out.println(c.getEstado().getNome());
        System.out.println(c.getEstado().getNome().replace("A", "I"));
        */
    }
    
}
