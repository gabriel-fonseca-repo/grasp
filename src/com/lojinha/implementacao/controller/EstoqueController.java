package com.lojinha.implementacao.controller;

import com.lojinha.abstracao.controller.ControllerInterface;
import com.lojinha.implementacao.expert.EstoqueExpert;
import com.lojinha.implementacao.vo.EstoqueVo;
import com.lojinha.implementacao.vo.ProdutoVo;

import java.util.HashMap;

public class EstoqueController implements ControllerInterface<EstoqueVo> {

    private static EstoqueController instance = null;

    public static EstoqueController getInstance() {
        if (instance == null) {
            return (instance = new EstoqueController());
        }
        return instance;
    }

    public void realizarBaixa(HashMap<String, ProdutoVo> produtos, HashMap<String, Integer> quantidades) {
        for (ProdutoVo produto : produtos.values()) {
            Integer qtdProduto = EstoqueExpert.getInstance().getQtdProdutoById(produto.getId());
            Integer novaQtdProduto = qtdProduto - quantidades.get(produto.getNome() + VendaController.SUFIXO_QUANTIDADE);
            alterarQtdProdutoById(produto.getId(), novaQtdProduto);
        }
    }

    public void alterarQtdProdutoById(Long idProduto, Integer novaQtdProduto) {
    }

}
