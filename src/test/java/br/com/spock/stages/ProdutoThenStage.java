package br.com.spock.stages;

import java.util.List;
import java.util.Optional;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import br.com.spock.model.Produto;
import br.com.spock.model.TypeProduto;
public class ProdutoThenStage extends Stage<ProdutoThenStage>{

    @ExpectedScenarioState
    Double resultado;

    @ExpectedScenarioState
    Integer quantidade;

    @ExpectedScenarioState
    List<Produto> produtos;

    @ExpectedScenarioState
    Optional<Produto> produto;

    @ExpectedScenarioState
    List<String> nomes;

    public ProdutoThenStage o_total_deve_ser(Double esperado){
        assert resultado.equals(esperado): "Total esperado: " + esperado + "obtido: " + resultado;
        return self();
    }

    public ProdutoThenStage a_media_deve_ser(Double esperado){
        assert resultado.equals(esperado):  "Total esperado: " + esperado + "obtido: " + resultado;
        return self();
    }

    public ProdutoThenStage a_quantidade_toal_produtos_deve_ser(Integer esperado){
        assert quantidade.equals(esperado): "Total de produtos esperado: " + esperado + "obtido: " + resultado;
        return self();
    }

    public ProdutoThenStage a_quantidade_produtos_deve_ser(int qntProdutosEsperada){
        assert produtos.size() == qntProdutosEsperada : "A quantidade de produtos esperada: "+ qntProdutosEsperada + "quantidade produtos obtida: " + produtos.size(); 
        return self();
    }

    public ProdutoThenStage produto_deve_ter_preco(Produto produtoEsperado){
        Produto p = getProdutoOuOptional();
        assert p.getPreco() == produtoEsperado.getPreco(): "Esperado: " + produtoEsperado.getPreco() + "produto obtido: " + p.getPreco();
        return self();
    }

    public ProdutoThenStage o_produto_deve_ter_nome(Produto produtoEsperado){
        Produto p = getProdutoOuOptional();
        assert p.getNomeProduto().equals(produtoEsperado.getNomeProduto()): "Nome Esperado: " + produtoEsperado.getNomeProduto() + "nome obtido: " + p.getNomeProduto();
        return self();
    }

    public ProdutoThenStage o_produto_deve_ter_tipo(TypeProduto tipoEsperado){
        assert produtos.stream()
            .allMatch(p -> p.getTipoProduto()  == tipoEsperado): "Tipo Esperado: " + tipoEsperado;
        return self();
    }

     public ProdutoThenStage o_produto_deve_ter_quantidade(int qntEsperada){
        Produto p = getProdutoOuOptional();
        assert p.getQuantidade() == qntEsperada;
        return self();
    }

     public ProdutoThenStage o_produto_deve_ter_id(String id){
        Produto p = getProdutoOuOptional();
        assert p.getId().equals(id);
        return self();
    }

    public ProdutoThenStage deve_retornar_lista_produto_vazia(){
        assert produtos.isEmpty();
        return self();
    }

    public ProdutoThenStage deve_retornar_optional_produto_vazio(){
        assert produto.isEmpty();
        return self();
    }

    public ProdutoThenStage deve_retornar_ordenado_crescente(List<String> nomesEsperados){
        List<String> nomesObtidos = produtos.stream()
        .map(Produto::getNomeProduto)
        .toList();

        assert nomesObtidos.equals(nomesEsperados)
        : "Ordem incorreta"
        + "Obtido:   " + nomesObtidos
        + "Esperado: " + nomesEsperados;

        return self();
    }

   private Produto getProdutoOuOptional(){
        assert produto.isPresent();
        return produto.get();
   }
}