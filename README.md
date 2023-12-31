# Nomai Script Word Processor

## Description
This is a word processor for the Nomai language. Nomai is a species of alien 
from the videogame Outer Wilds. Their language is made of spirals and branches
in many directions. I have taken the asthetics from the game and turned it
into a constructed script (con-script). Once the program loads, typing will
turn the English letters into Nomai. Each letter has a distinct symbol it turns
into. Try and see if you can find some!

## Table of Contents
- [Screenshots](#screenshots)
- [Code Examples](#code-examples)
- [To Do List](#to-do-list)

## Screenshots
### Original game:
![Nomai Writing in Game](images/nomai-writing-in-game.png)
### My version:
![Example Screenshot](images/example-screenshot.png)

## Code Examples
This is the code to delete characters
```agsl
    public boolean backspace() {
        // returns true if there was no text - delete paragraph
        if (cursorLocation != 0 && nomaiText.length() > 1) {
            nomaiText = nomaiText.substring(0, cursorLocation - 1) + nomaiText.substring(cursorLocation);
            cursorLocation--;
        } else if (cursorLocation != 0 && nomaiText.length() == 1) {
            nomaiText = "";
            cursorLocation = 0;
        } else if (cursorLocation == 0 && nomaiText.length() == 0) {
            return true;
        }
        return false;
    }
```
This is the code to translate plain english text to what the program reads
```agsl
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
```
## To Do List
- Manual sizing
- Automatic sizing mode
- Fix bug: sometimes line draws to corner when origional text has length of 25
- Change how cursor is implimented
