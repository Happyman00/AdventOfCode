package Puzzles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Puzzle5
{
    Utility utility;
    List<String> passes;

    public Puzzle5()
    {
        utility = new Utility();
        passes = utility.readFile("#5 boardingPasses.txt", true);
    }


    public int findHighestSeatID()
    {
        int currentHighestID = 0;

        for (String pass:passes)
        {
            int seatID = findSeatID(pass);
            if(seatID>currentHighestID)
                currentHighestID = seatID;
        }

        return currentHighestID;
    }

    public int findAvailableSeatID()
    {
        List<Integer> passIDs = new ArrayList<>();
        for(String pass:passes)
        {
            passIDs.add(findSeatID(pass));
        }

        Collections.sort(passIDs);
        for (int i = 0; i < passIDs.size()-1; i++)
        {
            if(passIDs.get(i) != passIDs.get(i+1) - 1)
                return passIDs.get(i) + 1;
        }

        return -1;
    }


    public int findSeatID(String boardingPass)
    {
        int row = 0;
        int column = 0;

        int rowBottom = 0;
        int rowTop= 127;
        int columnBottom = 0;
        int columnTop = 7;

        String rowString = boardingPass.substring(0,7);
        String columnString = boardingPass.substring(7);

        for(char r:rowString.toCharArray())
        {
            int difference = (rowTop+1-rowBottom)/2;
            if(r=='F')
                rowTop = rowTop - difference;
            else
                rowBottom = rowBottom + difference;
        }
        row = rowTop;

        for(char c:columnString.toCharArray())
        {
            int difference = (columnTop+1-columnBottom)/2;
            if(c=='L')
                columnTop = columnTop - difference;
            else
                columnBottom = columnBottom + difference;
        }
        column = columnTop;

        return (row * 8) + column;
    }


    public static void main(String[] args)
    {
        Puzzle5 puzzle5 = new Puzzle5();
        System.out.println("The highest seatID is " + puzzle5.findHighestSeatID());
        System.out.println("Found one available seat with ID " + puzzle5.findAvailableSeatID());
    }
}
