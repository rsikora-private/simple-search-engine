package pl.robertsikora.simplesearch.engine;

public class Logger {

    public void error(final String message, final Exception ex) {
        System.err.printf("message: %s, error: %s%n", message, ex);
    }

    public void info(final String info) {
        System.out.println(info);
    }

}
