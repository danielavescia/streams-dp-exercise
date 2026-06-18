package br.com.spock.stages;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import br.com.spock.model.Produto;
import br.com.spock.model.TypeProduto;
import br.com.spock.service.ProdutoService;

public class ProdutoWhenStage extends Stage<ProdutoWhenStage> {

    @ExpectedScenarioState
    ProdutoService produtoService;

    @ProvidedScenarioState
    Double resultado;

    @ProvidedScenarioState
    Integer quantidade;

    @ProvidedScenarioState
    List<Produto> produtos;

    @ProvidedScenarioState
    Optional<Produto> produto;

    @ProvidedScenarioState
    List<String> nomes;

    public ProdutoWhenStage calculo_o_total() {
        resultado = produtoService.total();
        return self();
    }

    public ProdutoWhenStage calculo_a_media(){
        resultado = produtoService.media();
        return self();
    }

     public ProdutoWhenStage calculo_a_quantidade_total(){
        quantidade = produtoService.quantidadeTotalProdutos();
        return self();
    }

    public ProdutoWhenStage busco_por_produtos_faixa_preco(double precoMin, double precoMax){
        produtos = produtoService.porFaixadePreço(precoMin, precoMax);
        return self();
    }

    public ProdutoWhenStage busco_produto_com_maior_quantidade(){
        produto = produtoService.porMaiorQuantidade();
        return self();
    }

    public ProdutoWhenStage busco_produto_com_menor_quantidade(){
        produto = produtoService.porMenorQuantidade();
        return self();
    }

    public ProdutoWhenStage busco_por_tipo_produto(TypeProduto tipo) {
        produtos = produtoService.porTipo(tipo);
        return self();
    }

    public ProdutoWhenStage busco_por_id_produto(String id) {
        produto = produtoService.porId(id);
        return self();
    }

    public ProdutoWhenStage busco_por_nome_produto(String nome) {
        produto = produtoService.porNome(nome);
        return self();
    }

    public ProdutoWhenStage ordeno_por(Comparator<Produto> comparator) {
        produtos = produtoService.ordenarProdutoPorCampo(comparator);
        return self();
    }
}
