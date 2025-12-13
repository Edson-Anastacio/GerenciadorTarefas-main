package com.projeto.controller;

import com.projeto.model.Task;
import com.projeto.service.TaskService;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class MainController {

    @FXML private TableView<Task> table;
    @FXML private TableColumn<Task, String> colTitulo;
    @FXML private TableColumn<Task, String> colPrioridade;
    @FXML private TableColumn<Task, Boolean> colConcluida;
    @FXML private ComboBox<String> filterCombo;
    @FXML private Button btnEditar;
    @FXML private Button btnRemover;
    @FXML private Button btnToggleConcluida;

    private final TaskService service = TaskService.getInstance();
    private FilteredList<Task> filtered;

    @FXML
    public void initialize(){
        colTitulo.setCellValueFactory(cell -> cell.getValue().tituloProperty());
        colPrioridade.setCellValueFactory(cell -> cell.getValue().prioridadeProperty());
        colConcluida.setCellValueFactory(new PropertyValueFactory<>("concluida"));

        filtered = new FilteredList<>(service.getTasks(), t -> true);
        table.setItems(filtered);

        filterCombo.getItems().addAll("Todas", "Ativas", "Concluídas");
        filterCombo.setValue("Todas");
        filterCombo.valueProperty().addListener((obs, oldV, newV) -> applyFilter(newV));

        // desabilitar botões quando não houver seleção
        btnEditar.disableProperty().bind(Bindings.isNull(table.getSelectionModel().selectedItemProperty()));
        btnRemover.disableProperty().bind(Bindings.isNull(table.getSelectionModel().selectedItemProperty()));
        btnToggleConcluida.disableProperty().bind(Bindings.isNull(table.getSelectionModel().selectedItemProperty()));

        // --- CORREÇÃO AQUI EMBAIXO ---
        // Adicionamos <Task, Boolean> dentro do TableCell
        colConcluida.setCellFactory(tc -> new TableCell<Task, Boolean>() {
            @Override
            protected void updateItem(Boolean concluida, boolean empty) {
                super.updateItem(concluida, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    CheckBox cb = new CheckBox();
                    cb.setDisable(true); // Checkbox apenas visual
                    // Verifica se não é nulo para evitar erro
                    cb.setSelected(concluida != null && concluida);
                    setGraphic(cb);
                }
            }
        });
    }

    private void applyFilter(String mode){
        switch (mode){
            case "Ativas":
                filtered.setPredicate(t -> !t.isConcluida());
                break;
            case "Concluídas":
                filtered.setPredicate(Task::isConcluida);
                break;
            default:
                filtered.setPredicate(t -> true);
        }
    }

    @FXML
    public void onAdicionar() throws IOException {
        Task novo = showTaskDialog(null);
        if (novo != null) service.add(novo);
    }

    @FXML
    public void onEditar() throws IOException {
        Task selec = table.getSelectionModel().getSelectedItem();
        if (selec == null) return;
        showTaskDialog(selec);
        table.refresh();
    }

    @FXML
    public void onRemover(){
        Task selec = table.getSelectionModel().getSelectedItem();
        if (selec == null) return;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Remover tarefa selecionada?", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> opt = alert.showAndWait();
        if (opt.isPresent() && opt.get() == ButtonType.YES){
            service.remove(selec);
        }
    }

    @FXML
    public void onToggleConcluida(){
        Task selec = table.getSelectionModel().getSelectedItem();
        if (selec == null) return;
        selec.setConcluida(!selec.isConcluida());
        table.refresh();
    }

    private Task showTaskDialog(Task toEdit) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/task-dialog.fxml"));
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(toEdit == null ? "Adicionar Tarefa" : "Editar Tarefa");
        stage.setScene(new Scene(loader.load()));
        TaskDialogController ctrl = loader.getController();
        if (toEdit != null) ctrl.setTask(toEdit);
        stage.showAndWait();
        return ctrl.getResult();
    }
}