package lotto;

import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.exit;
import static spark.Spark.get;

public class WebUILottoApplication {
    public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        int ticketCount = 0;

        try {
            ticketCount = lottoMachine.buyTickets();
            lottoMachine.run(ticketCount);

            Output output = new Output();
            output.printResult(lottoMachine.hits, ticketCount * 1000);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
