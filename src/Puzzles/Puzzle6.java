package Puzzles;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Puzzle6
{
    Utility utility;
    List<String> declarations;


    public Puzzle6()
    {
        utility = new Utility();
        declarations = utility.readFile("#6 customsDeclarations.txt", true);
    }

    public int findAmountOfTotalQuestions()
    {
        int totalAmount = 0;
        String groupString = "";

        for(String line:declarations)
        {
            groupString+=line;
            if(line.isEmpty())
            {
                totalAmount += groupString.chars().distinct().count();
                groupString = "";
            }
        }
        totalAmount += groupString.chars().distinct().count(); //I couldn't get the last group to be added unless I did this.

        return totalAmount;
    }


    public int findAmountOfTotalUniformQuestions()
    {
        int totalAmount = 0;
        ArrayList<String> group = new ArrayList<>();

        for(String line:declarations)
        {
            if(line.isEmpty())
            {
                String groupString = matchingChars(group);
                totalAmount += groupString.chars().distinct().count();
                group = new ArrayList<>();
            }
            else
                group.add(line);
        }

        return totalAmount;
    }

    private String matchingChars(ArrayList<String> group)
    {
        String currentString = group.get(0);
        for (int i = 0; i < group.size(); i++)
        {
            String s2 = group.get(i);

            List<Character> chars1 = currentString.chars()    // list of chars for first string
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toList());

            List<Character> chars2 = s2.chars()    // list of chars for second string
                    .mapToObj(c -> (char) c)
                    .collect(Collectors.toList());

            chars1.retainAll(chars2);
            currentString = chars1.stream().map(String::valueOf).collect(Collectors.joining());
        }

        return currentString;
    }


    public static void main(String[] args)
    {
        Puzzle6 puzzle6 = new Puzzle6();
        //System.out.println("Total sum of counts is " + puzzle6.findAmountOfTotalQuestions());
        System.out.println("Total sum of uniform counts is " + puzzle6.findAmountOfTotalUniformQuestions());
    }
}
