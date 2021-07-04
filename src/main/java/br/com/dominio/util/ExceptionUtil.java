package br.com.dominio.util;

public class ExceptionUtil extends Exception {

    public ExceptionUtil() {
    }

    public ExceptionUtil(String arg) {
        super(arg);
    }

    public ExceptionUtil(Throwable arg) {
        super(arg);
    }

    public ExceptionUtil(String arg1, Throwable arg2) {
        super(arg1, arg2);
    }
}
