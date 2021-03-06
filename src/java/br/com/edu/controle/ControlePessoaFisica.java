package br.com.edu.controle;

import br.com.edu.dao.CidadeDAO;
import br.com.edu.dao.PessoaFisicaDAO;
import br.com.edu.dao.ProdutoDAO;
import br.com.edu.dao.TipoEnderecoDAO;
import br.com.edu.util.Util;
import br.com.modeljpa.modelo.Cidade;
import br.com.modeljpa.modelo.Endereco;
import br.com.modeljpa.modelo.Permissao;
import br.com.modeljpa.modelo.PessoaFisica;
import br.com.modeljpa.modelo.Produto;
import br.com.modeljpa.modelo.TipoEndereco;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author Haylton
 */
@ManagedBean(name = "controlePessoaFisica")
@ViewScoped//volta pra viewscopped quando eu implementar o sing in e singup
public class ControlePessoaFisica implements Serializable {
    
    private PessoaFisicaDAO<PessoaFisica> dao;
    private PessoaFisica objeto;
    private CidadeDAO<Cidade> daoCidade;
    private TipoEnderecoDAO<TipoEndereco> daoTipoEndereco;
    private Endereco endereco;
    private Boolean novoEndereco;
    private ProdutoDAO<Produto> daoProduto;
    private Produto produto;


    
    public ControlePessoaFisica(){
        dao = new PessoaFisicaDAO<>();
        daoCidade = new CidadeDAO<>();
        daoTipoEndereco = new TipoEnderecoDAO<>();       
        daoProduto = new ProdutoDAO<>();

    }
    
    
//    public void imprimeProdutos(){
//        HashMap parametros = new HashMap();
//        UtilRelatorios.imprimeRelatorio("relatorioProdutos", parametros, daoProduto.getListaTodos());
//    }
//    
//    public void imprimePessoa(Integer id){
//        objeto = dao.localizar(id);
//        List<PessoaFisica> lista = new ArrayList<>();
//        lista.add(objeto);
//        HashMap parametros = new HashMap();
//        UtilRelatorios.imprimeRelatorio("relatorioPessoaFisica", parametros, lista);
//    }    
       
    public void adicionarDesejo(){
        if (produto != null){
            if (!objeto.getDesejos().contains(produto)){
                objeto.getDesejos().add(produto);
                Util.mensagemInformacao("Desejo adicionado com sucesso!");
            } else {
                Util.mensagemErro("Este desejo já existe na sua lista!");
            }
        }
    }
    
    public void removerDesejo(int index){
        produto = objeto.getDesejos().get(index);
        objeto.getDesejos().remove(produto);
        Util.mensagemInformacao("Desejo removido com sucesso!");
    }
    
    public String listar(){
        return "/privado/pessoafisica/listar?faces-redirect=true";
    }

    
    public void novoEndereco(){
        endereco = new Endereco();
        novoEndereco = true;
    }
    
    public void alterarEndereco(int index){
        endereco = objeto.getEnderecos().get(index);
        novoEndereco = false;
    }
    
    public void salvarEndereco(){
        if (novoEndereco){
            objeto.adicionarEndereco(endereco);
        }
        Util.mensagemInformacao("Endereço persistido com sucesso!");
    }
    
    public void removerEndereco(int index){
        objeto.removerEndereco(index);
        Util.mensagemInformacao("Endereço removido com sucesso!");
    }
    
    public void novo(){
        objeto = new PessoaFisica();        
    }
    
    public void salvar(PessoaFisica pessoaFisica){
        boolean persistiu = false;
        Permissao permissao = dao.temPermissaoUsuario();
        objeto = pessoaFisica;
        if (objeto.getId() == null){
            objeto.getPermissoes().add(permissao);
            persistiu = dao.persist(objeto);
        } else {
            objeto.getPermissoes().add(permissao);
            persistiu = dao.merge(objeto);
        }
        if (persistiu){
            Util.mensagemInformacao(dao.getMensagem());            
        } else {
            Util.mensagemErro(dao.getMensagem());            
        }
    }
    
    public void editar(Integer id){
        objeto = dao.localizar(id);        
    }
    
    public void remover(Integer id){
        objeto = dao.localizar(id);
        if (dao.remove(objeto)){
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
    }
    
   
    
    public PessoaFisicaDAO getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDAO dao) {
        this.dao = dao;
    }

    public PessoaFisica getObjeto() {
        return objeto;
    }

    public void setObjeto(PessoaFisica objeto) {
        this.objeto = objeto;
    }    

    public CidadeDAO<Cidade> getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDAO<Cidade> daoCidade) {
        this.daoCidade = daoCidade;
    }

    public TipoEnderecoDAO<TipoEndereco> getDaoTipoEndereco() {
        return daoTipoEndereco;
    }

    public void setDaoTipoEndereco(TipoEnderecoDAO<TipoEndereco> daoTipoEndereco) {
        this.daoTipoEndereco = daoTipoEndereco;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getNovoEndereco() {
        return novoEndereco;
    }

    public void setNovoEndereco(Boolean novoEndereco) {
        this.novoEndereco = novoEndereco;
    }

    public ProdutoDAO<Produto> getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDAO<Produto> daoProduto) {
        this.daoProduto = daoProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

  
    
    


}
