package br.com.ab.dao;

import br.com.ab.contracts.ICriteria;
import br.com.ab.contracts.IFilter;
import br.com.ab.contracts.ISqlInsert;
import br.com.ab.contracts.ISqlInstruction;
import br.com.ab.contracts.ISqlUpdate;
import br.com.ab.model.Estado;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author drink
 */
public class EstadoDao extends AbstractDao {

    public EstadoDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    protected String getTableName() {
        return "estado";
    }

    @Override
    public ArrayList<Object> getByCriterios(ICriteria c) {
        // Cria a instrução sql
        ISqlInstruction sql = this.newInstruction(ISqlInstruction.QueryType.SELECT);
        // Parametriza a instrução SQL
        sql.setCriterio(c);
        try {
            // Executa a sql
            ArrayList<HashMap<String, Object>> dados = this.executeSql(sql);
            if (!dados.isEmpty()) {
                ArrayList<Estado> ests = new ArrayList<>();
                for (HashMap<String, Object> row : dados) {
                    // Cria um estado para cada linha que retornou do banco
                    Estado est = new Estado();
                    est.setId((long) row.get("id"));
                    est.setNome((String) row.get("nome"));
                    est.setUf((String) row.get("sigla"));
                    ests.add(est);
                }
                return (ArrayList) ests;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void salvar(Object o) {
        Estado estado = (Estado) o;
        ISqlInstruction sql = this.newInstruction(ISqlInstruction.QueryType.INSERT);
        if (estado.getId() > 0) {
            sql = this.newInstruction(ISqlInstruction.QueryType.UPDATE);
        }
        ((ISqlUpdate) sql).addRowData("nome", estado.getNome());
        ((ISqlUpdate) sql).addRowData("sigla", estado.getUf());
        if (sql instanceof ISqlUpdate) {
            //update
            ICriteria criterio = new ICriteria();
            criterio.addExpressions(
                    new IFilter(
                            "id",
                            "=",
                            Long.toString(estado.getId()) //String.valueOf()
                    )
            );
            sql.setCriterio(criterio);
        } else if (sql instanceof ISqlInsert) {
            //insert
            ((ISqlInsert) sql).getRowData().put("id", null);
        }
        try {
            Object ret = this.executeSql(sql);
            if ( sql instanceof ISqlInsert && ret instanceof Long) {
                estado.setId((Long)ret);
            }
                
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void remover(Object o) {
        Estado estado = (Estado) o;
        if (estado.getId()>0) {
            ISqlInstruction del = this.newInstruction(ISqlInstruction.QueryType.DELETE);
            ICriteria criterio = new ICriteria();
            String vlo = String.valueOf(estado.getId());
            IFilter filtro = new IFilter("id", "=", vlo);            
            criterio.addExpressions(filtro);
           ///continuação
            del.setCriterio(criterio);
            try {
                executeSql(del);
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public Object getById(long id) {
        ICriteria criterio = new ICriteria();
        criterio.addExpressions(new IFilter("id", "=", String.valueOf(id)));
        return this.getByCriterios(criterio);
    }

}
