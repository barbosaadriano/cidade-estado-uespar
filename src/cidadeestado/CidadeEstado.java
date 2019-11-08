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
import br.com.ab.factories.CidadeCrudBuilder;
import br.com.ab.factories.CrudControllerFactory;
import br.com.ab.factories.EstadoCrudBuilder;
import br.com.ab.factories.ViewFactory;
import br.com.ab.services.Conexao;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        //Configura o loader
        FXMLLoader ld = ViewFactory.getInstance().getLoader("/cidadeestado/", "FXMLDocument");
        // Carrega o formulário principal
        Parent root = ld.load();
        //Carrega o controller do formulário principal
        FXMLDocumentController pc = ld.getController();
        
        // Obtém a instância do CrudControllerFactory
        CrudControllerFactory ccf = CrudControllerFactory.getInstance();
        //Fabrica o controller
        ccf.makeController();
        //Adiciona um configurador para o nome cidade
        ccf.getCrudConfigurer().put("cidade", CidadeCrudBuilder.getCidadeConfigurer());
        //Adiciona um configurador para o nome estado
        ccf.getCrudConfigurer().put("estado", EstadoCrudBuilder.getEstadoConfigurer());
        
        // limpa o conatainer do form principal
        pc.getContainer().getChildren().clear();
        // adiciona no container o form padrão de crud
        pc.getContainer().getChildren().add(ccf.getCrudController().getLayout());

        //adiciona listener no botão cidade da tela principal
        pc.getBtnCidade().addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                //quando clicar no botão cidade manda o crud controller se reconfigurar para cidade
                ccf.configurar("cidade");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            // atualiza o título da tela principal
            stage.setTitle("Pesquisa de Cidade!");
        });
        // adiciona listener no botão estado da tela principal
        pc.getBtnEstado().addEventHandler(ActionEvent.ACTION, (event) -> {
            try {
                // quando ouvir o clique do botão estado, manda o crud controller se reconfigurar para estado
                ccf.configurar("estado");
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
            // atualiza o título da tela principal
            stage.setTitle("Pesquisa de estado!");
        });
        //cria uma cena para o form principal
        Scene scene = new Scene(root);
        //seta a cena no stage
        stage.setScene(scene);
        //abre o stage
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
