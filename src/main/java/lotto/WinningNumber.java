package lotto;

import java.util.ArrayList;

public class WinningNumber {
    private ArrayList<Integer> sixNumbers;
    private int bonusNumber;

    public WinningNumber() {
        sixNumbers = new ArrayList<>();
        bonusNumber = 0;
    }

    public WinningNumber(ArrayList<Integer> testInput, int testBonusNumber) {
        sixNumbers = new ArrayList<>(testInput);
        bonusNumber = testBonusNumber;
    }

    public int getBonusNumber() {
        return bonusNumber;
    }

    public ArrayList<Integer> getSixNumbers() {
        return sixNumbers;
    }

    public void inputWinningNumber() {
        Input input = new Input();
        String inputString = input.inputLine("지난 주 당첨 번호를 입력해주세요.");

        String[] stringNumber = inputString.split(",");

        for (String num : stringNumber) {
            sixNumbers.add(Integer.parseInt(num));
        }
    }

    public void inputBonusNumber() throws Exception {
        Input input = new Input();
        bonusNumber = input.inputInt("보너스 번호를 입력해주세요.");

        if (sixNumbers.contains(bonusNumber)) {
            throw new RuntimeException("숫자가 중복됩니다.");
        }
    }
}
