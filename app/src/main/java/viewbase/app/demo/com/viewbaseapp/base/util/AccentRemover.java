package viewbase.app.demo.com.viewbaseapp.base.util;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AccentRemover {
    private static final Map<Character, Character> ACCENT_CHAR_MAP = new HashMap<>();
    //́ 769   ̀ 768   ̉ 777   ̣ 803 ̃ 771
    private static char[] SPECIAL_CHARACTERS_NEED_REMOVE = {Character.toChars(769)[0], Character.toChars(768)[0], Character.toChars(777)[0], Character.toChars(771)[0], Character.toChars(803)[0]};
    private static char[] SPECIAL_CHARACTERS = {'À', 'Á', 'Â', 'Ã', 'È', 'É', 'Ê', 'Ì', 'Í', 'Ò', 'Ó', 'Ô', 'Õ', 'Ù', 'Ú', 'Ý', 'à', 'á', 'â', 'ã', 'è', 'é', 'ê', 'ì', 'í', 'ò', 'ó', 'ô', 'õ',
            'ù', 'ú', 'ý', 'Ă', 'ă', 'Đ', 'đ', 'Ĩ', 'ĩ', 'Ũ', 'ũ', 'Ơ', 'ơ', 'Ư', 'ư', 'Ạ', 'ạ', 'Ả', 'ả', 'Ấ', 'ấ', 'Ầ', 'ầ', 'Ẩ', 'ẩ', 'Ẫ', 'ẫ', 'Ậ', 'ậ', 'Ắ', 'ắ', 'Ằ', 'ằ', 'Ẳ', 'ẳ', 'Ẵ', 'ẵ', 'Ặ', 'ặ',
            'Ẹ', 'ẹ', 'Ẻ', 'ẻ', 'Ẽ', 'ẽ', 'Ế', 'ế', 'Ề', 'ề', 'Ể', 'ể', 'Ễ', 'ễ', 'Ệ', 'ệ', 'Ỉ', 'ỉ', 'Ị', 'ị', 'Ọ', 'ọ', 'Ỏ', 'ỏ', 'Ố', 'ố', 'Ồ', 'ồ', 'Ổ', 'ổ', 'Ỗ', 'ỗ', 'Ộ', 'ộ', 'Ớ', 'ớ', 'Ờ', 'ờ', 'Ở',
            'ở', 'Ỡ', 'ỡ', 'Ợ', 'ợ', 'Ụ', 'ụ', 'Ủ', 'ủ', 'Ứ', 'ứ', 'Ừ', 'ừ', 'Ử', 'ử', 'Ữ', 'ữ', 'Ự', 'ự', 'ỳ', 'Ỳ', 'ỷ', 'Ỷ', 'ỹ', 'Ỹ', 'ỵ', 'Ỵ'};
    private static char[] REPLACEMENTS = {'A', 'A', 'A', 'A', 'E', 'E', 'E', 'I', 'I', 'O', 'O', 'O', 'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o',
            'u', 'u', 'y', 'A', 'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a',
            'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O',
            'o', 'O', 'o', 'O', 'o', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u', 'y', 'Y', 'y', 'Y', 'y', 'Y', 'y', 'Y'};

    private static void buildMap() {
        for (int index = 0; index < SPECIAL_CHARACTERS.length; index++) {
            ACCENT_CHAR_MAP.put(SPECIAL_CHARACTERS[index], REPLACEMENTS[index]);
        }
    }

    public static char removeAccent(char ch) {
        int index = Arrays.binarySearch(AccentRemover.SPECIAL_CHARACTERS, ch);
        if (index >= 0) {
            ch = AccentRemover.REPLACEMENTS[index];
        }
        return ch;
    }

    public static String removeAccent(String textNeedRemoveAccent) {
        String result = "";
        if (!textNeedRemoveAccent.isEmpty()) {
            StringBuilder sb = new StringBuilder(textNeedRemoveAccent);
            for (int i = 0; i < sb.length(); i++) {
                sb.setCharAt(i, AccentRemover.removeAccent(sb.charAt(i)));
            }
            result = sb.toString();

            //remove sign character
            for (char chNeedRemove : SPECIAL_CHARACTERS_NEED_REMOVE) {
                result = result.replaceAll(String.valueOf(chNeedRemove), "");
            }
        }
        return result;
    }

    public static String removeAccentAndSpace(String st) {
        if (ACCENT_CHAR_MAP.isEmpty()) {
            buildMap();
        }

        StringBuilder sb = new StringBuilder(st);
        for (int i = 0; i < sb.length(); i++) {
            Character ch = ACCENT_CHAR_MAP.get(Character.toLowerCase(sb.charAt(i)));
            sb.setCharAt(i, (ch == null ? Character.toLowerCase(sb.charAt(i)) : ch));
            if (sb.charAt(i) == ' ') {
                sb.deleteCharAt(i);
                i--;
            }
        }

        String result = sb.toString();

        //remove sign character
        for (char chNeedRemove : SPECIAL_CHARACTERS_NEED_REMOVE) {
            result = result.replaceAll(String.valueOf(chNeedRemove), "");
        }
        return result;
    }
}
