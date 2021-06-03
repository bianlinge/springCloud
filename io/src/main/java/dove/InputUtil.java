package dove;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Slf4j
public class InputUtil {
    private static final BufferedReader KEYBOARD_INPUT =
            new BufferedReader(new InputStreamReader(System.in));

    public InputUtil() {
    }

    public static String getString(String prompt) {
        boolean flag = true;
        String str = null;
        while (flag) {
            log.info(prompt);
            try {
                str = KEYBOARD_INPUT.readLine().trim();
                if (null == str || "".equals(str)) {
                    log.error("数据输入错误 ，该内容不允许为空：");
                } else {
                    flag = false;
                }
            } catch (IOException e) {
                log.error("数据输入错误 ，该内容不允许为空：");
                e.printStackTrace();
            }
        }
        return str;
    }

}
