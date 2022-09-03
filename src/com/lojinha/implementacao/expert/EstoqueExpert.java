package com.lojinha.implementacao.expert;

public class EstoqueExpert {

    private static EstoqueExpert instance = null;

    public static EstoqueExpert getInstance() {
        if (instance == null) {
            return (instance = new EstoqueExpert());
        }
        return instance;
    }

    public Integer getQtdProdutoById(Long idProduto) {
        return 1;
    }

}
