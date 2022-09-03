package com.lojinha.implementacao.vo;

import java.util.HashMap;

public class EstoqueVo {

    private HashMap<String, Object> produtosQuantidadeEstoque;

    public HashMap<String, Object> getProdutosQuantidadeEstoque() {
        return produtosQuantidadeEstoque;
    }

    public void setProdutosQuantidadeEstoque(HashMap<String, Object> produtosQuantidadeEstoque) {
        this.produtosQuantidadeEstoque = produtosQuantidadeEstoque;
    }
}
