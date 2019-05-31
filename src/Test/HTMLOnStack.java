package Test;

/**
 * @author focusxyhoo
 * @date 2019-05-17 19:14
 */
public class HTMLOnStack {

    public static class Tag {
        String name;
        boolean type;

        public Tag() {
            this("", false);
        }

        public Tag(String name, boolean type) {
            this.name = name;
            this.type = type;
        }

        public boolean isOpening() {
            return type;
        }

        public String getName() {
            return name;
        }
    }

}
