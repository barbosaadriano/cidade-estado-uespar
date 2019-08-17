
package br.com.ab.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author drink
 */
public class TelaDePesquisaController implements Initializable {

    @FXML
    private BorderPane pnPesquisa;
    @FXML
    private TextField txtPesquisa;
    @FXML
    private Button btnPesquisar;
    @FXML
    private TableView<?> tblDados;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
