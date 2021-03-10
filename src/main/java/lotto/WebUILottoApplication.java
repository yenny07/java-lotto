package lotto;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;
import static spark.Spark.get;

public class WebUILottoApplication {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        int ticketCount = 0;
        double yield = 0;

        try {
            ticketCount = buyTickets();
            yield = lottoMachine.run(ticketCount);
            //System.out.println(Arrays.toString(lottoMachine.hits));

            Output output = new Output();
            output.printResult(lottoMachine.hits);

            output.printYield(yield);

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
        System.out.println(ticketCount + "개를 구매했습니다.");

        return ticketCount;
    }
}
