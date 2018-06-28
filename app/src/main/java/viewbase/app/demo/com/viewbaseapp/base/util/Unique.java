package viewbase.app.demo.com.viewbaseapp.base.util;

public class Unique {
    private Unique() {
    }

    private static class NumberGenerator {
        private int current = 0;

        private NumberGenerator(int current) {
            this.current = current;
        }

        private int next() {
            return current++;
        }
    }

    public static class ViewHolderType {
        private static NumberGenerator numberGenerator = new NumberGenerator(10000);

        private ViewHolderType() {
        }

        public static int next() {
            return numberGenerator.next();
        }
    }

    public static class RequestCode {
        private static NumberGenerator numberGenerator = new NumberGenerator(1001);

        private RequestCode() {
        }

        public static int next() {
            return numberGenerator.next();
        }
    }
}
