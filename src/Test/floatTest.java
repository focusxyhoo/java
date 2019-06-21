package Test;

import java.math.BigDecimal;

/**
 * created with IntelliJ IDEA
 * author      : focusxyhoo
 * date        : 2019-06-17
 * time        : 21:00
 * description :
 */
public class floatTest {
    public static void main(String[] args) {
        float a = 1.0f - 0.9f;
        float b = 0.9f - 0.8f;
        if (a == b) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

        Float af = Float.valueOf(1.0f-0.9f);
        Float bf = Float.valueOf(0.9f-0.8f);
        if (af.equals(bf)) {
            System.out.println("True");
        } else {
            System.out.println("False");
        }

//        String param = null;
//        switch (param) {
//            case "null":
//                System.out.println("null");
//                break;
//            default:
//                System.out.println("default");
//        }

        BigDecimal ab = new BigDecimal(0.1);
        System.out.println(ab);
        BigDecimal bb = new BigDecimal("0.1");
        System.out.println(bb);

    }
}
