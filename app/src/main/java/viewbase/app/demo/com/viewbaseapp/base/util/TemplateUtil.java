package viewbase.app.demo.com.viewbaseapp.base.util;

import com.joanzapata.utils.Strings;

/**
 * Created by duongmatheo on 5/16/17.
 */

public class TemplateUtil {
    private TemplateUtil() {

    }

    public static Strings.Builder sql(String sql) {
        return Strings.format(sql);
    }

    public static Phrase string(CharSequence charSequence) {
        return Phrase.from(charSequence);
    }

    /**
     * create Spannable from text and style span
     *
     * @param text   text need formating
     * @param styles StyleSpan, ForegroundColorSpan, UnderlineSpan, BackgroundColorSpan, StyleSpan, RelativeSizeSpan, ImageSpan, TextAppearanceSpan, AlignmentSpan.Standard, CustomBackgroundSpan, SubscriptSpan, SuperscriptSpan, QuoteSpan, StrikethroughSpan, TypefaceSpan, URLSpan, CustomTypefaceSpan, CustomAlignmentSpan,
     * @return
     */
    public static CharSequence createSpannable(String text, Object... styles) {
        return new Spanny(text, styles);
    }
}
