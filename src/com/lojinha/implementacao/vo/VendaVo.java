package com.lojinha.implementacao.vo;

import com.lojinha.implementacao.expert.FormaPagamento;

import java.math.BigDecimal;
import java.util.List;

public class VendaVo {

    private List<ProdutoVendaVo> produtos;

    private BigDecimal total;

    private FormaPagamento formaPagamento;

    public List<ProdutoVendaVo> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<ProdutoVendaVo> produtos) {
        this.produtos = produtos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}

