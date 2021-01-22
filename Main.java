package bullscows;

import java.util.*;

public class Main {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Game game =  new Game();
    game.pseudorandom();
    game.gameLoop();



    }
}
class Game {
    Scanner sc = new Scanner(System.in);
    private String code ="";
    private String answer;
    private int[] gamestatus = new int[2];
    private int turn = 1;
    StringBuilder sprawdzacz = new StringBuilder();

    public void pseudorandom(){
        Random r = new Random();
        Random a = new Random();

        System.out.println("Input the length of the secret code:");
        int input = sc.nextInt();
        System.out.println("Input the number of possible symbols in the code:");
        int chars = sc.nextInt();

        String alphabet = "abcdefghijklmnopqrstuvwxyzz";
        StringBuilder stars = new StringBuilder();

        for (int i = 0; i < input; i++) {
            stars.append("*");
        }
        if (chars <= 10) {
            while (code.length()  < input) {
                int random = r.nextInt(9);
                if (!code.contains(String.valueOf(random))) {
                    code += String.valueOf(random);
                }
            }

            this.code =code;
            System.out.println("The secret code is prepared: "+stars+" (0-9).");
            System.out.println("Okay, let's start a game!");
        }
        if (chars > 10) {
            chars = chars - 10;
            String timalphabet = alphabet.substring(0,chars);
            while (code.length()    < input) {
                int random = r.nextInt(9);
                char symbol = timalphabet.charAt(r.nextInt(chars));
                if (!code.contains(String.valueOf(random))) {
                    code += String.valueOf(random);
                }
                if (!code.contains(String.valueOf(symbol)) && chars > 10) {
                    code += String.valueOf(symbol);
                }
            }
            System.out.println("The secret code  is prepared: "+stars+" (0-9, a-"+alphabet.charAt(chars-1)+").");
            System.out.println("Okay, let's start a game!");
        }
        this.code =code;
        System.out.println("CODE "+code);
    }



    public void pseudoradom1() {
        Random r = new Random();
        System.out.println("Please, enter the secret code's length:");
        int input = sc.nextInt();
        System.out.println("Input the number of possible symbols in the code:");
        int chars = sc.nextInt();
        if (input > 10) {
            System.out.println("Error: can't generate a secret number with a length of" + input + " because there aren't enough unique digits.");
            System.exit(0);
        } else {
            List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            numbers.add(i);
        }

        Collections.shuffle(numbers);

        String result = "";
        for (int i = 0; i < input; i++) {
            result += numbers.get(i).toString();
        }
        code = result;
    }

}

    public void gamesrame(){
        setAnswer(sc.next());
        gamcheck(code,answer,gamestatus);
        if (gamestatus[0] == 0 && gamestatus[1] ==1) {
            System.out.println("Grade: None. The secret code is "+ code +".");
        } else if ( gamestatus[0] == 0 && gamestatus [1] !=0) {
            System.out.println("Grade: "+ gamestatus[1]+" cow(s). The secret code is "+ code +".");
        } else if (( gamestatus[0] != 0 && gamestatus [1] ==0)) {
            System.out.println("Grade: "+ gamestatus[0]+" bull(s). The secret code is "+ code +".");
        } else {
            System.out.println("Grade: "+ gamestatus[0]+" bull(s) and "+gamestatus[1]+" cow(s). The secret code is "+ code +".");
        }
    }
    public void gameLoopStage4(){
        while (gamestatus[0] != code.length()) {
            gamestatus[0] = 0;
            gamestatus [1] = 0;
            System.out.println("Turn "+turn+":");
            setAnswer(sc.next());
            gamcheck(code,answer,gamestatus);
            printer(gamestatus);

        }
    }




    public void gameLoop(){
        while (gamestatus[0] != code.length()) {
            gamestatus[0] = 0;
            gamestatus [1] = 0;
           System.out.println("Turn "+turn+ " Answer:");
           setAnswer(sc.next());
           gamcheck(code,answer,gamestatus);
           printer(gamestatus);

        }
    }


    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getCode() {
        return code;
    }

    public String getAnswer() {
        return answer;
    }

    public int[] getGamestatus() {
        return gamestatus;
    }

    void gamcheck (String code, String answer, int[] gamestatus) {
            for (int i = 0; i < code.length(); i ++) {
                if (code.charAt(i) == answer.charAt(i)) {
                    gamestatus[0] ++;
                } else if (code.contains(String.valueOf(answer.charAt(i)))) {
                gamestatus[1]++;
                }
            }
        turn++;
    }
     void printer(int[] gamestatus) {

        String cow = "";
        String bull = "";
         if ( gamestatus[0] == 1) {
            bull ="1 bull";
        } else if (gamestatus[0] > 1) {
            bull = gamestatus[0]+" bulls";
        }
        if ( gamestatus[1] == 1) {
             cow ="1 cow";
         } else if (gamestatus[1] > 1) {
             cow = gamestatus[1]+" cows";
         }

        if (gamestatus[0] == code.length()) {
            System.out.println("Grade: " +bull + ".");
            System.out.println("Congratulations! You guessed the secret code.");
            // System.out.println("Congrats! The secret code is "+ code +".");
        } else if (gamestatus[0]==0 && gamestatus[1] == 0) {
            System.out.println("Grade: None.");
        } else if (gamestatus[0] == 0 && gamestatus[1] !=0) {
            System.out.println("Grade: "+cow+".");
        } else if (gamestatus[1] == 0 && gamestatus[0] !=0) {
            System.out.println("Grade: "+bull+".");
        } else {
            System.out.println("Grade: "+ bull+" and "+ cow +".");
        }
    }
}

