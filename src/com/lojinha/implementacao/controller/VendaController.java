package com.lojinha.implementacao.controller;

import com.lojinha.abstracao.controller.ControllerInterface;
import com.lojinha.implementacao.creator.VendaCreator;
import com.lojinha.implementacao.expert.VendaExpert;
import com.lojinha.implementacao.vo.ProdutoVendaVo;
import com.lojinha.implementacao.vo.VendaVo;

import java.math.BigDecimal;
import java.util.List;

public class VendaController implements ControllerInterface<VendaVo> {

    public static final String SUFIXO_QUANTIDADE = "quantidade";

    public static final String SUFIXO_PRODUTO = "produto";

    private static VendaController instance = null;

    public static VendaController getInstance() {
        if (instance == null) {
            return (instance = new VendaController());
        }
        return instance;
    }

    public BigDecimal realizarNovaVenda(List<ProdutoVendaVo> produtosSelecionados) {
        VendaVo venda = VendaCreator.getInstance().criarNovaVenda();
        for (ProdutoVendaVo produtoVenda : produtosSelecionados) {
            venda.getProdutos().put(produtoVenda.getProduto().getNome() + VendaController.SUFIXO_PRODUTO, produtoVenda.getProduto());
            venda.getQuantidade().put(produtoVenda.getProduto().getNome() + VendaController.SUFIXO_QUANTIDADE, produtoVenda.getQuantidade());
        }
        save(venda);
        EstoqueController.getInstance().realizarBaixa(venda.getProdutos(), venda.getQuantidade());
        return VendaExpert.getInstance().calcularValorTotalDaVenda(venda.getProdutos(), venda.getQuantidade());
    }
}
