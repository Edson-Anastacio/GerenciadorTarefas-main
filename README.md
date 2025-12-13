# Gerenciador de Tarefas Pessoais (JavaFX)

Projeto desenvolvido para a disciplina de **Linguagem de Programação 2**.
O objetivo é uma aplicação Desktop para gerenciamento de tarefas pessoais (ToDo List) utilizando a arquitetura MVC.

---

## 👨‍💻 Autor

* **Nome:** Julio Edson Anastácio Rêgo
* **Matrícula:** 20230054260

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 25 (OpenJDK - Eclipse Adoptium)
* **Interface Gráfica:** JavaFX 23.0.1
* **Gerenciador de Dependências:** Apache Maven
* **IDE:** Visual Studio Code

---

## ⚙️ Funcionalidades

O sistema implementa um CRUD completo de tarefas com as seguintes funções:

* ✅ **Adicionar Tarefa:** Criação de novas tarefas com Título, Descrição e Prioridade (Baixa, Média, Alta).
* ✏️ **Editar Tarefa:** Alteração de dados de tarefas já existentes.
* 🗑️ **Remover Tarefa:** Exclusão de itens com confirmação de segurança via *Alert Dialog*.
* ✔️ **Concluir:** Checkbox interativo na tabela para marcar/desmarcar tarefas como concluídas.
* 🔍 **Filtros Dinâmicos:** Filtragem da lista em tempo real:
    * *Todas*
    * *Ativas (Pendentes)*
    * *Concluídas*

---

## 🏗️ Arquitetura do Projeto (MVC)

O projeto está organizado seguindo o padrão **Model-View-Controller**:

* **Model:** Classes que representam os dados (`Task`) e a lógica de negócios (`TaskService`).
* **View:** Arquivos `.fxml` que definem a interface visual (`main-view.fxml`, `task-dialog.fxml`).
* **Controller:** Classes que conectam a tela à lógica (`MainController`, `TaskDialogController`).

---

## 🛠️ Como Rodar o Projeto

### Pré-requisitos
* JDK 21 ou superior (Projeto configurado para JDK 25).
* Maven instalado ou integrado à IDE.

### ⚠️ Importante: Execução no VS Code
Devido ao funcionamento dos módulos do JavaFX em versões recentes do JDK, **não execute** o arquivo `Main.java` diretamente pelo botão de "Play" padrão, pois isso pode causar o erro *"Runtime Components Missing"*.

**Opção 1 (Recomendada - Via Launcher):**
1.  Abra o arquivo `src/main/java/com/projeto/Launcher.java`.
2.  Clique em **Run** (Executar) neste arquivo.
3.  O `Launcher` se encarrega de carregar as dependências antes de iniciar a aplicação.

**Opção 2 (Via Maven):**
1.  Abra o terminal na pasta do projeto.
2.  Execute o comando:
    ```bash
    mvn javafx:run
    ```

