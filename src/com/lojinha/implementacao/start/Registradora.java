package com.lojinha.implementacao.start;

import com.lojinha.implementacao.creator.VendaCreator;
import com.lojinha.implementacao.gui.GuiVenda;
import com.lojinha.implementacao.vo.VendaVo;

import java.util.Scanner;

public class Registradora {
    public static void main(String[] args) {
        VendaVo vendaVo = VendaCreator.getInstance().criarNovaVenda();
        Scanner sc = new Scanner(System.in);
        GuiVenda.inciarAtendimento(vendaVo, sc);
    }
}
