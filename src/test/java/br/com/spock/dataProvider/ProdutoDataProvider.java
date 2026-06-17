package br.com.spock.dataProvider;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import br.com.spock.model.TypeProduto;

public class ProdutoDataProvider {

    public static Stream<Arguments> cenariosPortTipoProduto(){
        return Stream.of(
            Arguments.of(TypeProduto.COMPUTADOR, 3),
            Arguments.of(TypeProduto.CELULAR, 0)
        );
    }

    public static Stream<Arguments> cenariosPorFaixaPreco(){
         return Stream.of(
            Arguments.of(4000.0, 6000.0, 3),
            Arguments.of(4000.0, 4499.0, 1),
            Arguments.of(0.0, 3999.0, 0)
         );
    }
}
