package src.controller;

import src.model.Task;
import util.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskController {

    public void save (Task task) {

        String sql = "INSERT INTO tasks ("
                + "idProject, "
                + "name, "
                + "description, "
                + "completed,"
                + "notes,"
                + "deadline,"
                + "createdAt,"
                + "updatedAt)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, task.getIdProject());
            statement.setString(2, task.getName());
            statement.setString(3, task.getDescription());
            statement.setBoolean(4, task.isCompleted());
            statement.setString(5, task.getNotes());
            statement.setDate(6, new Date(task.getDeadline().getTime()));
            statement.setDate(7, new Date(task.getCreatedAt().getTime()));
            statement.setDate(8, new Date(task.getUpdatedAt().getTime()));
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao salvar a tarefa " + ex.getMessage(), ex);

        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }
    }

    public void update (Task task) {

        String sql = "UPDATE tasks SET "
                + "idProject = ?, "
                + "name = ?, "
                + "description = ?, "
                + "notes = ?, "
                + "completed = ?, "
                + "deadline = ?, "
                + "createdAt = ?, "
                + "updatedAt = ?, "
                + "WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Estabelecendo a conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            // Preparando a query
            statement = connection.prepareStatement(sql);

            // Setando os valores do statement
            statement.setInt(1, task.getId());
            statement.setInt(2, task.getIdProject());
            statement.setString(3, task.getName());
            statement.setString(4, task.getDescription());
            statement.setString(5, task.getNotes());
            statement.setBoolean(6, task.isCompleted());
            statement.setDate(7, new Date(task.getDeadline().getTime()));
            statement.setDate(8, new Date(task.getCreatedAt().getTime()));
            statement.setDate(9, new Date(task.getUpdatedAt().getTime()));

            // Executando a query
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa " + ex.getMessage());
        }

    }

    public void removeById (int taskId) {

        String sql = "DELETE FROM tasks WHERE id = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Criação da conexão com o banco de dados
            connection = ConnectionFactory.getConnection();

            // Preparando a query
            statement = connection.prepareStatement(sql);

            // Setando os valores
            statement.setInt(1, taskId);

            // Executando a query
            statement.execute();

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao deletar a tarefa" + ex.getMessage());

        } finally {
            ConnectionFactory.closeConnection(connection, statement);
        }

    }

    // Único método com retorno
    public List<Task> getAll (int idProject) {

        String sql = "SELECT * FROM tasks WHERE idProject = ?";

        Connection connection = null;
        PreparedStatement statement = null;

        // Na query de select é necessário incluir resultSet também
        ResultSet resultSet = null;

        // Lista de tarefas que será devolvida quando a chamada  do método acontecer
        List<Task> tasks = new ArrayList<Task>();

        try {
            // Criação da conexão
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(sql);

            // Valor retornado pela execução da query
            statement.setInt(1, idProject);

            // Enquanto houver valores a serem percorridos
            resultSet = statement.executeQuery();

            while(resultSet.next()) {

                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setIdProject(resultSet.getInt("idProject"));
                task.setName(resultSet.getString("name"));
                task.setDescription(resultSet.getString("description"));
                task.setNotes(resultSet.getString("notes"));
                task.setIsCompleted(resultSet.getBoolean("completed"));
                task.setDeadline(resultSet.getDate("deadline"));
                task.setCreatedAt(resultSet.getDate("createdAt"));
                task.setUpdatedAt(resultSet.getDate("updatedAt"));

                tasks.add(task);
            }

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao inserir a tarefa " + ex.getMessage(), ex);

        } finally {
            ConnectionFactory.closeConnection(connection, statement, resultSet);
        }
        // Lista de tarefas que foi criada e carregada do banco de dados
        return tasks;
    }

}
