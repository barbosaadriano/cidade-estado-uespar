package br.com.ab.factories;

import br.com.ab.contracts.CrudConfigurerInterface;
import br.com.ab.contracts.CrudInterface;
import br.com.ab.controller.TelaDePesquisaController;
import br.com.ab.dao.DaoInterface;
import br.com.ab.dao.EstadoDao;
import br.com.ab.dao.TableModelInterface;
import br.com.ab.services.Conexao;
import java.io.IOException;

/**
 *
 * @author drink
 */
public class EstadoCrudBuilder implements CrudConfigurerInterface {

    public static EstadoCrudBuilder getEstadoConfigurer(){
        return new EstadoCrudBuilder();
    }
    
    @Override
    public void configurar(CrudInterface crud) {        
        TableModelInterface tm = new EstadoDao(Conexao.getInstance().getConn()); 
        try {
            TelaDePesquisaController tpc = ViewFactory.getInstance().loadController("TelaDePesquisa");
            tpc.configure(tm);
            crud.configurar(
                (DaoInterface) tm, 
                tpc,
                ViewFactory.getInstance().loadController("FormEstado"), 
                "Estados"
            );
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
