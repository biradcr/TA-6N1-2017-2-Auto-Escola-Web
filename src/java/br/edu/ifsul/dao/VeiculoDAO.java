package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Veiculo;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author ubiratan
 */
@Stateful //EJB que não guarda estados
public class VeiculoDAO<TIPO> extends DAOGenerico<Veiculo> implements Serializable {
    
    public VeiculoDAO(){
        super();
        classePersistente = Veiculo.class;
//        ordem = "nome";// ordem padrão do DAO
    }
    
    @Override
    public Veiculo getObjectById(Object id) throws Exception{
        Veiculo obj = (Veiculo) em.find(classePersistente, id);
        /*
        A linha abaixo realiza a inicialização da coleção para que 
        quando ela for editada na interface já esteja carregada, evitando assim 
        um erro de LazyInitializationException
        */
        obj.getInstrutores().size();
        return obj;
    }            
}
