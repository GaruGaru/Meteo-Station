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

    public static String shorten(String str, int len) {
        if (str.length() <= len) return str;
        return str.substring(0, len - 3) + "...";
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
