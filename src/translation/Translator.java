package translation;

public class Translator {

    public static String toNomai(String english) {
        StringBuilder nomai = new StringBuilder();

        for (int i = 0; i < english.length(); i++) {
            // if uppercase put the "^" symbol in front
            if (Character.isUpperCase(english.charAt(i))) {
                nomai.append("^");
                nomai.append(Character.toLowerCase(english.charAt(i)));
            } else {
                nomai.append(english.charAt(i));
            }
        }

        return nomai.toString();
    }

    public static String toEnglish(String nomai) {
        StringBuilder english = new StringBuilder();
        for (int i = 0; i < nomai.length(); i++) {
            if (nomai.charAt(i) == '^') {
                i++;
                if (i < nomai.length() && Character.isLowerCase(nomai.charAt(i))) {
                    english.append(Character.toUpperCase(nomai.charAt(i++)));
                } else {
                    return null;
                }
            } else {
                english.append(nomai.charAt(i));
            }
        }

        return english.toString();
    }
}
