# java-lotto
로또 미션 진행을 위한 저장소

### 풀이
1. main()에서 LottoMachine 객체를 만들어 run() 함수를 호출합니다. 
```java
public static void main(String[] args) {
        LottoMachine lottoMachine = new LottoMachine();
        lottoMachine.run();
    }
```

2. 일련의 작업 과정은 run() 안에서 이뤄집니다. 구매액을 입력받고, 티켓들을 생성하고, 당첨번호와 보너스번호를 입력받고, 당첨 여부를 매치하여 결과를 출력합니다.
```java
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
```

3. makeTicket() 메소드는 티켓 한 장을 하나의 ArrayList로 티켓들을 생성해 하나의 묶음으로 만들어 List<List<Integer>>를 리턴합니다. 티켓 한 장을 만들 땐 ArrayList에 1부터 45까지의 숫자를 넣고 이를 Collections.shuffle()을 이용하여 무작위 배치한 뒤, subList(0, 6)로 앞 6개의 요소만 남긴 채 오름차 정렬했습니다.
```java
for (int i = 0; i < ticketCount; i++) {
            tickets.add(createNumbers());
            System.out.println(tickets.get(i));
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
```

4. 당첨 번호와 보너스 번호는 입력받은 값을 정수로 변환해 활용합니다.
```java
List<String> stringNumber = Arrays.asList(inputString.split(","));
    List<Integer> winningNumber = stringNumber.stream()
            .map(Integer::parseInt)
            .collect(Collectors.toList());
```

5. matcher()에서 하나의 티켓에 접근해 이를 stream()으로 펼치고, 당첨 번호에 해당하는 번호가 몇 개인지 셉니다. 3개 이상이라면, 몇 등인지 계산하는 getRank()를 호출합니다. hits[] 배열에 0번째 인덱스부터 3등 티켓의 개수, 4등 티켓의 개수... 식으로 저장됩니다.
```java
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
        ...
}

private Rank getRank(long hitCount, List<Integer> ticket, int bonusNumber) {
        Rank rank = null;

        switch ((int) hitCount) {
            case 3:
                rank = Rank.THREE;
                break;
            ...
        }

        return rank;
}
```

6. printResult()는 결과를 출력하며 hits[] 배열에 접근해 총 상금액과 수익률을 구합니다.
```java
for (Rank rank : ranks) {
            System.out.println(rank.getHit() + "개 일치 (" + rank.getPrize() + "원) - " + hits[rank.ordinal()] + "개");
            totalPrize += rank.getPrize() * hits[rank.ordinal()];
}

System.out.println("총 수익률은 " + (totalPrize / inputMoney) + "입니다.");

```