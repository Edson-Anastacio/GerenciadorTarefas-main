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
* **Gerenciador de Dependências:** Apache Maven
* **IDE:** Visual Studio Code

---

## ⚙️ Funcionalidades

O sistema implementa um CRUD completo conectado a um banco de dados real:

* ✅ **Persistência Real:** As tarefas são salvas no PostgreSQL e não são perdidas ao fechar o programa.
* ➕ **Adicionar Tarefa:** Criação de novas tarefas com Título, Descrição e Prioridade.
* ✏️ **Editar Tarefa:** Alteração de dados com atualização imediata no banco de dados.
* 🗑️ **Remover Tarefa:** Exclusão definitiva do registro no banco.
* ✔️ **Status:** Checkbox interativo que salva o estado (pendente/concluída) no banco.
* 🔍 **Filtros Dinâmicos:** Filtragem visual (Todas / Ativas / Concluídas).

---

## 🏗️ Arquitetura do Projeto (MVC + DAO)

O projeto evoluiu para incluir a camada de acesso a dados, garantindo separação de responsabilidades:

* **Model:** Representação dos dados (`Task`).
* **View:** Interface visual (`.fxml`).
* **Controller:** Lógica de interação com o usuário.
* **DAO (Data Access Object):** Classe responsável por executar os comandos SQL e isolar o banco da aplicação (`TaskDAO`).
* **Service:** Regras de negócio e ponte entre Controller e DAO.

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
├── TaskDAO.java    # Acesso ao Banco de Dados (SQL)
├── Launcher.java   # Ponto de entrada
├── Main.java       # Classe Principal
└── SetupBanco.java # Utilitário para criar a tabela no banco
```
---

## 🔮 Roadmap (Progresso)

- [x] Integração com **Banco de Dados PostgreSQL** via **Docker**.
- [ ] Refatoração do Back-end para **Spring Boot**.
- [ ] Implementação de Login e múltiplos usuários.

---

## 🛠️ Como Rodar o Projeto

### 1. Pré-requisitos
* JDK 21 ou superior (Configurado para Java 25).
* **Docker Desktop** instalado e em execução.
* Maven.

### 2. Configurando o Banco de Dados (Docker)
Antes de executar a aplicação pela primeira vez, é necessário criar o ambiente do banco de dados:
1. **Subir o Container:** Abra o terminal e rode:

```bash
docker run --name banco-tarefas -e POSTGRES_PASSWORD=minhasenha -p 5432:5432 -d postgres
```
2. **Criar a Tabela (Importante!):**
* No VS Code, abra o arquivo `src/main/java/com/projeto/SetupBanco.java`.
* Clique em **Run** (Executar)
* Aguarde a mensagem: "Tabela 'tarefas' criada com sucesso no Docker!"

### 3. Executando a Aplicação
**Opção 1 (Via VS Code - Recomendada):**
1. Abra o arquivo `src/main/java/com/projeto/Launcher.java`.
2. Clique em **Run**

**Opção 2 (Via Terminal):**
```bash
mvn javafx:run
```
---
## ❓ Solução de Problemas Comuns
**Erro "Port 5432 is already allocated"**

Significa que já existe um PostgreSQL rodando na sua máquina ocupando a porta padrão. Solução: Pare o serviço local do Postgres ou mude a porta do Docker para `5433:5432` (lembre-se de alterar a URL de conexão no `TaskDAO.java`).

**Erro "No suitable driver found"**

Significa que o Maven não baixou a dependência do PostgreSQL. **Solução:** Clique com botão direito no `pom.xml` > **Reload Project.**