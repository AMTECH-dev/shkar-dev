package amtech.registry;

import java.util.HashMap;

public class TemporaryData {
	public static HashMap<String, String> userData = new HashMap<>();

	public static final String CONTENT_TYPE = "Content-Type";
	public static final String POST = "post";
	public static final String GET = "get";	
	
	public static final String TEXT_HTML = "text/html; charset=UTF-8";
	public static final String IMAGE_PNG = "image/png; charset=UTF-8";

	public static String LOG_PATH = null;
	
	public static final String PNG = "png";

    public static final int OK = 200;
	public static final int ERR_300 = 300;
	public static final int ERR_404 = 404;
}
