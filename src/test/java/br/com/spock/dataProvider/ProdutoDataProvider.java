package br.com.spock.dataProvider;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;
import br.com.spock.model.Produto;
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

     public static Stream<Arguments> cenariosPorBuscaNome(){
        Produto lenovo = new Produto("123", "micro lenovo", TypeProduto.COMPUTADOR, 4000., 2);
        Produto hp     = new Produto("456", "micro hp",     TypeProduto.COMPUTADOR, 6000., 2);
        Produto del   = new Produto("789", "micro del",   TypeProduto.COMPUTADOR, 4500., 3);

    return Stream.of(
        Arguments.of("micro lenovo", lenovo),
        Arguments.of("micro hp",     hp),
        Arguments.of("micro del",   del)
    );
    }

    public static Stream <Arguments> cenariosCamposOrdenacaoCrescente(){
        return Stream.of(
            //nome
            Arguments.of(
                Comparator.comparing(Produto::getNomeProduto), 
                List.of("micro del", "micro hp", "micro lenovo")),

            //preco    
            Arguments.of(
                Comparator.comparing(Produto::getPreco), 
                List.of("micro lenovo", "micro del", "micro hp")),

            //quantidade
                Arguments.of(
                Comparator.comparing(Produto::getQuantidade), 
                List.of("micro lenovo", "micro hp", "micro del")),

            //id
            Arguments.of(
                Comparator.comparing(Produto::getId), 
                List.of("micro lenovo", "micro hp", "micro del"))
            );     
    }

    public static Stream <Arguments> cenariosCamposOrdenacaoDecrescente(){
        return Stream.of(
            //nome
            Arguments.of(
                Comparator.comparing(Produto::getNomeProduto), 
                List.of("micro lenovo", "micro hp", "micro del")),

            //preco    
            Arguments.of(
                Comparator.comparing(Produto::getPreco), 
                List.of("micro hp", "micro del", "micro lenovo")),

            //quantidade
                Arguments.of(
                Comparator.comparing(Produto::getQuantidade), 
                List.of("micro del", "micro hp", "micro lenovo")),

            //id
            Arguments.of(
                Comparator.comparing(Produto::getId), 
                 List.of("micro del", "micro hp", "micro lenovo"))
            );     
    } 
}
