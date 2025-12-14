package com.projeto;

import com.projeto.model.Task;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public TaskDAO() {
        criarTabelaSeNaoExistir();
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "minhasenha");
    }

    private void criarTabelaSeNaoExistir() {
        String sql = "CREATE TABLE IF NOT EXISTS tarefas (" +
                     "id SERIAL PRIMARY KEY," +
                     "titulo VARCHAR(100) NOT NULL," +
                     "descricao TEXT," +
                     "prioridade VARCHAR(20)," +
                     "concluida BOOLEAN DEFAULT FALSE" +
                     ");";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    // 1. SALVAR
    public void adicionar(Task task) {
        String sql = "INSERT INTO tarefas (titulo, descricao, prioridade, concluida) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, task.getTitulo());
            stmt.setString(2, task.getDescricao());
            stmt.setString(3, task.getPrioridade());
            stmt.setBoolean(4, task.isConcluida());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) task.setId(rs.getInt(1));
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 2. LISTAR
    public List<Task> listarTodas() {
        List<Task> tarefas = new ArrayList<>();
        String sql = "SELECT * FROM tarefas ORDER BY id ASC";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                tarefas.add(new Task(
                    rs.getInt("id"), rs.getString("titulo"), rs.getString("descricao"),
                    rs.getString("prioridade"), rs.getBoolean("concluida")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return tarefas;
    }

    // 3. ATUALIZAR
    public void atualizar(Task task) {
        String sql = "UPDATE tarefas SET titulo=?, descricao=?, prioridade=?, concluida=? WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, task.getTitulo());
            stmt.setString(2, task.getDescricao());
            stmt.setString(3, task.getPrioridade());
            stmt.setBoolean(4, task.isConcluida());
            stmt.setInt(5, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    // 4. DELETAR
    public void deletar(Task task) {
        String sql = "DELETE FROM tarefas WHERE id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, task.getId());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}