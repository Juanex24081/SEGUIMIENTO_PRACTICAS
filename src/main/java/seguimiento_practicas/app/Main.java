package seguimiento_practicas.app;

import seguimiento_practicas.view.login.LoginView;
import seguimiento_practicas.util.*;

public class Main {
    public static void main(String[] args) {
        new LoginView().setVisible(true);

        Conexion.getConnection();
    }
}


