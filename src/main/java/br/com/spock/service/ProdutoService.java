package br.com.spock.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import br.com.spock.model.Produto;
import br.com.spock.model.TypeProduto;
 
@Service
public class ProdutoService {
 
	private  List<Produto> produtos=new ArrayList<>();
	public void init() {
	produtos =	List.of(new Produto("123","micro lenovo",TypeProduto.COMPUTADOR,4000.,2),
				  new Produto("456","micro hp",TypeProduto.COMPUTADOR,6000.,2),
				 new Produto("789","micro del",TypeProduto.COMPUTADOR,4500.,3)
				);		
	}
	public ProdutoService() {
		this.init();
	}
	public void adicionarProduto(Produto produto) {
		   this.produtos.add(produto);
	}
	public List<Produto> getProdutos(){
		return this.produtos;
	}

	public Double total(){
		return produtos.stream().mapToDouble(produto-> produto.getPreco()).sum();
	}
	public List<String> streamNomes() {
		Supplier<String> nomes= () ->  produtos.stream().map(prod->prod.getNomeProduto())
				.findFirst()
                .orElse("");
		return  produtos.
				 stream().filter(prod1 -> prod1.getPreco()>=4500.)
				 .map(prod-> nomes.get())
				 .collect(Collectors.toList());
	}

    public List<Produto> porTipo(TypeProduto tipo) {
        return produtos.stream()
                    .filter(p -> p.getTipoProduto() == tipo)
                    .collect(Collectors.toList());
    }

    public Optional<Produto> porMaiorQuantidade() {
        return produtos.stream()
            .max(Comparator.comparing(Produto::getQuantidade));        
    }

    public List<Produto> porFaixadePreço(double precoMinimo, double precoMaximo) {
        return produtos.stream()
                .filter(p -> p.getPreco() >= precoMinimo && p.getPreco() <= precoMaximo)
                .collect(Collectors.toList());
    }
}