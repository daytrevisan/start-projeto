package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    public static final String DRIVER = "com.mysql.jdbc.Driver";
    public static final String URL = "jdbc:mysql://localhost:3306/todoapp";
    public static final String USER = "root";
    public static final String PASS = "";

    /*
    Classe de conexão com banco de dados
    - 'DRIVER': quem faz a ponte aplicação com banco de dados;
    - 'URL': caminho de conexão com o banco de dados;
    - Onde está 'localhost:3306' (servidor:porta), poderia ser o IP de uma máquina caso aplicação e bd estivessem
    em locais diferentes (localhost = servidor local);
    - O n. da porta é a que aparece relacionado ao MySQL (no Xampp);
    - "todoapp" é o nome do banco de dados gerado no MySQL;
    - Por configuração defaut, USER é 'root' e PASS é vazio;
     */

    // Abertura conexão com banco de dados
    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception ex) {
            throw new RuntimeException("Erro na conexão com o banco de dados", ex);
        }
    }

    /*
     - Try/catch são métodos para tratamento de erros (exceções), como:
     - Driver não instalado, caminho até o bd não está correto, porta ou usuário incorretos, etc;
     - Implementando o try/catch, caso aconteça algum erro, o algoritmo saiba o que fazer;
     - Em 'try' se coloca todo o código passível de erro e em 'catch' se coloca o tratamento (é executado logo
                           após o erro acontecer, não executa o 'return' do 'try');
    */

    // Fechamento conexão com banco de dados
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", ex);
        }
    }
}
