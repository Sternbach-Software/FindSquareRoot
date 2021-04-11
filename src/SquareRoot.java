import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SquareRoot {
    public static void main(String[] args) {
        System.out.println(squareRoot(1800)[0] + "√" + squareRoot(1800)[1]);
    }

    /*
        public static Pair<Integer, Integer> squareRoot(int number) {
            int number1 = number;
            List<Integer> roots = new ArrayList<>();
            int coefficient = 1;
            int root = 2;
            if (root <= number) {
                while(true) {
                    if (number1 % (root * root) == 0) {
                        roots.add(root);
                        number1 /= root * root;
                        int j = 2;
                        int var7 = number1;
                        if (j <= number1) {
                            while(true) {
                                if (number1 % (j * j) == 0) {
                                    roots.add(j);
                                    number1 /= j * j;
                                }
                                if (j == var7) break;
                                ++j;
                            }
                        }
                    }
                    if (root == number) break;
                    ++root;
                }
            }

            for (Integer root1 : roots) coefficient *= root1;

            return new Pair<>(coefficient, number1);
        }
    */
    public static int[] squareRoot(int number) {
        int number1 = number;
        List<Integer> roots = new ArrayList<>();
        int coefficient = 1;
        for (int i = 2; i < number1; i++) {
            if (number1 % (i * i) == 0) {
                roots.add(i);
                number1 /= i * i;
                for (int j = 2; j < number1; j++) {
                    if (number1 % (j * j) == 0) {
                        roots.add(j);
                        number1 /= j * j;
                    }
                }
            }
        }
        for (int root : roots) coefficient *= root;

        return new int[]{coefficient, number1};
    }}
