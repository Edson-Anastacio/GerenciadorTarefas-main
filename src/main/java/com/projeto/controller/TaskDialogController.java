package com.projeto.controller;

import com.projeto.model.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TaskDialogController {

    @FXML private TextField tfTitulo;
    @FXML private TextArea taDescricao;
    @FXML private ComboBox<String> cbPrioridade;
    @FXML private Button btnSalvar;
    @FXML private Button btnCancelar;

    private Task taskResult = null;

    @FXML
    public void initialize(){
        cbPrioridade.getItems().addAll("Baixa", "Média", "Alta");
        cbPrioridade.setValue("Média");
    }

    public void setTask(Task t){
        // Preenche os campos com os dados da tarefa existente
        tfTitulo.setText(t.getTitulo());
        taDescricao.setText(t.getDescricao());
        cbPrioridade.setValue(t.getPrioridade());
        
        // Mantém a referência para edição
        taskResult = t;
    }

    public Task getResult(){
        return taskResult;
    }

    @FXML
    public void onSalvar(){
        String titulo = tfTitulo.getText().trim();
        
        // Validação simples
        if (titulo.isEmpty()){
            Alert a = new Alert(Alert.AlertType.WARNING, "O título é obrigatório.");
            a.showAndWait();
            return;
        }

        if (taskResult == null){
            // --- MODO CRIAR NOVA ---
            // Certifique-se que sua classe Task tem este construtor!
            taskResult = new Task(titulo, taDescricao.getText(), cbPrioridade.getValue());
        } else {
            // --- MODO EDITAR ---
            taskResult.setTitulo(titulo);
            taskResult.setDescricao(taDescricao.getText());
            taskResult.setPrioridade(cbPrioridade.getValue());
        }
        
        close();
    }

    @FXML
    public void onCancelar(){
        // Se cancelou, não queremos retornar nada se for uma nova tarefa
        // Se for edição, as alterações não foram salvas (setters não foram chamados)
        if (taskResult == null) {
            // Se era novo, garante que retorna null
            taskResult = null; 
        }
        // Se era edição, taskResult continua sendo o objeto original (sem alterações)
        // ou podemos forçar null se quisermos que o MainController ignore.
        // Mas a lógica mais segura para cancelar é simplesmente fechar.
        
        close();
    }

    private void close(){
        // Fecha a janela pegando a referência pelo botão
        Stage s = (Stage) btnCancelar.getScene().getWindow();
        s.close();
    }
}