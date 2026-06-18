package br.com.spock.service;

import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import com.tngtech.jgiven.junit5.ScenarioTest;
import br.com.spock.model.Produto;
import br.com.spock.model.TypeProduto;
import br.com.spock.stages.ProdutoGivenStage;
import br.com.spock.stages.ProdutoThenStage;
import br.com.spock.stages.ProdutoWhenStage;

public class ProdutoServiceTest extends ScenarioTest<ProdutoGivenStage, ProdutoWhenStage, ProdutoThenStage> {

    @Test
    void deve_calcular_soma_total(){
        given().um_catalago_padrao();
        when().calculo_o_total();
        then().o_total_deve_ser(14500.0);
    }

    @ParameterizedTest(name = "faixa R${0}-R${1} retorna {2} produto(s)")
    @MethodSource("br.com.spock.dataProvider.ProdutoDataProvider#cenariosPorFaixaPreco")
    void deve_filtrar_por_faixa_de_preco(double precoMin, double precoMax, int qntProdutosEsperada){
        given().um_catalago_padrao();
        when().busco_por_produtos_faixa_preco(precoMin, precoMax);
        then().a_quantidade_produtos_deve_ser(qntProdutosEsperada);
    }

    @ParameterizedTest(name = "tipo {0} retorna {1} produto(s)")
    @MethodSource("br.com.spock.dataProvider.ProdutoDataProvider#cenariosPortTipoProduto")
    void deve_filtrar_por_tipo_de_produto(TypeProduto typeProduto, int qntProdutosEsperada){
        given().um_catalago_padrao();
        when().busco_por_tipo_produto(typeProduto);
        then()
            .a_quantidade_produtos_deve_ser(qntProdutosEsperada)
            .o_produto_deve_ter_tipo(typeProduto);
    }

    @Test
    void deve_filtrar_produto_com_maior_quantidade(){
        given().um_catalago_padrao();
        when().busco_produto_com_maior_quantidade();
        then().o_produto_deve_ter_quantidade(3);
    }

    @Test
    void deve_filtrar_por_id_de_produto(){
        given().um_catalago_padrao();
        when().busco_por_id_produto("123");
        then().o_produto_deve_ter_id("123");
    }

    @Test
    void deve_retornar_optional_produto_vazio_ao_filtrar_por_id_inexistente(){
        given().um_catalago_padrao();
        when().busco_por_id_produto("999");
        then().deve_retornar_optional_produto_vazio();
    }

    @ParameterizedTest(name = "comparação com {0} retorna {2}")
    @MethodSource("br.com.spock.dataProvider.ProdutoDataProvider#cenariosCamposOrdenacaoCrescente")
    public void deve_ordenador_produtos(Comparator<Produto> comparator, List<String> esperada){
        given().um_catalago_padrao();
        when().ordeno_por(comparator);
        then().deve_retornar_ordenado_crescente(esperada);
    }
}