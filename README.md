# 📝 Gerenciador de Tarefas Pessoais (JavaFX + Docker)

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/javafx-%23FF0000.svg?style=for-the-badge&logo=java&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)

> Projeto desenvolvido para a disciplina de **Linguagem de Programação 2**.
> Aplicação Desktop para gerenciamento de tarefas (ToDo List) com **persistência de dados em PostgreSQL** containerizado.

## 📸 Demonstração
<img src="assets/print-tela.png" alt="Tela do Sistema" width="700">

---

## 👨‍💻 Autor

* **Nome:** Julio Edson Anastácio Rêgo
* **Matrícula:** 20230054260

---

## 🚀 Tecnologias Utilizadas

* **Linguagem:** Java 25 (OpenJDK - Eclipse Adoptium)
* **Interface Gráfica:** JavaFX 23.0.1
* **Banco de Dados:** PostgreSQL (via Docker)
* **Automação:** DockerService (Java ProcessBuilder)
* **Gerenciador de Dependências:** Apache Maven
* **IDE:** Visual Studio Code

---

## ⚙️ Funcionalidades

O sistema implementa um CRUD completo com infraestrutura automatizada:

* ✅ **Auto-Bootstrap:** O sistema verifica, baixa e inicia o container do Docker automaticamente ao abrir.
* ✅ **Persistência Real:** As tarefas são salvas no PostgreSQL.
* ➕ **Adicionar Tarefa:** Criação de novas tarefas com Título, Descrição e Prioridade.
* ✏️ **Editar Tarefa:** Alteração de dados com atualização imediata no banco de dados.
* 🗑️ **Remover Tarefa:** Exclusão definitiva do registro no banco.
* ✔️ **Status:** Checkbox interativo que salva o estado (pendente/concluída).
* 🔍 **Filtros Dinâmicos:** Filtragem visual (Todas / Ativas / Concluídas).

---

## 🏗️ Arquitetura do Projeto (MVC + DAO)

O projeto evoluiu para incluir a camada de acesso a dados, garantindo separação de responsabilidades:

* **Model:** Representação dos dados (`Task`).
* **View:** Interface visual (`.fxml` e CSS).
* **Controller:** Lógica de interação com o usuário.
* **DAO:** Acesso ao Banco de Dados e criação automática de tabelas (`TaskDAO`).
* **Service:** Lógica de infraestrutura (`DockerService`) e regras de negócio (`TaskService`).

### 📂 Estrutura de Pastas
```text
src/main/java/com/projeto
│
├── controller      # Controladores da interface (Lógica de Tela)
│   ├── MainController.java
│   └── TaskDialogController.java
│
├── model           # Objetos de Domínio
│   └── Task.java
│
├── service         # Regras de Negócio
│   └── TaskService.java
│
├── DockerService.java # Automação: Gerencia o container Docker
├── TaskDAO.java       # Acesso ao Banco: CRUD + Criação de Tabela
├── Launcher.java      # Ponto de entrada (Inicializa Docker + App)
└── Main.java          # Classe Principal JavaFX
```
---

## 🔮 Roadmap (Progresso)

- [x] Integração com **Banco de Dados PostgreSQL** via **Docker**.
- [x] Implementação de **Auto-Bootstrap** (Inicialização automática do ambiente).
- [ ] Refatoração do Back-end para **Spring Boot**.
- [ ] Implementação de Login e múltiplos usuários.

---

## 🛠️ Como Rodar o Projeto

### 1. Pré-requisitos
* JDK 21 ou superior (Configurado para Java 25).
* **Docker Desktop** instalado e aberto.
* Maven.

### 2. Executando a Aplicação

1. Abra o arquivo `src/main/java/com/projeto/Launcher.java`
2. Clique em **Run**
 - **O que acontece nos bastidores?** Ao clicar em Run, o `Launcher` chama o `DockerService`, que verifica se o container `banco-tarefas` existe. Se não existir, ele cria e inicia o PostgreSQL automaticamente. Em seguida, o `TaskDAO` cria a tabela `tarefas` se ela ainda não existir

 **Opção via Terminal:**
 
 ```bash
 mvn javafx:run
 ```


## ❓ Solução de Problemas Comuns
**Erro: "Docker não encontrado" ou erro ao iniciar**

Certifique-se de que o aplicativo **Docker Desktop** está aberto no seu Windows antes de rodar o projeto.

**Erro: "Port 5432 is already allocated"**

Significa que já existe um outro PostgreSQL rodando na sua máquina. **Solução:** Pare o serviço local do Postgres ou altere a porta no arquivo `DockerService.java` para `5433:5432`.