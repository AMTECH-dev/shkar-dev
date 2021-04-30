package amtech.logic;

import amtech.registry.AnswerMessages;
import amtech.registry.ReturnCodes;
import amtech.tools.LogConfigurator;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.logging.Logger;


public class Responser {
    private static final Logger LOGGER = LogConfigurator.getLogger(Responser.class);

    protected void writeResponse(HttpExchange httpExchange, int sendCode) {
        String message = "";

        switch (sendCode) {
            case HttpURLConnection.HTTP_BAD_REQUEST: message = AnswerMessages.formErrorMessage;
                break;
            case HttpURLConnection.HTTP_NOT_ACCEPTABLE: message = AnswerMessages.notEnougtDataMessage;
                break;
            case ReturnCodes.UNDEFINED_PROBLEM: message = AnswerMessages.undefinedErrorMessage;
                break;
            case HttpURLConnection.HTTP_NOT_FOUND:
            case HttpURLConnection.HTTP_UNAVAILABLE: message = AnswerMessages.pageLoadErrorMessage;
                break;
            case HttpURLConnection.HTTP_NO_CONTENT: message = AnswerMessages.notExistsErrorMessage;
                break;
            case HttpURLConnection.HTTP_GONE: message = AnswerMessages.resourceDeletedErrorMessage;
                break;

            default: message = AnswerMessages.undefinedErrorMessage;
        }

        writeResponse(httpExchange, message.getBytes(), sendCode);
    }

    protected void writeResponse(HttpExchange httpExchange, byte[] bytes) {
        writeResponse(httpExchange, bytes, HttpURLConnection.HTTP_OK);
    }

    protected void writeResponse(HttpExchange httpExchange, byte[] writeData, int sendCode) {
        int resultCode = sendCode;

        if (writeData.length < 0) {
            LOGGER.severe("writeData lenght less 0. Its ok?");
            resultCode = HttpURLConnection.HTTP_NOT_FOUND;
        }

        try {
            httpExchange.sendResponseHeaders(resultCode, writeData.length);

            LOGGER.finest("Writing a response to with code " + resultCode + "...");
            try (OutputStream os = httpExchange.getResponseBody()) {
                os.write(writeData);
            } catch (Exception e) {
                LOGGER.warning("Exception: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (IOException e) {
            LOGGER.warning("Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}