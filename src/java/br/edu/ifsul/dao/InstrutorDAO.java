/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Instrutor;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author ubiratan
 */
@Stateful //EJB que não guarda estados
public class InstrutorDAO<TIPO> extends DAOGenerico<Instrutor> implements Serializable {
    public InstrutorDAO(){
        super();
        classePersistente = Instrutor.class;
    }
}
