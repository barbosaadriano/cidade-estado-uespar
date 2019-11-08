/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cidadeestado;

import br.com.ab.contracts.CrudInterface;
import br.com.ab.contracts.FormControllerInterface;
import br.com.ab.contracts.LookUpControllerInterface;
import br.com.ab.controller.LookUpController;
import br.com.ab.controller.TelaDePesquisaController;
import br.com.ab.dao.CidadeDao;
import br.com.ab.dao.DaoInterface;
import br.com.ab.dao.EstadoDao;
import br.com.ab.dao.TableModelInterface;
import br.com.ab.factories.ViewFactory;
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

  
        FormControllerInterface ce = ViewFactory.getInstance().loadController("FormEstado");
        FormControllerInterface cc = ViewFactory.getInstance().loadController("FormCidade");
        FormControllerInterface cl = ViewFactory.getInstance().loadController("LookUp");        
        Parent crudview = ViewFactory.getInstance().loadView("CrudView");
        CrudInterface crudController = ViewFactory.getInstance().loadController("CrudView");
        FXMLLoader ld = ViewFactory.getInstance().getLoader("/cidadeestado/","FXMLDocument");
        Parent root = ld.load();
        FXMLDocumentController pc = ld.getController();
        
        Parent bp = ViewFactory.getInstance().loadView("TelaDePesquisa");
        TelaDePesquisaController tpc = ViewFactory.getInstance().loadController("TelaDePesquisa");
        
        pc.getBtnCidade().addEventHandler(ActionEvent.ACTION, (event) -> {
            ((LookUpController)cl).configurar(
                    new EstadoDao(Conexao.getInstance().getConn()),
                    "Pesquisar estado"
            );
            TableModelInterface tm = new CidadeDao(Conexao.getInstance().getConn());
            tpc.configure(tm);
            ((LookUpControllerInterface)cc).setLookUp((LookUpControllerInterface)cl);
            crudController.configurar((DaoInterface)tm, tpc, cc, "Cidades");
            pc.getContainer().getChildren().clear();
            pc.getContainer().getChildren().add(crudview);            
            stage.setTitle("Pesquisa de Cidade!");
        });
        
        pc.getBtnEstado().addEventHandler(ActionEvent.ACTION, (event) -> {
            TableModelInterface tm = new EstadoDao(Conexao.getInstance().getConn()); 
            tpc.configure(tm);
            crudController.configurar((DaoInterface) tm, tpc, ce, "Estados");
            pc.getContainer().getChildren().clear();
            pc.getContainer().getChildren().add(crudview);
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
     
    }

}
