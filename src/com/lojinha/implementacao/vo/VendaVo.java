package com.lojinha.implementacao.vo;

import java.util.HashMap;

public class VendaVo {

    private HashMap<String, ProdutoVo> produtos;

    private HashMap<String, Integer> quantidade;

    public HashMap<String, Integer> getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(HashMap<String, Integer> quantidade) {
        this.quantidade = quantidade;
    }

    public HashMap<String, ProdutoVo> getProdutos() {
        return produtos;
    }

    public void setProdutos(HashMap<String, ProdutoVo> produtos) {
        this.produtos = produtos;
    }
}
