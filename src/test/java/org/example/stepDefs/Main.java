package org.example.stepDefs;

public class Main {

    public static void main(String[] args) {

        String input = "(7) Records Found";
        String numberStr = input.substring(input.indexOf('(') + 1, input.indexOf(')'));
        int number = Integer.parseInt(numberStr);
        System.out.println("Extracted number: " + number+1);
    }
}
