package Puzzles;

import java.util.List;

public class Puzzle9
{
    Utility utility;
    List<Integer> portNumbers;

    public Puzzle9()
    {
        utility = new Utility();
        portNumbers = utility.readNumberFile("#9 portNumbers.txt", true);
    }

    public int findInvalidNumber()
    {
        for (int i = 25; i < portNumbers.size(); i++)
        {
            if(!isSumOfPrevious(i,25))
                return portNumbers.get(i);
        }
        return -1;
    }


    public boolean isSumOfPrevious(int index, int previousAmount)
    {
        for (int i = index-25; i < index; i++)
        {
            for (int j = index-25; j < index; j++)
            {
                if(i!=j)
                {
                    if(portNumbers.get(i)+portNumbers.get(j) == portNumbers.get(index))
                        return true;
                }
            }
        }

        return false;
    }

    public int findEncryptionWeakness()
    {
        int invalid = findInvalidNumber();
        int invalidIndex = portNumbers.indexOf(invalid);
        int sum = 0;
        int min = 0;
        int max = 0;

        for (int i = 0; i < invalidIndex; i++)
        {
            sum = 0;
            int pointer = i;
            while (sum<invalid)
            {
                sum+=portNumbers.get(pointer);
                pointer++;
            }
            if(sum==invalid)
            {
                min = portNumbers.get(i);
                for (int j = i; j <= pointer; j++)
                {
                    int current = portNumbers.get(j);
                    if(current<min)
                        min = current;
                    if(current>max)
                        max = current;
                }
                return min+max;
            }
        }

        return -1;
    }


    public static void main(String[] args)
    {
        Puzzle9 puzzle9 = new Puzzle9();
        System.out.println("First invalid number is " + puzzle9.findInvalidNumber());
        System.out.println("Encryption weakness is " + puzzle9.findEncryptionWeakness());
    }
}
