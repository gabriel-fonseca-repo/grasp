package com.lojinha.implementacao.db;

import com.lojinha.implementacao.expert.FormaPagamento;
import com.lojinha.implementacao.vo.ProdutoVo;

import java.math.BigDecimal;
import java.util.ArrayList;

public class DbConnection {

    private static ArrayList<ProdutoVo> itensDisponiveis;

    private static ArrayList<FormaPagamento> formasPagamento;

    static {
        itensDisponiveis = new ArrayList<>();
        ProdutoVo produtoVo = new ProdutoVo();
        produtoVo.setId(1L);
        produtoVo.setNome("Laranja");
        produtoVo.setPreco(new BigDecimal(23));
        itensDisponiveis.add(produtoVo);

        produtoVo = new ProdutoVo();
        produtoVo.setId(2L);
        produtoVo.setNome("Maçã");
        produtoVo.setPreco(new BigDecimal(26));
        itensDisponiveis.add(produtoVo);

        produtoVo = new ProdutoVo();
        produtoVo.setId(3L);
        produtoVo.setNome("Banana");
        produtoVo.setPreco(new BigDecimal(30));
        itensDisponiveis.add(produtoVo);

        formasPagamento = new ArrayList<>();
        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setId(1L);
        formaPagamento.setDescricao("Crédito");
        formasPagamento.add(formaPagamento);

        formaPagamento = new FormaPagamento();
        formaPagamento.setId(2L);
        formaPagamento.setDescricao("Débito");
        formasPagamento.add(formaPagamento);
    }

    public static ArrayList<ProdutoVo> getItensDisponiveis() {
        return itensDisponiveis;
    }

    public static void setItensDisponiveis(ArrayList<ProdutoVo> itensDisponiveis) {
        DbConnection.itensDisponiveis = itensDisponiveis;
    }

    public static ArrayList<FormaPagamento> getFormasPagamento() {
        return formasPagamento;
    }

    public static void setFormasPagamento(ArrayList<FormaPagamento> formasPagamento) {
        DbConnection.formasPagamento = formasPagamento;
    }
}
