package br.edu.ifsul.controle;

import br.edu.ifsul.dao.MontadoraDAO;
import br.edu.ifsul.dao.VeiculoDAO;
import br.edu.ifsul.modelo.Instrutor;
import br.edu.ifsul.modelo.Montadora;
import br.edu.ifsul.modelo.Veiculo;
import br.edu.ifsul.util.Util;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author ubiratan
 */
@Named(value = "controleVeiculo")
@SessionScoped
public class ControleVeiculo implements Serializable{

    @EJB
    private VeiculoDAO<Veiculo> dao;
    private Veiculo objeto;
    private Boolean editando;
    @EJB
    private MontadoraDAO<Montadora> daoMontadora;
    
    private Boolean editandoInstrutor;
    private Instrutor instrutor;
    private Boolean novoInstrutor;
    
    public ControleVeiculo(){
        editando = false;
        editandoInstrutor = false;
    }
    
    public String listar(){
        editando = false;
        return "/privado/veiculo/listar?faces-redirect=true";
    }
    
    public void novo(){
        editando = true;
        editandoInstrutor = false;
        objeto = new Veiculo();
    }
    
    public void alterar(Object id){
        try {
            objeto = dao.getObjectById(id);
            editando = true;
            editandoInstrutor = false;
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
    
    public void novoInstrutor() {
        instrutor = new Instrutor();
        editandoInstrutor = true;
        novoInstrutor = true;
    }

    public void salvarInstrutor() {
        if (instrutor.getId() == null) {
            if (novoInstrutor) {
                objeto.adicionarInstrutor(instrutor);
            }
        }
        editandoInstrutor = false;
        Util.mensagemInformacao("Instrutor persistido com sucesso!");
    }

    public void alterarInstrutor(int index) {
        instrutor = objeto.getInstrutores().get(index);
        editandoInstrutor = true;
        novoInstrutor = false;
    }

    public void excluirInstrutor(int index) {
        objeto.removerInstrutor(index);
        Util.mensagemInformacao("Instrutor removido com sucesso!");
    }

    public VeiculoDAO<Veiculo> getDao() {
        return dao;
    }

    public void setDao(VeiculoDAO<Veiculo> dao) {
        this.dao = dao;
    }

    public Veiculo getObjeto() {
        return objeto;
    }

    public void setObjeto(Veiculo objeto) {
        this.objeto = objeto;
    }

    public Boolean getEditando() {
        return editando;
    }

    public void setEditando(Boolean editando) {
        this.editando = editando;
    }

    public MontadoraDAO<Montadora> getDaoMontadora() {
        return daoMontadora;
    }

    public void setDaoMontadora(MontadoraDAO<Montadora> daoMontadora) {
        this.daoMontadora = daoMontadora;
    }

    public Boolean getEditandoInstrutor() {
        return editandoInstrutor;
    }

    public void setEditandoInstrutor(Boolean editandoInstrutor) {
        this.editandoInstrutor = editandoInstrutor;
    }

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
    }

    public Boolean getNovoInstrutor() {
        return novoInstrutor;
    }

    public void setNovoInstrutor(Boolean novoInstrutor) {
        this.novoInstrutor = novoInstrutor;
    }

}
