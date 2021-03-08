package lotto;

import java.util.Scanner;

public class Input {
    Scanner scanner = new Scanner(System.in);

    public String inputLine(String guide) {
        System.out.println(guide);
        return scanner.nextLine();
    }

    public int inputInt(String guide) {
        System.out.println(guide);
        return scanner.nextInt();
    }
}
