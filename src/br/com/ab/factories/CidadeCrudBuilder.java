/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ab.factories;

import br.com.ab.contracts.CrudConfigurerInterface;
import br.com.ab.contracts.CrudInterface;
import br.com.ab.contracts.FormControllerInterface;
import br.com.ab.contracts.LookUpControllerInterface;
import br.com.ab.controller.LookUpController;
import br.com.ab.controller.TelaDePesquisaController;
import br.com.ab.dao.CidadeDao;
import br.com.ab.dao.DaoInterface;
import br.com.ab.dao.EstadoDao;
import br.com.ab.dao.TableModelInterface;
import br.com.ab.services.Conexao;
import java.io.IOException;

/**
 *
 * @author drink Classe configuradora do crud de cidade
 */
public class CidadeCrudBuilder implements CrudConfigurerInterface {

    // este é um factory method, semelhante ao singleton, porém aqui não é singleton,
    // ou seja, permitirá mais de uma instância da mesma classe.
    public static CidadeCrudBuilder getCidadeConfigurer() {
        return new CidadeCrudBuilder();
    }

    @Override
    public void configurar(CrudInterface crud) {
        FormControllerInterface cc;
        try {
            cc = ViewFactory.getInstance().loadController("FormCidade");
            FormControllerInterface cl = ViewFactory.getInstance().loadController("LookUp");
            ((LookUpController) cl).configurar(
                    new EstadoDao(Conexao.getInstance().getConn()),
                    "Pesquisar estado"
            );
            TelaDePesquisaController tpc = ViewFactory.getInstance().loadController("TelaDePesquisa");
            TableModelInterface tm = new CidadeDao(Conexao.getInstance().getConn());
            tpc.configure(tm);
            ((LookUpControllerInterface) cc).setLookUp((LookUpControllerInterface) cl);
            crud.configurar((DaoInterface) tm, tpc, cc, "Cidades");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
