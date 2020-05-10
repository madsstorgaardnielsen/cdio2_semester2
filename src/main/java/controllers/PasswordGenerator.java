package controllers;

public class PasswordGenerator {
    private char[] uppercaseLetters = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90};
    private char[] lowercaseLetters = {97, 98, 99, 100, 101, 102, 103, 104, 105, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122};
    private char[] numbers = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57};

    public String generatePassword() {
        char[] code = new char[8];
        code[0] = getUppercase();
        code[1] = getLowercase();
        code[2] = getNumber();
        for (int i = 3; i < 8; i++) {
            int num = (int) (Math.random() * 3);
            switch (num) {
                case 0:
                    code[i] = getUppercase();
                    break;
                case 1:
                    code[i] = getLowercase();
                    break;
                default:
                    code[i] = getNumber();
            }
        }
        return new String(code);
    }

    private char getUppercase() {
        int num = (int) (Math.random() * uppercaseLetters.length);
        return uppercaseLetters[num];
    }

    private char getLowercase() {
        int num = (int) (Math.random() * lowercaseLetters.length);
        return lowercaseLetters[num];
    }

    private char getNumber() {
        int num = (int) (Math.random() * numbers.length);
        return numbers[num];
    }
}
