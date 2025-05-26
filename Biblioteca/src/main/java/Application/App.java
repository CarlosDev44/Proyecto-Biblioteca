package Application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Administrador;
import model.Biblioteca;
import model.Bibliotecario;

public class App extends Application {


    @Override
    public void start(Stage stage) throws Exception {

        Biblioteca biblioteca = Biblioteca.getInstancia();
        Administrador administrador = new Administrador("Carlos", 1, "carlos@gmail.com", "contra123");
        biblioteca.agregarEmpleado(administrador);

        Bibliotecario bibliotecario = new Bibliotecario("Juan", 2, "juan@gmail.com", "contra321");
        biblioteca.agregarEmpleado(bibliotecario);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Views/Login.fxml"));

        Parent root = loader.load();
        Scene scene = new Scene(root);

        stage.setTitle("Login Biblioteca");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args)
    {
        launch();
    }
}
