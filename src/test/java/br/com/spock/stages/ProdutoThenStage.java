package br.com.spock.stages;

import java.util.List;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import br.com.spock.model.Produto;

public class ProdutoThenStage extends Stage<ProdutoThenStage>{

    @ExpectedScenarioState
    protected Double total;

    @ExpectedScenarioState
    List<Produto> produtos;

    @ExpectedScenarioState
    Produto produto;

    @ExpectedScenarioState
    List<String> nomes;

    public ProdutoThenStage o_total_deve_ser(Double esperado){
        assert total.equals(esperado): "Total esperado: " + esperado + "obtido: " + total;
        return self();
    }

    public ProdutoThenStage a_quantidade_produtos_deve_ser(int qntProdutosEsperada){
        assert produtos.size() == qntProdutosEsperada : "A quantidade de produtos esperada: "+ qntProdutosEsperada + "quantidade produtos obtida: " + produtos.size(); 
        return self();
    }

    public ProdutoThenStage o_primeiro_produto_deve_ter_preco(Produto produtoEsperado){
        assert produto.getPreco() == produtoEsperado.getPreco(): "Esperado: " + produtoEsperado.getPreco() + "produto obtido: " + produto.getPreco();
        return self();
    }
}
