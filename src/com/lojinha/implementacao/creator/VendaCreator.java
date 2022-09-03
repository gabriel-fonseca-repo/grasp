package com.lojinha.implementacao.creator;

import com.lojinha.implementacao.vo.VendaVo;

public class VendaCreator {

    private static VendaCreator instance = null;

    public static VendaCreator getInstance() {
        if (instance == null) {
            return (instance = new VendaCreator());
        }
        return instance;
    }

    public VendaVo criarNovaVenda() {
        return new VendaVo();
    }
}
