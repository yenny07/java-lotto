package lotto;

public class WebUILottoApplication {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        int ticketCount = 0;
        double yield = 0;

        try {
            ticketCount = buyTickets();
            yield = lottoMachine.run(ticketCount);

            Output.printResult(lottoMachine.hits);
            Output.printYield(yield);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static int buyTickets() throws RuntimeException {
        Input input = new Input();
        int inputMoney = input.inputInt("금액을 입력하세요.");

        if (inputMoney < 1000) {
            throw new RuntimeException("한장도 사지 못하는 금액입니다.");
        }

        int ticketCount = inputMoney / 1000;
        Output.printTicketCount(ticketCount);

        return ticketCount;
    }
}
