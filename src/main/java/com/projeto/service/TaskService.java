package com.projeto.service;

import com.projeto.model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskService {

    private static TaskService instance;
    private final ObservableList<Task> tasks = FXCollections.observableArrayList();

    private TaskService() {
        // exemplo inicial (pode remover)
        tasks.add(new Task("Estudar Lógica", "Estudar os capítulos 1 a 3", "Média"));
        tasks.add(new Task("Fazer compras", "Comprar leite e pão", "Baixa"));
        tasks.add(new Task("Projeto LP2", "Estruturar repositório e README", "Alta"));
    }

    public static TaskService getInstance(){
        if (instance == null) instance = new TaskService();
        return instance;
    }

    public ObservableList<Task> getTasks(){ return tasks; }

    public void add(Task t){ tasks.add(t); }

    public void remove(Task t){ tasks.remove(t); }

    // substitui: editar em memória - basta alterar propriedades do Task já presente
}
