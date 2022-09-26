package com.lojinha.implementacao.gui;

import com.lojinha.implementacao.controller.VendaController;
import com.lojinha.implementacao.vo.VendaVo;

import java.util.Scanner;

public class GuiVenda {
    public static void inciarAtendimento(VendaVo vendaVo, Scanner sc) {
        System.out.println("Olá! Seja bem vindo a lojinha!");
        System.out.println("Escolha um item: ");
        VendaController.getInstance().iniciarProcessoAdicaoItem(vendaVo, sc);
        System.out.println("Escolha uma forma de pagamento: ");
        VendaController.getInstance().iniciarProcessoEscolhaFormaPagamento(vendaVo, sc);
        VendaController.getInstance().iniciarProcessoFinalizarVenda(vendaVo, sc);
    }
}