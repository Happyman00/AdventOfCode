package Puzzles;

import java.util.List;

public class Puzzle3
{
    Utility utility;

    public Puzzle3()
    {
        utility = new Utility();
    }

    public void findAmountOfTrees()
    {
        List<String> map = utility.readFile("#3 mapOfTrees.txt", true);
        int amountOfTreesHit = 0;
        int currentPos = 0;
        int currentLine = 0;

        int stepAmountRight = 1;
        int stepAmountDown = 2                                                                      ;

        for(String line:map)
        {
            if(currentLine%stepAmountDown == 0)
            {
                if(line.charAt(currentPos)=='#')
                {
                    amountOfTreesHit++;
                }

                if(line.length() <= currentPos+stepAmountRight)
                    currentPos = currentPos - line.length() + stepAmountRight;
                else
                    currentPos = currentPos+stepAmountRight;
            }

            currentLine++;
        }

        System.out.println("Amount of trees hit is " + amountOfTreesHit);
    }


    public static void main(String[] args)
    {
        Puzzle3 puzzle3 = new Puzzle3();
        puzzle3.findAmountOfTrees();
    }
}
