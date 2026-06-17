package br.com.spock.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import com.tngtech.jgiven.junit5.ScenarioTest;
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
    @CsvSource({
        "4000.0, 6000.0, 3",
        "4000.0, 4499.0, 1",
        "0.0, 3999.0, 0" 
    })
    void deve_filtrar_por_faixa_de_preco(double precoMin, double precoMax, int qntProdutosEsperada){
        given().um_catalago_padrao();
        when().busco_por_produtos_faixa_preco(precoMin, precoMax);
        then().a_quantidade_produtos_deve_ser(qntProdutosEsperada);
    }
}