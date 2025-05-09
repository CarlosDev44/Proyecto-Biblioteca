package model;

import java.util.ArrayList;
import java.util.List;

public class Login {

    private List<Empleado> listEmpleadosLogin;

    public Login()
    {
        this.listEmpleadosLogin = new ArrayList<>();
    }

    public List<Empleado> getListEmpleadosLogin() {
        return listEmpleadosLogin;
    }

    public void setListEmpleadosLogin(List<Empleado> listEmpleadosLogin) {
        this.listEmpleadosLogin = listEmpleadosLogin;
    }

//public bool autenticar(){}
}
