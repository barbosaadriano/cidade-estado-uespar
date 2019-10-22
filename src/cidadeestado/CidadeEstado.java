/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cidadeestado;

import br.com.ab.controller.TelaDePesquisaController;
import br.com.ab.dao.CidadeDao;
import br.com.ab.dao.EstadoDao;
import br.com.ab.dao.TableModelInterface;
import br.com.ab.services.Conexao;
import javafx.application.Application;
import javafx.event.ActionEvent;
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
        // Carrega o leiaute e o controller Principal
        FXMLLoader loaderPrincipal =  new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        Parent root = loaderPrincipal.load();                   
        FXMLDocumentController pc = loaderPrincipal.getController();
        // Carrega o leiaute e o controller de pesquisa
        FXMLLoader loaderPesquisa =  new FXMLLoader(getClass().getResource("/br/com/ab/view/TelaDePesquisa.fxml"));
        Parent bp = loaderPesquisa.load();                   
        TelaDePesquisaController tpc = loaderPesquisa.getController();
        
        pc.getBtnCidade().addEventHandler(ActionEvent.ACTION, (event) -> {
            pc.getContainer().getChildren().clear();
            pc.getContainer().getChildren().add(bp);
            //TableModelInterface tm = new FalseDaoCidade();
            TableModelInterface tm = new CidadeDao(Conexao.getInstance().getConn());
            tpc.configure(tm);
            stage.setTitle("Pesquisa de Cidade!");
        });
        pc.getBtnEstado().addEventHandler(ActionEvent.ACTION, (event) -> {
             pc.getContainer().getChildren().clear();
            pc.getContainer().getChildren().add(bp);
            //TableModelInterface tm = new FalseDaoEstado();
            TableModelInterface tm = new EstadoDao(Conexao.getInstance().getConn());
            tpc.configure(tm);
            stage.setTitle("Pesquisa de estado!");
        });
        
        
        
        
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
