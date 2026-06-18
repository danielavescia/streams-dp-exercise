package br.com.spock.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import br.com.spock.model.Produto;
import br.com.spock.model.TypeProduto;
 
@Service
public class ProdutoService {
 
	private  List<Produto> produtos=new ArrayList<>();
	public void init() {
	produtos =	List.of(new Produto("123","micro lenovo",TypeProduto.COMPUTADOR,4000.,1),
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

	//Agregações
	public Double total(){
		return produtos.stream().mapToDouble(produto-> produto.getPreco()).sum();
	}

	public Double media(){
		return produtos.stream()
        .mapToDouble(Produto::getPreco).average()
        .stream().map(avg -> Math.round(avg * 100.0) / 100.0)
        .findFirst()
        .orElse(0.0);
	}

	public Integer quantidadeTotalProdutos(){
		return produtos.stream().mapToInt(produto -> produto.getQuantidade()).sum();
	}

	//Filtros
    public List<Produto> porTipo(TypeProduto tipo) {
        return produtos.stream()
                    .filter(p -> p.getTipoProduto() == tipo)
                    .collect(Collectors.toList());
    }

    public Optional<Produto> porMaiorQuantidade() {
        return produtos.stream()
            .max(Comparator.comparing(Produto::getQuantidade));        
    }

	public Optional<Produto> porMenorQuantidade() {
        return produtos.stream()
            .min(Comparator.comparing(Produto::getQuantidade));        
    }

    public List<Produto> porFaixadePreço(double precoMinimo, double precoMaximo) {
        return produtos.stream()
                .filter(p -> p.getPreco() >= precoMinimo && p.getPreco() <= precoMaximo)
                .collect(Collectors.toList());
    }

    public Optional<Produto> porId(String id){
        return produtos.stream()
            .filter(p -> p.getId().equals(id))
            .findFirst();
    }

	public Optional<Produto> porNome(String name){
		return produtos.stream()
			.filter(produto -> produto.getNomeProduto().equals(name))
			.findFirst();
	}

	public List<Produto> porNomeContendo(String trecho){
		return produtos.stream()
			.filter(produto -> produto.getNomeProduto().contains(trecho))
			.toList();
	}

	public List<Produto> comQuantidadeAbaixoDe(Integer quantidade){
		return produtos.stream()
			.filter(p -> p.getQuantidade() < quantidade)
			.toList();
	}

	public List<Produto> comQuantidadeAcimaDe(Integer quantidade){
		return produtos.stream()
			.filter(p -> p.getQuantidade() > quantidade)
			.toList();
	}

	//Ordenação
	//Crescente
	public List<Produto> ordenarProdutoPorCampoCrescente(Comparator<Produto>comparator){
		return produtos.stream().sorted(comparator)
		.toList();
	}

	//Ordenação Decrescente
	public List<Produto> ordenarProdutoPorCampoDecrescente(Comparator<Produto>comparator){
		return produtos.stream().sorted(comparator.reversed())
		.toList();
	}
}