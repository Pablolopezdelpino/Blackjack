package com.pldp.util.console;

import java.util.Scanner;

public class In {
    private Out out;
    public In(Out out) {
        this.out = out;
    }
    public boolean questionYN(String prompt) {
        while (true) {
            out.print(String.format("%s [Y/N]: ", prompt));
            String input = readLine().toLowerCase();
            if (input.equals("n")
                    || input.equals("no")) {
                return false;
            }
            if (input.equals("y")
                    || input.equals("yes")) {
                return true;
            }
            out.println("I didn't understand that");
        }
    }
    
    private String readLine() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
        //String input = System.console().readLine();
    }
}
