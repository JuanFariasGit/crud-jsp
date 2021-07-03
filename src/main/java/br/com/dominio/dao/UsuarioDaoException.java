package br.com.dominio.dao;

public class UsuarioDaoException extends Exception {

    public UsuarioDaoException() {
    }

    public UsuarioDaoException(String arg) {
        super(arg);
    }

    public UsuarioDaoException(Throwable arg) {
        super(arg);
    }

    public UsuarioDaoException(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }
}
