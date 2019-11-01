package br.com.ab.controller;

import br.com.ab.contracts.LookUpControllerInterface;
import br.com.ab.model.Cidade;
import br.com.ab.model.Estado;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author drink
 */
public class FormCidadeController implements Initializable,
        LookUpControllerInterface  {

    @FXML
    private AnchorPane apCidade;
    @FXML
    private Font x1;
    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtEstado;
    @FXML
    private Button btnLookUp;
    //Atributo para guardar a cidade
    private Cidade cidade;
    // lookUpController
    private LookUpController lkp;
    private BooleanProperty hasActiveLookup;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.hasActiveLookup = new SimpleBooleanProperty(false);
    }    

    @FXML
    private void buscarEstado(ActionEvent event) {
        StackPane parent = (StackPane) apCidade.getParent();
        parent.getChildren().add(lkp.getLayout());
        lkp.inicializar();
        this.hasActiveLookup.setValue(Boolean.TRUE);
        
        Task<Object> lookup = new Task() {
            @Override
            protected Object call() throws Exception {
                while(!lkp.isCloseRequested()){
                    Thread.sleep(500);
                }
                return lkp.getModel();
            }
        };
        
        lookup.setOnSucceeded((evt) -> {
            if (lookup.getValue()!=null) {
                cidade.setEstado((Estado)lookup.getValue());
            }
            this.hasActiveLookup.setValue(Boolean.FALSE);
            parent.getChildren().remove(lkp.getLayout());
        });
        
        new Thread(lookup).start();
    }

    @Override
    public Parent getLayout() {
        return this.apCidade;
    }

    @Override
    public void setModel(Object model) {
        this.cidade = (Cidade)model;
        txtNome.setText(this.cidade.getNome());
        if (this.cidade.getEstado() != null) {
            txtEstado.setText(this.cidade.getEstado().getNome());
        } else {
            txtEstado.setText("");
        }
    }

    @Override
    public Object getModel() {
        this.cidade.setNome(txtNome.getText());
        return this.cidade;
    }

    @Override
    public void inicializar() {
        this.setModel(new Cidade());
    }

    @Override
    public void setLookUp(LookUpControllerInterface lkp) {
        this.lkp = (LookUpController) lkp;
    }

    @Override
    public BooleanProperty hasActiveLookup() {
        return this.hasActiveLookup;
    }
    
}
