import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private List<Aluguel> alugueis = new ArrayList<>();

    public Cliente(String nome) {
        this.nome = nome;
    }

    public void adicionaAluguel(Aluguel aluguel) {
        alugueis.add(aluguel);
    }

    public String getNome() {
        return nome;
    }

    public String extrato() {
        String resultado = "Registro de Alugueis de " + getNome() + "\n";

        for (Aluguel aluguel : alugueis) {
            // Mostra o valor calculado de cada fita alugada
            resultado += "\t" + aluguel.getFita().getTitulo() + "\t" + String.valueOf(aluguel.getValor()) + "\n";
        }

        // Rodapé: variáveis temporárias substituídas pelas chamadas de consulta direta
        resultado += "Valor total devido: " + String.valueOf(getValorTotal()) + "\n";
        resultado += "Voce acumulou " + String.valueOf(getPontosTotaisDeFidelizador()) + " pontos de fidelizador";
        return resultado;
    }

    // 2ª Refatoração: método de consulta para o total devido
    public double getValorTotal() {
        double total = 0;
        for (Aluguel aluguel : alugueis) {
            total += aluguel.getValor();
        }
        return total;
    }

    // 2ª Refatoração: método de consulta para o total de pontos
    public int getPontosTotaisDeFidelizador() {
        int totalPontos = 0;
        for (Aluguel aluguel : alugueis) {
            totalPontos += aluguel.getPontosDeFidelizador();
        }
        return totalPontos;
    }
}