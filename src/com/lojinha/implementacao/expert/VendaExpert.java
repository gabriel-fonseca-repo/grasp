package com.lojinha.implementacao.expert;

import com.lojinha.implementacao.controller.VendaController;
import com.lojinha.implementacao.vo.ProdutoVo;
import com.lojinha.implementacao.vo.VendaVo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class VendaExpert {

    private static VendaExpert instance = null;

    public static VendaExpert getInstance() {
        if (instance == null) {
            return (instance = new VendaExpert());
        }
        return instance;
    }

    public BigDecimal calcularValorTotalDaVenda(HashMap<String, ProdutoVo> produtos, HashMap<String, Integer> quantidades) {
        BigDecimal precoTotal = BigDecimal.ZERO;
        for (ProdutoVo produto : produtos.values()) {
            BigDecimal precoProduto = produto.getPreco();
            Integer quantidade = quantidades.get(produto.getNome() + VendaController.SUFIXO_QUANTIDADE);
            BigDecimal precoTotalProduto = precoProduto.multiply(BigDecimal.valueOf(quantidade));
            precoTotal = precoTotal.add(precoTotalProduto);
        }
        return precoTotal;
    }

    public List<VendaVo> getVendasDoDia() {
        return null;
    }
}
