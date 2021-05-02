package amtech.registry;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("serial")
public class MediaTypes {
	public static List<String> mediaTypesArray;
	
	static {
		mediaTypesArray = new ArrayList<String>() {
			{
				add("png");
				add("jpg");
				add("jpeg");
			}
		};
	}
}
