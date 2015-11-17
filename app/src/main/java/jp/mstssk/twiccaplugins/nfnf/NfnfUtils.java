package jp.mstssk.twiccaplugins.nfnf;

import android.content.Context;


public class NfnfUtils {

    public static String buildText(Context context, String prefix, String userInput, String suffix) {

        StringBuilder sb = new StringBuilder();

        if (!isEmpty(prefix)) {
            sb.append(prefix);
        }

        int inputLength = getLength(userInput);
        if (inputLength != 0 && inputLength <= 2) {
            sb.append(context.getString(R.string.nf));
        } else {
            sb.append(context.getString(R.string.nfnf));
        }

        if (!isEmpty(userInput)) {
            String lastChar = getLastChar(userInput);
            if (lastChar != null) {
                sb.append(lastChar);
            }
            sb.append(wrapWithParentheses(userInput));
        }

        if (!isEmpty(suffix)) {
            sb.append(suffix);
        }

        return sb.toString();

    }

    public static String getLastChar(String text) {

        String str = text.trim().replaceAll("　*$", "");

        if (str.endsWith("?") || str.endsWith("？")) {
            return "？";
        } else if (str.endsWith("!") || str.endsWith("！")) {
            return "！";
        } else if (str.endsWith("…") || str.endsWith("...")) {
            return "…";
        }

        return null;
    }

    public static String wrapWithParentheses(String str) {

        if (str.length() == 0) {
            return "";
        }
        return "（" + str + "）";
    }

    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static int getLength(String str) {
        return str != null ? str.length() : 0;
    }
}
