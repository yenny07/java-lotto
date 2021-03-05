package lotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoMachine {

    public void run() {
        int ticketCount;
        int bonusNumber;
        List<List<Integer>> tickets;
        List<Integer> winningNumber;
        int[] hits;

        ticketCount = buyTickets();
        tickets = makeTickets(ticketCount);

        winningNumber = inputWinningNumber();
        bonusNumber = inputBonusNumber();
        hits = matcher(tickets, winningNumber, bonusNumber);
        printResult(hits, ticketCount * 1000);
    }

    private int buyTickets() {
        Scanner scanner = new Scanner(System.in);
        String inputMoney = scanner.nextLine();
        int ticketCount = Integer.parseInt(inputMoney) / 1000;
        System.out.println(ticketCount + "개를 구매했습니다.");

        scanner.close();
        return ticketCount;
    }

    private List<List<Integer>> makeTickets(int ticketCount) {
        List<List<Integer>> tickets = new ArrayList<>();

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

        scanner.close();
        return winningNumber;
    }

    private int inputBonusNumber() {
        System.out.println("보너스 번호를 입력해주세요.");
        Scanner scanner = new Scanner(System.in);
        String bonusNumber = scanner.nextLine();

        scanner.close();
        return Integer.parseInt(bonusNumber);
    }

    private int[] matcher(List<List<Integer>> tickets, List<Integer> winningNumber, int bonusNumber) {
        int[] hits = new int[5];
        long hitCount = 0;
        Rank rank;

        for (List<Integer> ticket : tickets) {
            hitCount = ticket.stream()
                    .filter(winningNumber::contains)
                    .count();
            if (hitCount >= 3) {
                rank = getRank(hitCount, ticket, bonusNumber);
                hits[rank.ordinal()] += 1;
            }
        }

        return hits;
    }

    private Rank getRank(long hitCount, List<Integer> ticket, int bonusNumber) {
        Rank rank = null;

        switch ((int) hitCount) {
            case 3:
                rank = Rank.THREE;
                break;
            case 4:
                rank = Rank.FOUR;
                break;
            case 5:
                rank = (ticket.contains(bonusNumber)) ? Rank.BONUS : Rank.FIVE;
                break;
            case 6:
                rank = Rank.SIX;
                break;
            default:
                rank = null;
        }

        return rank;
    }

    private void printResult(int[] hits, int inputMoney) {
        Rank[] ranks = Rank.values();
        int totalPrize = 0;

        System.out.println("당첨 통계");
        System.out.println("========");
        for (Rank rank : ranks) {
            System.out.println(rank.getHit() + "개 일치 (" + rank.getPrize() + "원) - " + hits[rank.ordinal()] + "개");
            totalPrize += rank.getPrize() * hits[rank.ordinal()];
        }

        System.out.println("총 수익률은 " + (totalPrize / inputMoney) + "입니다.");
    }

}
