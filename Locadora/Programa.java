public class Programa {
    public static void main(String[] args) {
        
        Cliente c1 = new Cliente("Joao");

        c1.adicionaAluguel(new Aluguel(new Fita("Matrix", Fita.NORMAL), 3));
        c1.adicionaAluguel(new Aluguel(new Fita("Duna 2", Fita.LANCAMENTO), 2));
        c1.adicionaAluguel(new Aluguel(new Fita("Toy Story", Fita.INFANTIL), 4));
    
        System.out.println(c1.extrato());
    }
}