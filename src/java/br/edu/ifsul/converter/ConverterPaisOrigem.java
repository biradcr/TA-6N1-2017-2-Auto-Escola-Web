/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.converter;

import br.edu.ifsul.modelo.Pais_Origem;
import java.io.Serializable;
import java.util.jar.Attributes;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ubiratan
 */
@FacesConverter(value = "converterPaisOrigem")
public class ConverterPaisOrigem implements Converter, Serializable{
    @PersistenceContext(unitName = "TA-6N1-2017-2-Auto-Escola-WebPU")
    private EntityManager em;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if(string == null || string.equals("Selecione um registro")){
            return null;
        }
        return em.find(Pais_Origem.class, Integer.parseInt(string));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if(o == null){
            return null;
        }
        Pais_Origem obj = (Pais_Origem) o;
        return obj.getId().toString();
    }
    
}
