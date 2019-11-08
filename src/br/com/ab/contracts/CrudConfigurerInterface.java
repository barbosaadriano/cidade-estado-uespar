package br.com.ab.contracts;

/**
 *
 * @author drink interface para definir o contrato entre a fabrica do crud _
 *                  e algum objeto configurador que será acionado quando necessário _
 *                  para configurar o crud em questão
 */
public interface CrudConfigurerInterface {
    
    public void configurar(CrudInterface crud);
}
