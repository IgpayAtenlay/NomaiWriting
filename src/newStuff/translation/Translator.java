package newStuff.translation;

public class Translator {

    public static String translate(String origional) {
        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < origional.length(); i++) {
            if (Character.isUpperCase(origional.charAt(i))) {
                newString.append("^");
                newString.append(Character.toLowerCase(origional.charAt(i)));
            } else {
                newString.append(origional.charAt(i));
            }
        }

        return newString.toString();
    }
}
