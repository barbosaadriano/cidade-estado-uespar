package br.com.ab.contracts;

import javafx.beans.property.BooleanProperty;

/**
 *
 * @author drink
 */
public interface LookUpControllerInterface extends FormControllerInterface {
    
    public void setLookUp(LookUpControllerInterface lkp);
    
    public BooleanProperty hasActiveLookup();
}
