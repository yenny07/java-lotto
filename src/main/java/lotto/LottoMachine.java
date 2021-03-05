package lotto;

import java.util.*;

public class LottoMachine {

    public void buyTickets(){
        Scanner scanner = new Scanner(System.in);
        String inputMoney = scanner.nextLine();
        int ticketCount = Integer.parseInt(inputMoney) / 1000;
        System.out.println(ticketCount + "개를 구매했습니다.");

        makeTickets(ticketCount);
    }

    private void makeTickets(int ticketCount) {
        List<List> tickets = new ArrayList<>();

        for(int i = 0; i < ticketCount; i++){
            tickets.add(createNumbers());
            System.out.println(tickets);
        }
    }

    private List<Integer> createNumbers() {
        List<Integer> ticket = new ArrayList<>();

        for(int i = 1; i <= 45; i++){
            ticket.add(i);
        }

        Collections.shuffle(ticket);
        ticket = ticket.subList(0, 6);
        Collections.sort(ticket);

        return ticket;
    }
}
