import java.util.regex.Pattern;

public class PIN{
//    Exactly 4 or 6 characters.
//    Only numeric characters (0-9).
//    No whitespace.
//    Examples
//    validate("121317") ➞ true
//
//    validate("1234") ➞ true
//
//    validate("45135") ➞ false
//
//    validate("89abc1") ➞ false
//
//    validate("900876") ➞ true
//
//    validate(" 4983") ➞ false

    public static boolean validate(String s) {
        if (s == null || s.isEmpty() || s.isBlank()) return false;

        return s.matches("[0-9]{4}|[0-9]{6}");
    }

    public static void main(String[] args) {
        System.out.println(validate("123123"));
        System.out.println(validate(" 23123"));
        System.out.println(validate(null));
        System.out.println(validate("89abc1"));
        System.out.println(validate("1234"));
    }
}
