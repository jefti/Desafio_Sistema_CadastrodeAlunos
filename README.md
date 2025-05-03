# 🧠 Desafio: Sistema de Cadastro de Alunos

Este repositório contém a resolução do desafio proposto em Java para a criação de um sistema de cadastro e gerenciamento de alunos, utilizando conceitos de **orientação a objetos**.

## 📝 Enunciado

Crie um programa em **Java** que gerencie o cadastro de alunos de uma turma. O sistema deve permitir:

1. Cadastrar alunos com nome, matrícula e 3 notas  
2. Calcular a média de cada aluno  
3. Determinar se o aluno está **Aprovado**, em **Recuperação** ou **Reprovado**  
4. Exibir um relatório com nome, matrícula, notas, média e situação  
5. Utilizar **orientação a objetos** para modelar o aluno  

---

## 📦 Requisitos obrigatórios

✅ Criar uma classe `Aluno` com:

- **Atributos**:
  - `nome` (String)
  - `matricula` (String)
  - `notas` (array de 3 doubles)

- **Métodos**:
  - `calcularMedia()` → retorna a média das notas
  - `getSituacao()` → retorna:
    - `"Aprovado"` (média ≥ 7)
    - `"Recuperação"` (5 ≤ média < 7)
    - `"Reprovado"` (média < 5)
  - `exibirRelatorio()` → imprime os dados do aluno com média e situação

✅ No `main`:

- Criar um `ArrayList<Aluno>` para armazenar os objetos  
- Ler os dados do usuário com `Scanner` (quantidade de alunos, nome, matrícula e notas)  
- Armazenar cada aluno no `ArrayList`  
- Exibir um relatório completo de todos os alunos  

---

## ✏️ Exemplo de menu no `Main.java`

1 - Cadastrar aluno
2 - Listar alunos
3 - Buscar aluno por matrícula
4 - Atualizar nota de um aluno
5 - Sair


---

## 🔁 Funcionalidades Extras (opcional)

- Ordenar os alunos por média  
- Calcular a média geral da turma  
- Salvar o relatório em um arquivo `.txt`  

---

## 🚀 Tecnologias

- Java 17+
- IDE (recomendado: IntelliJ IDEA ou Eclipse)

## 📂 Estrutura do projeto (sugestão)

📁 src/
┣ 📄 Aluno.java
┣ 📄 Main.java

## ✅ Status do projeto

✅ Em desenvolvimento 
