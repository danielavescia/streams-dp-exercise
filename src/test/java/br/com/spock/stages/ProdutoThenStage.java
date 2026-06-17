package br.com.spock.stages;

import java.util.List;
import java.util.Optional;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import br.com.spock.model.Produto;
import br.com.spock.model.TypeProduto;

public class ProdutoThenStage extends Stage<ProdutoThenStage>{

    @ExpectedScenarioState
    protected Double total;

    @ExpectedScenarioState
    List<Produto> produtos;

    @ExpectedScenarioState
    Optional<Produto> produto;

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

    public ProdutoThenStage produto_deve_ter_preco(Produto produtoEsperado){
        assert produto.isPresent();
        Produto p = produto.get();
        assert p.getPreco() == produtoEsperado.getPreco(): "Esperado: " + produtoEsperado.getPreco() + "produto obtido: " + p.getPreco();
        return self();
    }

    public ProdutoThenStage o_produto_deve_ter_nome(Produto produtoEsperado){
        assert produto.isPresent();
        Produto p = produto.get();
        assert p.getNomeProduto().equals(produtoEsperado.getNomeProduto()): "Nome Esperado: " + produtoEsperado.getNomeProduto() + "nome obtido: " + p.getNomeProduto();
        return self();
    }
    public ProdutoThenStage o_produto_deve_ter_tipo(TypeProduto tipoEsperado){
        assert produtos.stream()
            .allMatch(p -> p.getTipoProduto()  == tipoEsperado): "Tipo Esperado: " + tipoEsperado;
        return self();
    }
}