package Puzzles;

import java.util.List;

public class Puzzle2
{
    Utility utility;

    public Puzzle2()
    {
        utility = new Utility();
    }

    private void findAmountOfPasswords(boolean part1)
    {
        List<String> passwords = utility.readFile("#2 passwordPolicies.txt", true);

        int count = 0;

        for(String policie:passwords)
        {
            String[] strings = policie.split(" ");
            int lowerRange = Integer.parseInt(strings[0].substring(0, strings[0].indexOf("-")));
            int higherRange = Integer.parseInt(strings[0].substring(strings[0].indexOf("-") + 1));
            char charToFind = strings[1].charAt(0);
            String password = strings[2];

            if(part1) {
                long charOccurences = password.chars().filter(ch -> ch==charToFind).count();
                if (charOccurences >= lowerRange && charOccurences <= higherRange)
                    count++;
            }
            else
            {
                int amountOfOccurence = 0;
                if(password.charAt(lowerRange-1)==charToFind)
                    amountOfOccurence++;
                if(password.charAt(higherRange-1)==charToFind)
                    amountOfOccurence++;
                if(amountOfOccurence==1)
                    count++;
            }
        }

        System.out.println("The amount of valid passwords is " + count);
    }

    public static void main(String[] args) {
        Puzzle2 puzzle2 = new Puzzle2();
        puzzle2.findAmountOfPasswords(false);
    }
}
