package com.lojinha.implementacao.controller;

import com.lojinha.implementacao.db.DbConnection;
import com.lojinha.implementacao.expert.FormaPagamento;
import com.lojinha.implementacao.vo.ProdutoVendaVo;
import com.lojinha.implementacao.vo.ProdutoVo;
import com.lojinha.implementacao.vo.VendaVo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class VendaController {

    private static VendaController instance = null;

    public static VendaController getInstance() {
        if (instance == null) {
            return (instance = new VendaController());
        }
        return instance;
    }

    private VendaController() {

    }

    public void iniciarProcessoAdicaoItem(VendaVo vendaVo, Scanner sc) {
        List<ProdutoVo> produtos = DbConnection.getItensDisponiveis();
        HashMap<String, ProdutoVo> mapParaSelecionar = new HashMap<>();
        for (ProdutoVo produtoVo : produtos) {
            mapParaSelecionar.put(produtoVo.getId().toString(), produtoVo);
        }
        vendaVo.setProdutos(new ArrayList<>());
        vendaVo.setTotal(BigDecimal.ZERO);
        while (true) {
            for (ProdutoVo produtoVo : produtos) {
                System.out.println(produtoVo.getId() + " - " + produtoVo.getNome());
            }
            System.out.println("Para sair da seleção, digite qualquer valor não presente na lista");
            String resposta = sc.nextLine();
            if (mapParaSelecionar.containsKey(resposta)) {
                System.out.println("A quantidade: ");
                String quantidade = sc.nextLine();
                int quantidadeInt = 0;
                while (true) {
                    try {
                        if (quantidade.equals("0")) {
                            System.out.println("Quantidade invalida!");
                        } else {
                            quantidadeInt = Integer.parseInt(quantidade);
                            break;
                        }
                    } catch (Exception e) {
                        System.out.println("Quantidade invalida!");
                    }
                }
                ProdutoVendaVo produtoVendaVo = new ProdutoVendaVo();
                produtoVendaVo.setProduto(mapParaSelecionar.get(resposta));
                produtoVendaVo.setQuantidade(quantidadeInt);
                vendaVo.getProdutos().add(produtoVendaVo);
                vendaVo.setTotal(vendaVo.getTotal().add(mapParaSelecionar.get(resposta).getPreco()));
            } else {
                break;
            }
        }
    }

    public void iniciarProcessoEscolhaFormaPagamento(VendaVo vendaVo, Scanner sc) {
        List<FormaPagamento> formasPagamento = DbConnection.getFormasPagamento();
        HashMap<String, FormaPagamento> mapParaSelecionar = new HashMap<>();
        for (FormaPagamento formaPagamento : formasPagamento) {
            mapParaSelecionar.put(formaPagamento.getId().toString(), formaPagamento);
        }
        while (true) {
            for (FormaPagamento formaPagamento : formasPagamento) {
                System.out.println(formaPagamento.getId() + " - " + formaPagamento.getDescricao());
            }
            String resposta = sc.nextLine();
            if (mapParaSelecionar.containsKey(resposta)) {
                vendaVo.setFormaPagamento(mapParaSelecionar.get(resposta));
                break;
            } else {
                System.out.println("Forma de pagamento invalida!");
            }
        }
    }

    public void iniciarProcessoFinalizarVenda(VendaVo vendaVo, Scanner sc) {
        System.out.println("Sua venda deu um total de: R$ " + vendaVo.getTotal());
        System.out.println("Os itens selecionados e a quantidade foram: ");
        for (ProdutoVendaVo produtoVendaVo : vendaVo.getProdutos()) {
            System.out.println(produtoVendaVo.getProduto().getNome() + " - " + produtoVendaVo.getQuantidade());
        }
        System.out.println("E a forma de pagamento escolhida foi: " + vendaVo.getFormaPagamento().getDescricao());
    }
}
