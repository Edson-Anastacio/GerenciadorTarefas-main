package com.projeto;

public class Launcher {
    public static void main(String[] args) {
        DockerService.iniciarBanco();
        
        Main.main(args);
    }
}