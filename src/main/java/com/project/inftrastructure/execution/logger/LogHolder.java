package com.project.inftrastructure.execution.logger;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class LogHolder {
    PrintStream requestVar;
    PrintStream responseVar;
    private ByteArrayOutputStream requestStream;
    private ByteArrayOutputStream responseStream;

    public LogHolder() {
        this.requestStream = new ByteArrayOutputStream();
        this.responseStream = new ByteArrayOutputStream();
        this.requestVar = new PrintStream(requestStream, true);
        this.responseVar = new PrintStream(responseStream, true);
    }

    public PrintStream getRequestVar() {
        return requestVar;
    }

    public PrintStream getResponseVar() {
        return responseVar;
    }

    public ByteArrayOutputStream getRequestStream() {
        return requestStream;
    }

    public ByteArrayOutputStream getResponseStream() {
        return responseStream;
    }
}
