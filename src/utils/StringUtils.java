package utils;

public class StringUtils {
	public static final String apenasNumeros(String str){
		String result = "";
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {
                result += str.charAt(i);
            }
        }

        return result;
	}
}
