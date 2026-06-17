package br.com.spock.stages;

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
    Double total;

    @ProvidedScenarioState
    List<Produto> produtos;

    @ProvidedScenarioState
    Optional<Produto> produto;

    @ProvidedScenarioState
    List<String> nomes;

    public ProdutoWhenStage calculo_o_total() {
        total = produtoService.total();
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

    public ProdutoWhenStage busco_por_tipo_produto(TypeProduto tipo) {
        produtos = produtoService.porTipo(tipo);
        return self();
    }

    public ProdutoWhenStage busco_por_id_produto(String id) {
        produto = produtoService.porId(id);
        return self();
    }
}
