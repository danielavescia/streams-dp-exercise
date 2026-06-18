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
    void deve_calcular_soma_total() {
        given().um_catalago_padrao();
        when().calculo_o_total();
        then().o_total_deve_ser(14500.0);
    }

    @Test
    void deve_calcular_media() {
        given().um_catalago_padrao();
        when().calculo_a_media();
        then().a_media_deve_ser(4833.33);
    }

    @Test
    void deve_retornar_quantidade_total_produtos() {
        given().um_catalago_padrao();
        when().calculo_a_quantidade_total();
        then().a_quantidade_toal_produtos_deve_ser(6);
    }

    @ParameterizedTest(name = "faixa R${0}-R${1} retorna {2} produto(s)")
    @MethodSource("br.com.spock.dataProvider.ProdutoDataProvider#cenariosPorFaixaPreco")
    void deve_filtrar_por_faixa_de_preco(double precoMin, double precoMax, int qntProdutosEsperada) {
        given().um_catalago_padrao();
        when().busco_por_produtos_faixa_preco(precoMin, precoMax);
        then().a_quantidade_produtos_deve_ser(qntProdutosEsperada);
    }

    @ParameterizedTest(name = "tipo {0} retorna {1} produto(s)")
    @MethodSource("br.com.spock.dataProvider.ProdutoDataProvider#cenariosPortTipoProduto")
    void deve_filtrar_por_tipo_de_produto(TypeProduto typeProduto, int qntProdutosEsperada) {
        given().um_catalago_padrao();
        when().busco_por_tipo_produto(typeProduto);
        then()
                .a_quantidade_produtos_deve_ser(qntProdutosEsperada)
                .o_produto_deve_ter_tipo(typeProduto);
    }

    @Test
    void deve_filtrar_produto_com_maior_quantidade() {
        given().um_catalago_padrao();
        when().busco_produto_com_maior_quantidade();
        then().o_produto_deve_ter_quantidade(3);
    }

    @Test
    void deve_filtrar_produto_com_menor_quantidade() {
        given().um_catalago_padrao();
        when().busco_produto_com_menor_quantidade();
        then().o_produto_deve_ter_quantidade(1);
    }

    @Test
    void deve_filtrar_por_id_de_produto() {
        given().um_catalago_padrao();
        when().busco_por_id_produto("123");
        then().o_produto_deve_ter_id("123");
    }

    @Test
    void deve_retornar_optional_produto_vazio_ao_filtrar_por_id_inexistente() {
        given().um_catalago_padrao();
        when().busco_por_id_produto("999");
        then().deve_retornar_optional_produto_vazio();
    }

    @ParameterizedTest(name = "Filtrar por nome {0}, deve retornar o produto com nome{2}")
    @MethodSource("br.com.spock.dataProvider.ProdutoDataProvider#cenariosPorBuscaNome")
    void deve_filtrar_por_nome_produto(String nome, Produto esperado) {
        given().um_catalago_padrao();
        when().busco_por_nome_produto(nome);
        then().o_produto_deve_ter_nome(esperado);
    }

    @ParameterizedTest(name = "comparação com {0} retorna {2}")
    @MethodSource("br.com.spock.dataProvider.ProdutoDataProvider#cenariosCamposOrdenacaoCrescente")
    void deve_ordenador_produtos_por_campo_crescente(Comparator<Produto> comparator, List<String> esperada) {
        given().um_catalago_padrao();
        when().ordeno_por_campo_crescente(comparator);
        then().deve_retornar_ordenado(esperada);
    }

    @ParameterizedTest(name = "comparação com {0} retorna {2}")
    @MethodSource("br.com.spock.dataProvider.ProdutoDataProvider#cenariosCamposOrdenacaoDecrescente")
    void deve_ordenador_produtos_por_campo_decrescente(Comparator<Produto> comparator, List<String> esperada) {
        given().um_catalago_padrao();
        when().ordeno_por_campo_decrescente(comparator);
        then().deve_retornar_ordenado(esperada);
    }

    @Test
    void deve_retornar_produtos_com_quantidade_abaixo_de() {
        given().um_catalago_padrao();
        when().busco_por_quantidade_abaixo_de(2);
        then().a_quantidade_produtos_deve_ser(1);
    }

    @Test
    void deve_retornar_produtos_com_quantidade_acima_de() {
        given().um_catalago_padrao();
        when().busco_por_quantidade_acima_de(2);
        then().a_quantidade_produtos_deve_ser(1);
    }

    @Test
    void deve_retornar_produtos_contendo_trecho(){
        given().um_catalago_padrao();
        when().filtro_nome_por_trecho("micro");
        then()
            .produtos_devem_ter_trecho("micro")
            .a_quantidade_produtos_deve_ser(3);
    }
}