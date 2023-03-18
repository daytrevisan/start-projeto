package com.project;

import java.sql.Connection;
import java.sql.Date;

public class Main {
    public static void main(String[] args) {

        ProjectController projectController =  new ProjectController();

        // Criação do projeto, set das informações e salvamento do projeto
        Project project = new Project();
        project.setName("Projeto teste");
        project.setDescription("description");
        projectController.save(project);

//        // Update do nome do projeto
//        project.setName("Novo nome do projeto");
//        projectController.update(project);
//
//        // Criação da lista de projetos + listagem de todos os projetos e retorno do número de projetos
//        List<Project> projects = projectController.getAll();
//        System.out.println("Total de projetos = " + projects.size());
//
//        projectController.removeById();
//
//        TaskController taskController = new TaskController();
//
//        Task task = new Task();
//        task.setIdProject(2);
//        task.setName("Criar as telas da aplicação");
//        task.setDescription("Devem ser criadas telas para os cadastros");
//        task.setNotes("Sem notas");
//        task.setIsCompleted(false);
//        task.setDeadline(new Date());
//
//        taskController.save(task);
//
//        task.setName("Alterar telas da aplicação");
//        taskController.update(task);
//        List<Task> tasks = taskController.getAll(12);
//        System.out.println("Total de tarefas = " + tasks.size());

//      Connection c = ConnectionFactory.getConnection();
//      ConnectionFactory.closeConnection(c);

    }
}