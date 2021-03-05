package lotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoMachine {

    public void run() {
        int ticketCount;
        int bonusNumber;
        List<List> tickets;
        List<Integer> winningNumber;

        ticketCount = buyTickets();
        tickets = makeTickets(ticketCount);
        // System.out.println(tickets);
        winningNumber = inputWinningNumber();
        bonusNumber = inputBonusNumber();

    }

    private int buyTickets() {
        Scanner scanner = new Scanner(System.in);
        String inputMoney = scanner.nextLine();
        int ticketCount = Integer.parseInt(inputMoney) / 1000;
        System.out.println(ticketCount + "개를 구매했습니다.");

        return ticketCount;
    }

    private List<List> makeTickets(int ticketCount) {
        List<List> tickets = new ArrayList<>();

        for (int i = 0; i < ticketCount; i++) {
            tickets.add(createNumbers());
            System.out.println(tickets.get(i));
        }

        return tickets;
    }

    private List<Integer> createNumbers() {
        List<Integer> ticket = new ArrayList<>();

        for (int i = 1; i <= 45; i++) {
            ticket.add(i);
        }

        Collections.shuffle(ticket);
        ticket = ticket.subList(0, 6);
        Collections.sort(ticket);

        return ticket;
    }

    private List<Integer> inputWinningNumber() {
        System.out.println("지난 주 당첨 번호를 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        List<String> stringNumber = Arrays.asList(inputString.split(","));
        List<Integer> winningNumber = stringNumber.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        return winningNumber;
    }

    private int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        String bonusNumber = scanner.nextLine();

        return Integer.parseInt(bonusNumber);
    }

}
