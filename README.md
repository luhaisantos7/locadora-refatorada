# Projeto Locadora - Refatoração (APS II)

Projeto prático desenvolvido para a disciplina de **Análise e Projeto de Sistemas II**, ministrada pelo professor **Raul Andrade** no **Centro Universitário de João Pessoa (UNIPÊ)**.

Este repositório contém a versão refatorada do projeto clássico da Locadora (baseado no estudo de caso de Martin Fowler), aplicando técnicas de refatoração para eliminação de *code smells* e melhoria do design orientado a objetos.

---

## Refatorações Realizadas

### 1ª Refatoração: Extração e Movimentação de Métodos (*Extract Method* & *Move Method*)
* **Classes envolvidas:** `Cliente.java` e `Aluguel.java`
* **Code Smells corrigidos:**
  * **Inveja dos Dados (*Feature Envy*):** A classe `Cliente` acessava excessivamente os dados de `Aluguel` e `Fita` para realizar os cálculos de preço e pontos de fidelidade.
  * **Método Longo (*Long Method*):** O método `extrato()` acumulava regras de cálculo misturadas com regras de formatação.
* **Solução aplicada:**
  * Extração e transferência dos métodos `getValor()` e `getPontosDeFidelizador()` para dentro da classe `Aluguel`.
  * `Cliente.java` passa a apenas delegar as chamadas para o aluguel (`aluguel.getValor()` e `aluguel.getPontosDeFidelizador()`).
* **Justificativa:**
  * Aplicação do padrão **Information Expert** (GRASP) e do princípio **Tell, Don't Ask**: quem detém os dados da locação (fita e dias) é a classe `Aluguel`, logo ela deve ser a responsável por calcular seus próprios valores. Reduz o acoplamento e aumenta a coesão de `Cliente`.

---

### 2ª Refatoração: Substituição de Variável Temporária por Consulta (*Replace Temp with Query*)
* **Classe envolvida:** `Cliente.java`
* **Code Smell corrigido:**
  * **Variáveis Temporárias Acumuladoras (*Temporary Variables*):** As variáveis `valorTotal` e `pontosDeFidelizador` ficavam presas dentro do laço `for` do método `extrato()`.
* **Solução aplicada:**
  * Remoção das variáveis temporárias do método `extrato()`.
  * Criação dos métodos de consulta independentes: `getValorTotal()` e `getPontosTotaisDeFidelizador()`.
* **Justificativa:**
  * **Reusabilidade e Extensibilidade:** Agora qualquer outra parte do sistema pode consultar o total devido ou os pontos acumulados chamando diretamente `cliente.getValorTotal()`, sem precisar gerar um relatório em texto para isso.
  * Facilita a criação futura de novos formatos de extrato (ex.: `extratoHTML()` ou exportação JSON) sem duplicar a lógica de cálculo.

---

## Validação e Testes
O comportamento observável do software foi mantido rigorosamente idêntico ao original, respeitando o princípio formal da refatoração. A execução da classe `Programa.java` valida a integridade dos cálculos:

```text
Registro de Alugueis de Joao
	Matrix	3.5
	Duna 2	6.0
	Toy Story	3.0
Valor total devido: 12.5
Voce acumulou 4 pontos de fidelizador