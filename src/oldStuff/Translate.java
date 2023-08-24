package oldStuff;

import java.util.ArrayList;

public class Translate {

    public static ArrayList<String> translate(String sentence) {
        ArrayList<String> chars = stringToChar(sentence);
        parseUppercase(chars);
        punctuation(chars);
        return chars;
    }

    private static ArrayList<String> stringToChar(String sentence) {
        ArrayList<String> chars = new ArrayList<>();
        for (char letter : sentence.toCharArray()) {
            chars.add(String.valueOf(letter));
        }
        return chars;
    }

    private static void parseUppercase(ArrayList<String> chars) {
        for (int i = 0; i < chars.size(); i++) {
            if (!chars.get(i).toLowerCase().equals(chars.get(i))) {
                chars.set(i, chars.get(i).toLowerCase());
//                turn this on to start uppercasing
//                chars.add(i, "Y");
//                i++;
            }
        }
    }

    private static void punctuation(ArrayList<String> chars) {
//        right now remove all punctuation
        for (int i = 0; i < chars.size(); i++) {
            if (chars.get(i).toLowerCase().equals(chars.get(i)) && chars.get(i).toUpperCase().equals(chars.get(i))) {
                chars.remove(i);
                i--;
            }
        }
    }
}
