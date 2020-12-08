package Puzzles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Utility {

    public ArrayList readFile(String fileName, Boolean includePathPrefix)
    {
            if(includePathPrefix) {
                fileName = "C:\\Users\\Bruker\\IdeaProjects\\AdventOfCode\\src\\Inputs\\" + fileName;
            }

            ArrayList array = new ArrayList<>();
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line;

                while ((line = reader.readLine()) != null) {
                    array.add(line);
                }
                reader.close();
            }
            catch (Exception e)
            {
                System.out.println(e);
            }
            return array;
    }

    public ArrayList readNumberFile(String fileName, Boolean includePathPrefix)
    {
        if(includePathPrefix) {
            fileName = "C:\\Users\\Bruker\\IdeaProjects\\AdventOfCode\\src\\Inputs\\" + fileName;
        }

        ArrayList array = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                array.add(Integer.parseInt(line));
            }
            reader.close();
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return array;
    }

}
