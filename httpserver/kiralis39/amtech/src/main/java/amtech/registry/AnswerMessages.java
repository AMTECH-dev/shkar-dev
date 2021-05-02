package amtech.registry;

public class AnswerMessages {
	private static String PREFIX = "<html><head><meta charset=\"utf-8\"></head>";
	
    public static String undefinedErrorMessage = PREFIX + "<h2>Произошла неопределенная ошибка.</h2><hr>Попробуйте перезагрузить страницу.";
    public static String notEnougtDataMessage = PREFIX + "<h2>Похожe, были заполнены не все необходимые поля!</h2><hr>Вернитесь назад и повторите, пожалуйста.";
    public static String formErrorMessage = PREFIX + "<h2>На форме произошла ошибка.</h2><hr>Попробуйте повторить позже, пожалуйста.";
    public static String pageLoadErrorMessage = PREFIX + "<h2>Произошла ошибка при загрузке страницы.</h2><hr>Попробуйте позже, пожалуйста.";
    public static String notExistsErrorMessage = PREFIX + "<h2>Запрашиваемый ресурс не был найден.</h2><hr>Попробуйте позже, пожалуйста.";
    public static String resourceDeletedErrorMessage = PREFIX + "<h2>Произошла ошибка при загрузке ресурса.</h2><hr>Возможно, ресурс был удалён.";
	public static String timeoutErrorMessage = PREFIX + "<h2>Соединение разорвано, предположительно по таймауту.</h2><hr>Проверьте соединение или обратитесь к администратору.";
}
