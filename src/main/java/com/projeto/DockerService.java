package com.projeto;

import java.io.IOException;

public class DockerService {

    public static void iniciarBanco() {
        System.out.println("🐳 Verificando Docker...");

        try {
            ProcessBuilder startProcess = new ProcessBuilder("docker", "start", "banco-tarefas");
            Process start = startProcess.start();
            int startCode = start.waitFor();

            if (startCode == 0) {
                System.out.println("✅ Docker: Container 'banco-tarefas' iniciado.");
            } else {
                System.out.println("⚠️ Container não encontrado. Criando um novo...");
                
                ProcessBuilder runProcess = new ProcessBuilder(
                    "docker", "run", 
                    "--name", "banco-tarefas", 
                    "-e", "POSTGRES_PASSWORD=minhasenha", 
                    "-p", "5432:5432", 
                    "-d", "postgres"
                );
                Process run = runProcess.start();
                int runCode = run.waitFor();
                
                if (runCode == 0) {
                    System.out.println("✅ Docker: Container criado e rodando!");
                    System.out.println("⏳ Aguardando banco inicializar...");
                    Thread.sleep(5000); 
                } else {
                    System.err.println("❌ Erro ao subir Docker. Verifique se o Docker Desktop está aberto.");
                }
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}