package lotto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WinningNumber extends ArrayList<Integer> {

    private int bonusNumber;

    public int getBonusNumber() {
        return bonusNumber;
    }

    public void inputWinningNumber() {
        Input input = new Input();
        String inputString = input.inputLine("지난 주 당첨 번호를 입력해주세요.");

        String[] stringNumber = inputString.split(",");

        for (String num : stringNumber) {
            this.add(Integer.parseInt(num));
        }
    }

    public void inputBonusNumber() {
        Input input = new Input();
        bonusNumber = input.inputInt("보너스 번호를 입력해주세요.");
    }
}
