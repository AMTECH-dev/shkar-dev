package amtech.logic;

import com.sun.net.httpserver.HttpExchange;

public interface iHandler {
	public void postRequest(HttpExchange httpExchange); // используется для отправки сущностей к определённому ресурсу. Часто вызывает изменение состояния или какие-то побочные эффекты на сервере.
	public void getRequest(HttpExchange httpExchange); // запрашивает представление ресурса. Запросы с использованием этого метода могут только извлекать данные.
	
	abstract void headRequest(HttpExchange httpExchange); // запрашивает ресурс так же, как и метод GET, но без тела ответа.
	abstract void putRequest(HttpExchange httpExchange); // заменяет все текущие представления ресурса данными запроса.
	abstract void deleteRequest(HttpExchange httpExchange); // удаляет указанный ресурс.
	
	abstract void connectRequest(HttpExchange httpExchange); // устанавливает "туннель" к серверу, определённому по ресурсу.
	abstract void optionsRequest(HttpExchange httpExchange); // используется для описания параметров соединения с ресурсом.
	abstract void traceRequest(HttpExchange httpExchange); // выполняет вызов возвращаемого тестового сообщения с ресурса.
	abstract void patchRequest(HttpExchange httpExchange); // используется для частичного изменения ресурса.
}