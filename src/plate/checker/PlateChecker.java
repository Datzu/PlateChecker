/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package plate.checker;

import java.util.Arrays;
import java.util.Scanner;

public class PlateChecker {

    public static void main(String[] args) {
        new PlateChecker().start();
    }

    char[] lettersArray = {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L',
            'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'V', 'W', 'X', 'Y', 'Z'};

    String[] forbbidenChars = {"A", "E", "I", "O", "U", "Ñ"};

    private void start() {
        System.out.println("The next plate is: " + this.processPlate(this.getPlateFromUser()));
    }

    private String getPlateFromUser() {
        String plate = "";

        while (this.checkPlateCorrect(plate)) {
            Scanner input = new Scanner(System.in);

            System.out.println("Write your plate: ");
            plate = input.next();

            if (checkPlateCorrect(plate)) {
                System.out.println("The plate must have the next pattern: NNNNLLL -> N - numbers (0 - 9), L - letters -> " + Arrays.toString(lettersArray));
            }
        }

        return plate;
    }

    private boolean checkPlateCorrect(String plate) {
        return !plate.matches("^[0-9]{4}[A-Z]{3}$") || this.isAForbiddenChar(plate);
    }

    public String processPlate(String plate) {
        int numbers = Integer.parseInt(plate.substring(0, 4));
        int nextNumber = numbers + 1;
        String letters = plate.substring(4, 7);

        if (nextNumber > 9999) {
            return this.processNextLetters(nextNumber, letters);
        } else {
            return this.buildPlate(nextNumber, letters);
        }
    }

    private  String buildPlate(int numbers, String letters) {
        String numbersString = String.valueOf(numbers);
        while (numbersString.length() < 4) {
            numbersString = "0" + numbersString;
        }
        return numbersString + letters;
    }

    private String processNextLetters(int nextNumber, String letters) {
        char[] lettersFromUser = letters.toCharArray();
        int[] lettersNumericValue = new int[3];

        lettersNumericValue[0] = (int) lettersFromUser[0];
        lettersNumericValue[1] = (int) lettersFromUser[1];
        lettersNumericValue[2] = (int) lettersFromUser[2];

        lettersNumericValue[0]++;
        if (lettersNumericValue[0] > 90) {
            lettersNumericValue[0]--;
            lettersNumericValue[1]++;
            if (isAForbiddenChar(String.valueOf(lettersNumericValue[1]))) {
                lettersNumericValue[1]++;
            }

            if (lettersNumericValue[1] > 90) {
                lettersNumericValue[1]--;
                lettersNumericValue[2]++;
                if (isAForbiddenChar(String.valueOf(lettersNumericValue[1]))) {
                    lettersNumericValue[2]++;
                }

                if (lettersNumericValue[2] > 90) {
                    return "0000BBB";
                }
            }
        }

        return String.valueOf("0000" + (char) lettersNumericValue[0] + (char) lettersNumericValue[1] + (char) lettersNumericValue[2]);
    }

    private boolean isAForbiddenChar(String plate) {
        return Arrays.stream(forbbidenChars).parallel().anyMatch(plate::contains);
    }

}