package amtech.logic;

import amtech.registry.AnswerMessages;
import amtech.registry.ReturnCodes;
import config.LogConfigurator;

import com.sun.net.httpserver.HttpExchange;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.logging.Logger;


public abstract class Responser implements iHandler {
    private static final Logger LOGGER = LogConfigurator.getLogger();

    
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
            case HttpURLConnection.HTTP_CLIENT_TIMEOUT:  message = AnswerMessages.timeoutErrorMessage;
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
                LOGGER.warning("Exception: " + e.getMessage() + " > " + e.getCause());
                e.printStackTrace();
            }

        } catch (Exception e) {
            LOGGER.warning("Exception: " + e.getMessage() + " > " + e.getCause());
            e.printStackTrace();
        }
    }

	
	@Override
	public void headRequest(HttpExchange httpExchange) {
		
	}

	@Override
	public void putRequest(HttpExchange httpExchange) {
		
	}

	@Override
	public void deleteRequest(HttpExchange httpExchange) {
		
	}

	
	@Override
	public void connectRequest(HttpExchange httpExchange) {
		
	}

	@Override
	public void optionsRequest(HttpExchange httpExchange) {
		
	}

	@Override
	public void traceRequest(HttpExchange httpExchange) {
		
	}

	@Override
	public void patchRequest(HttpExchange httpExchange) {
		
	}
}