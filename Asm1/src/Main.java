import java.util.Scanner;

public class Main {
    private static int guessCount = 0;
    private static int totalGuess = 0;
    private static int gameCount = 0;
    private static int bestGame = Integer.MAX_VALUE;

    public static void main(String[] args) {

        play();
        report();

    }

    private static void play() {
        //define variables
        int min = 0;
        int max = 100;
        int guessNum;
        int rand = getRand(min, max);

        Scanner input = new Scanner(System.in);
        String guessNumStr, asw = "";

        boolean isContinue;
        boolean isPlayAgainContinue = true;

        //game start
        gameCount++;

        //greetings
        System.out.println();
        System.out.println("I'm thinking of a number between " + min + " and " + max + "...");
        while (true) {
            System.out.print("Your guess? ");
                guessNumStr = input.next();

            if(isValidNum(guessNumStr)) {
                guessNum = Integer.parseInt(guessNumStr);
                if(guessNum < min || guessNum > max) {
                        System.out.println("Please guess a number between " + min + " and " + max +  " only");
                        continue;
                    }
                } else {
                    System.out.println("Invalid input value, please type number value only.");
                    continue;
                }

                //valid number input -> compare to rand number.
                guessNum = Integer.parseInt(guessNumStr);
                guessCount++;
                totalGuess++;


            if (guessNum == rand) {
                System.out.println("You got it right in " + guessCount + " guess(es)!");
                if(guessCount < bestGame) {
                    bestGame = guessCount;
                }
                guessCount = 0; //reset game count
                System.out.print("Do you want to play again? ");

                //Play again? feature
                while(isPlayAgainContinue) {
                    asw = input.next();
                    if(!isValidPlayAgainAnswer(asw)){
                        System.out.println("Please input valid answer.");
                        continue;
                    }
                    break;
                }
                isContinue = isPlayAgain(asw);
                if(!isContinue) { //user refuses play again
                    break;
                } else { //user continues game, make a new round
                    rand = getRand(min, max); //reset random number
                    gameCount++; //new round
                    continue;
                }
            }

            if (guessNum < rand) {
                System.out.println("It's higher.");
                continue;
            }
            if (guessNum > rand) {
                System.out.println("It's lower.");
            }
        }

    }

    private static void report() {

        System.out.println();
        System.out.println("Overall Results:");
        System.out.printf("%-16s%-2s%d","Total game", "= ", gameCount);
        System.out.println();
        System.out.printf("%-16s%-2s%d","Total guess(es)", "= ", totalGuess);
        System.out.println();
        System.out.printf("%-16s%-2s%.1f","Guess(es)/game","= ",(double)totalGuess/gameCount);
        System.out.println();
        System.out.printf("%-16s%-2s%d","Best game", "= ", bestGame);

    }

    //CHECK VALID METHODS
    /**
     * Check if the input String is a valid number or not
     * @param guessNumStr user's guess input in
     * @return true if given String is a valid number, otherwise false
     * @author Minh Ho
     * @since 2020-05-Oct
     */
    private static boolean isValidNum(String guessNumStr) {

        if (guessNumStr == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(guessNumStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /** Check if user input a valid answer for [Play Again?] question
     *
     * @param asw user's answer input
     * @return true if input is ["yes", "y", "no", "n", "khong", "co"], else returns false
     * @author Minh Ho
     * @since 2020-05-Oct
     */
    private static Boolean isValidPlayAgainAnswer(String asw) {
        return asw.equalsIgnoreCase("yes")
                || asw.equalsIgnoreCase("y")
                || asw.equalsIgnoreCase("no")
                || asw.equalsIgnoreCase("n")
                || asw.equalsIgnoreCase("khong")
                || asw.equalsIgnoreCase("co");
    }


    /** Check if user wants to play again or not
     * @param asw answer from user
     * @return true if user inputs ["yes", "y"], ignores case
     * @author Minh Ho
     * @version 1.0
     * @since 2020-05-Oct
     */
    //TODO: Dùng lệnh do…while để thực hiện phần đưa ra các lượt chơi. Điều kiện tiếp tục là: y, Y, yes, Yes, YES (yes không phân biệt hoa thường).
    private static boolean isPlayAgain(String asw) {
        return asw.equalsIgnoreCase("yes")
                || asw.equalsIgnoreCase("y");
    }

    /**
     * Generate random number between min and max value, print result in case of test
     * @param min minimum value
     * @param max maximum value
     * @return Return random number between min value and max value
     * @author Minh Ho
     * @version 1.0
     * @since 2020-05-Oct
     */
    //TODO: Dùng hàm random() để sinh ra số ngẫu nhiên
    private static int getRand(int min, int max) {
        return (int) (Math.random() * (max - min + 1) + min);
    }
}



