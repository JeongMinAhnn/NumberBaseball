package numberBaseball;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Practice {
  public Practice() {
    thisMethod();
  }

  public void thisMethod() {
    Random random = new Random();
    Set<Integer> set = new HashSet<>();
    while (set.size() < 3) {
      int randomNum = random.nextInt(9) + 1;
      set.add(randomNum);
    }
    System.out.println(set);
  }

  public static void main(String[] args) {
    Practice practice = new Practice();


  }
}

