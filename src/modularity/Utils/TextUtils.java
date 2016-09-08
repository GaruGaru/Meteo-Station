package modularity.Utils;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Tommaso Garuglieri on 12/06/2016.
 */
public class TextUtils {
    public static boolean isEmpty(CharSequence charSequence) {
        return (charSequence == null || charSequence.length() == 0);
    }

    public static String shorten(String str, int maxLen) {
        if (str == null)
            throw new RuntimeException("Can't shorten a null string");
        if (maxLen < 0)
            return "maxLen parameter can't be < 0";
        if (maxLen == 0)
            return "";
        if (maxLen - 3 < 0)
            return str.substring(0, maxLen);
        if (str.length() <= maxLen) return str;
        return str.substring(0, maxLen - 3) + "...";
    }

    public static String streamToString(InputStream stream) {
        if (stream == null) return null;
        String content = "";
        Scanner scanner = new Scanner(stream);
        while (scanner.hasNext()) {
            content += scanner.next();
        }
        return content;
    }


}
