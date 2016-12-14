package br.senac.rn.model;

import java.io.Serializable;

public class Autor implements Serializable {

    private String login;
    private String email;
    private String fone;

    public Autor() {}

    public Autor(String login, String email, String fone) {
        this.login = login;
        this.email = email;
        this.fone = fone;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getFone() {
        return fone;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }


}
