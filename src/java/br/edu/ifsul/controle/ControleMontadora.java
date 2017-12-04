/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MontadoraDAO;
import br.edu.ifsul.dao.PaisOrigemDAO;
import br.edu.ifsul.modelo.Montadora;
import br.edu.ifsul.modelo.Pais_Origem;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ubiratan
 */
@Named(value = "controleMontadora")
@SessionScoped
public class ControleMontadora implements Serializable{
    @EJB
    private MontadoraDAO<Montadora> dao;
    private Montadora objeto;
    private Boolean editando;
    @EJB
    private PaisOrigemDAO<Pais_Origem> daoPaisOrigem;
    
    public ControleMontadora(){
        editando = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/montadora/listar?faces-redirect=true";
    }
    
    public void novo(){
        objeto = new Montadora();
        editando = true;
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);
            editando = true;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao carregar objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void excluir(Object id){
        try {
            objeto = dao.getObjectById(id);
            dao.remove(objeto);
            Util.mensagemInformacao("Objeto removido com sucesso!");
        } catch (Exception e) {
            Util.mensagemErro("Erro ao remover o objeto: " + Util.getMensagemErro(e));
        }
    }
    
    public void salvar(){
        try {
            if(objeto.getId() == null){
                dao.persist(objeto);
            }else{
                dao.merge(objeto);
            }
            Util.mensagemInformacao("Objeto persistido com sucesso!");
            editando = false;
        } catch (Exception e) {
            Util.mensagemErro("Erro ao persistir objeto: " + Util.getMensagemErro(e));
        }
    }

    public MontadoraDAO<Montadora> getDao() {
        return dao;
    }

    public void setDao(MontadoraDAO<Montadora> dao) {
        this.dao = dao;
    }

    public Montadora getObjeto() {
        return objeto;
    }

    public void setObjeto(Montadora objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public PaisOrigemDAO<Pais_Origem> getDaoPaisOrigem() {
        return daoPaisOrigem;
    }

    public void setDaoPaisOrigem(PaisOrigemDAO<Pais_Origem> daoPaisOrigem) {
        this.daoPaisOrigem = daoPaisOrigem;
    }


}
