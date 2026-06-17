package br.com.spock.stages;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import br.com.spock.service.ProdutoService;

public class ProdutoGivenStage extends Stage<ProdutoGivenStage>{
    
    @ProvidedScenarioState
    ProdutoService produtoService;

    public ProdutoGivenStage um_catalago_padrao(){
        produtoService = new ProdutoService();
        return self();
    }

    public ProdutoGivenStage um_catalago_vazio(){
        produtoService = new ProdutoService();
        produtoService.getProdutos().clear();
        return self();
    }
}