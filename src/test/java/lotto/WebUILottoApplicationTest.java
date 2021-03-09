package lotto;

import org.eclipse.jetty.util.DateCache;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class WebUILottoApplicationTest {
    @Test
    public void testLottoMachine() throws NoSuchMethodException {
        LottoMachine lottoMachine = new LottoMachine();
        Ticket ticket = new Ticket(new ArrayList<>(Arrays.asList(1,2,3,4,5,6)));
        List<ArrayList<Integer>> tickets = new ArrayList<>();
        tickets.add(ticket);
        WinningNumber winningNumber = new WinningNumber(new ArrayList<>(Arrays.asList(1,2,3,4,5,10)), 6);

        Method method = lottoMachine.getClass()
                .getDeclaredMethod("matcher", List.class, WinningNumber.class);
        method.setAccessible(true);

        try {
            method.invoke(lottoMachine, tickets, winningNumber);
            assertThat(lottoMachine.hits[2]).isEqualTo(0);
            assertThat(lottoMachine.hits[3]).isEqualTo(1);
            assertThat(lottoMachine.hits[4]).isEqualTo(0);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}