package org.rent;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        if(console.nextLine().equals("go")){
            Record rec = new Record(100.0, 200.0, 300.0, 5.0, 4.0);
            System.out.println(rec);
        }
    }
}