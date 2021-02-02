package Utilities;


public class StrUtilities {


    public static final char LF = '\n';

    public static final char CR = '\r';


    public static String chomp(String str) {
        if (isEmpty(str)) {
            return str;
        }

        if (str.length() == 1) {
            char ch = str.charAt(0);
            if (ch == CR || ch == LF) {
                return "";
            }
            return str;
        }

        int lastIdx = str.length() - 1;
        char last = str.charAt(lastIdx);

        if (last == LF) {
            if (str.charAt(lastIdx - 1) == CR) {
                lastIdx--;
            }
        } else if (last != CR) {
            lastIdx++;
        }
        return str.substring(0, lastIdx);
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }


    public static char[][] StringTo2D_CharArray(String map) {

        String[] components = map.split("\n");
        char[][] chars = new char[components.length + 1][];
        for (int i = 0; i < components.length; i++) {
            String component = components[i];
            chars[i] = component.toCharArray();
        }
        chars[components.length] = new char[]{'\0'};

        return chars;
    }
}
