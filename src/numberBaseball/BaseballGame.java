package numberBaseball;

import java.util.*;

public class BaseballGame extends BaseballGameDisplay {
  private List<Integer> answer; // 정답 리스트
  private int attempts; // 시도 횟수


  // 객체 생성시 정답을 만들도록 함
  public BaseballGame() {
    generateAnswer();
  }

  public void generateAnswer() {
    Random random = new Random();
    Set<Integer> set = new HashSet<>();
    while (set.size() < 3) {
      int randomNum = random.nextInt(9) + 1; // 0~8까지 숫자를 생성하므로 +1 -> 1~9 생성
      set.add(randomNum);
    }
    answer = new ArrayList<>(set);
  }
  public void start() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      // 시작 메뉴얼 출력
      System.out.println("환영합니다! 원하시는 번호를 입력해주세요");
      System.out.println("1. 게임 시작하기");
      System.out.println("2. 게임 기록 보기");
      System.out.println("3. 종료하기");

      String select = scanner.nextLine();

      // 1반 선택 - 게임 시작
      if (select.equals("1")) {
        System.out.println("< 게임을 시작합니다 >");
        play();  // 게임 진행
      }
      // 2번 선택
      if (!select.equals("1") && !select.equals("3")) {
        System.out.println("준비중입니다. 다시 선택해주세요.");
        continue;
      }

      // 3번 선택
      if (select.equals("3")) {
        System.out.println("게임을 종료합니다.");
        break;
      }
    }
    scanner.close();
  }

  public int play() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      // 1. 유저에게 입력값을 받음


      System.out.println("0~9사이 정수를 입력하세요: ");
      String input = scanner.nextLine();
      // 2. 올바른 입력값을 받았는지 검증
      if (!validateInput(input)) {
        System.out.println("입력된 값이 양식에 맞지 않습니다.");
        continue;
      }
      // 3. 게임 진행횟수 증가
      attempts++;
      // 4. 스트라이크 개수 계산
      int strikes = countStrike(input);
      // 5. 정답여부 확인, 만약 정답이면 break 를 이용해 반복문 탈출
      // 6. 볼 개수 계산
      int balls = countBall(input);
      // 7. 힌트 출력
      displayHint(strikes, balls);
      if (strikes == 3) {
        System.out.println("축하합니다. 정답입니다! 시도 횟수: " + attempts);
        return attempts;
      }
    }
  }

  // 게임 진행횟수 반환
  protected boolean validateInput(String input) {
    if (input.length() != 3) return false;
    Set<Character> set = new HashSet<>();
    for (char c : input.toCharArray()) {
      if (!Character.isDigit(c) || c == '0') return false;
      set.add(c);
    }
    return set.size() == 3;

  }

  private int countStrike(String input) {
    int strikeCount = 0;
    for (int i = 0; i < 3; i++) {
      if (answer.get(i) == Character.getNumericValue(input.charAt(i))) {
        strikeCount++;
      }
    }
    return strikeCount;
  }

  private int countBall(String input) {
    int ballCount = 0;
    for (int i = 0; i < 3; i++) {
      int inputNum = Character.getNumericValue(input.charAt(i));
      if (answer.contains(inputNum) && answer.indexOf(inputNum) != i) {
        ballCount++;
      }
    }
    return ballCount;
  }
}

