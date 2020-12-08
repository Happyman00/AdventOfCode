package Puzzles;

import java.util.List;

public class Puzzle1
{
    Utility utility;

    public Puzzle1()
    {
        utility = new Utility();
    }

    private void findNumbersThatAddTo2020()
    {
        List<Integer> lines = utility.readNumberFile("#1 expenseReport.txt", true);
        for(Integer i:lines)
        {
            for(Integer j:lines)
            {
                for(Integer k:lines)
                {
                    if (i + j + k == 2020) {
                        System.out.println("Found the three numbers: " + i + " and " + j + " and " + k);
                        System.out.println("Your answer is: " + i * j * k);
                    }
                }
            }
        }
    }






    public static void main(String[] args)
    {
        Puzzle1 puzzle = new Puzzle1();
        puzzle.findNumbersThatAddTo2020();
    }


}
