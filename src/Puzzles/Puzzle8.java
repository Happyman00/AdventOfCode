package Puzzles;

import java.util.ArrayList;
import java.util.List;

public class Puzzle8
{
    Utility utility;
    List<String> instructions;

    public Puzzle8()
    {
        utility = new Utility();
        instructions = utility.readFile("#8 bootInstructions.txt", true);
    }

    public int accBeforeLoop(int lineToChange)
    {
        int acc = 0;
        int i = 0;
        List<Integer> linesExecuted = new ArrayList<>();

        while(!linesExecuted.contains(i)&&i<instructions.size())
        {
            linesExecuted.add(i);

            String instruction = instructions.get(i).substring(0,3);
            int amount = Integer.parseInt(instructions.get(i).substring(4));

            if(i==lineToChange)
            {
                if(instruction.equals("jmp"))
                    instruction="nop";
                else if(instruction.equals("nop"))
                    instruction="jmp";
            }

            if(instruction.equals("jmp"))
                i+=amount-1;
            else if(instruction.equals("acc"))
                acc+=amount;

            i++;
        }

        return acc;
    }

    public int findChangeToExitLoop()
    {
        List<Integer> linesExecutedStored = new ArrayList<>();
        List<Integer> linesExecutedTemp = new ArrayList<>();
        int i = 0;
        int switchIndex = 0;
        boolean foundNewIndex = true;

        while(i<instructions.size())
        {
            String instruction = instructions.get(i).substring(0,3);
            int amount = Integer.parseInt(instructions.get(i).substring(4));

            if(foundNewIndex)
                linesExecutedTemp.add(i);
            else
            {
                linesExecutedStored.add(i);
                if(switchIndex!=i)
                {
                    if (instruction.equals("jmp"))
                    {
                        instruction = "nop";
                        switchIndex = i;
                        foundNewIndex = true;
                    } else if (instruction.equals("nop"))
                    {
                        instruction = "jpm";
                        switchIndex = i;
                        foundNewIndex = true;
                    }
                }
            }

            if(instruction.equals("jmp"))
                i+=amount-1;
            i++;

            if(linesExecutedStored.contains(i)||linesExecutedTemp.contains(i))
            {
                i=switchIndex;
                linesExecutedTemp = new ArrayList<>();
                foundNewIndex = false;
            }
        }

        return switchIndex;
    }

    public static void main(String[] args)
    {
        Puzzle8 puzzle8 = new Puzzle8();
        System.out.println("The accumulator was " + puzzle8.accBeforeLoop(puzzle8.findChangeToExitLoop()) + " before looping");
        System.out.println("Line change to exit loop " + puzzle8.findChangeToExitLoop());
    }
}
