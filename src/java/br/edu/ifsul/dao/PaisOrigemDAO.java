/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.modelo.Pais_Origem;
import java.io.Serializable;
import javax.ejb.Stateful;

/**
 *
 * @author ubiratan
 */
@Stateful //EJB que não guarda estados
public class PaisOrigemDAO<TIPO> extends DAOGenerico<Pais_Origem> implements Serializable {
    public PaisOrigemDAO(){
        super();
        classePersistente = Pais_Origem.class;
    }
}
