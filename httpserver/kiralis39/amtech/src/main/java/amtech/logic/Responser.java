package amtech.logic;

import amtech.registry.AnswerMessages;
import amtech.registry.ReturnCodes;
import amtech.tools.LogConfigurator;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Logger;


public class Responser {
    private static final Logger LOGGER = LogConfigurator.getLogger(Responser.class);

    protected void writeResponse(HttpExchange httpExchange, int sendCode) {
        String message = "";

        switch (sendCode) {
            case ReturnCodes.BAD_REQUEST: message = AnswerMessages.formErrorMessage;
                break;
            case ReturnCodes.NOT_ACCEPTABLE: message = AnswerMessages.notEnougtDataMessage;
                break;
            case ReturnCodes.UNDEFINED_PROBLEM: message = AnswerMessages.undefinedErrorMessage;
                break;
            case ReturnCodes.ERR_404: message = AnswerMessages.undefinedErrorMessage;
                break;
            case ReturnCodes.NO_CONTENT: message = AnswerMessages.notExistsErrorMessage;
                break;
            case ReturnCodes.HASDELETED_PROBLEM: message = AnswerMessages.resourceDeletedErrorMessage;
                break;
            case ReturnCodes.PAGE_LOAD_PROBLEM: message = AnswerMessages.pageLoadErrorMessage;
                break;
            default: message = AnswerMessages.undefinedErrorMessage;
        }

        writeResponse(httpExchange, message.getBytes(), sendCode);
    }

    protected void writeResponse(HttpExchange httpExchange, byte[] bytes) {
        writeResponse(httpExchange, bytes, ReturnCodes.OK);
    }

    protected void writeResponse(HttpExchange httpExchange, byte[] writeData, int sendCode) {
        int resultCode = sendCode;

        if (writeData.length < 0) {
            LOGGER.severe("RegFormHandler(): writeData lenght less 0. Its ok?");
            resultCode = ReturnCodes.ERR_404;
        }

        try {
            httpExchange.sendResponseHeaders(resultCode, writeData.length);

            LOGGER.info("Writing a response to page.html with code " + resultCode + "...");
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