# Padrão de projeto GRASP.

Atividades e exercícios realizados durante a cadeira de projetos e arquitetura de sistemas na unifor.

# Classe responsável por representar a entidade Venda:

```java
public class VendaVo {

    private List<ProdutoVendaVo> produtos;

    private BigDecimal total;

    private FormaPagamento formaPagamento;

    /*Getters e Setters*/
}
```

# Classe responsável por representar a entidade Item:

```java
public class ProdutoVo {

    private Long id;

    private String nome;

    private BigDecimal preco;

    /*Getters e Setters*/
}
```

# Classe responsável por representar a relação entre a entidade Item e Venda:

```java
public class ProdutoVendaVo {

    private ProdutoVo produto;

    private Integer quantidade;

    /*Getters e Setters*/
}
```

# Classe responsável por representar a entidade FormaPagamento:

```java
public class FormaPagamento {

    private Long id;

    private String descricao;

    /*Getters e Setters*/
}
```

# Classe responsável por simular uma Interface gráfica:

```java
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
```

# Classe responsável por realizar o controle dos dados:

```java
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
```

# Classe responsável por dar início ao atendimento:

```java
public class Registradora {

    public static void main(String[] args) {
        VendaVo vendaVo = VendaCreator.getInstance().criarNovaVenda();
        Scanner sc = new Scanner(System.in);
        GuiVenda.inciarAtendimento(vendaVo, sc);
    }
}
```

# Exemplo de um atendimento:

```java
Olá! Seja bem vindo a lojinha!
Escolha um item:
1 - Laranja
2 - Maçã
3 - Banana
Para sair da seleção, digite qualquer valor não presente na lista
3
A quantidade:
50
1 - Laranja
2 - Maçã
3 - Banana
Para sair da seleção, digite qualquer valor não presente na lista
2
A quantidade:
34
1 - Laranja
2 - Maçã
3 - Banana
Para sair da seleção, digite qualquer valor não presente na lista
4
Escolha uma forma de pagamento:
1 - Crédito
2 - Débito
3
Forma de pagamento invalida!
1 - Crédito
2 - Débito
1
Sua venda deu um total de: R$ 56
Os itens selecionados e a quantidade foram:
Banana - 50
Maçã - 34
E a forma de pagamento escolhida foi: Crédito
```

# Classe responsável por simular conexão com um banco de dados:

```java
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

    /*Getters e Setters*/
}
```
