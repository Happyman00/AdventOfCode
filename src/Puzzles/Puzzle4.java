package Puzzles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle4
{
    Utility utility;
    List<String> passportFields;

    public Puzzle4()
    {
        utility = new Utility();
        passportFields = new ArrayList<>();
        passportFields.add("byr");
        passportFields.add("iyr");
        passportFields.add("eyr");
        passportFields.add("hgt");
        passportFields.add("hcl");
        passportFields.add("ecl");
        passportFields.add("pid");
        //passportFields.add("cid");
    }

    public void findValidPassports()
    {
        List<String> passports = utility.readFile("#4 passports.txt", true);

        int amountOfValidPasswords = 0;
        int currentPasswordCount = 0;
        int currentLine = 0;

        for(String line:passports)
        {
            if(line.isEmpty())
            {
                //next passport
                currentPasswordCount = 0;
            }

            currentPasswordCount += containsFields(line);
            System.out.println(currentLine + "   " + currentPasswordCount);

            if(currentPasswordCount==7)
            {
                amountOfValidPasswords++;
                currentPasswordCount = 0;
            }
            currentLine++;
        }

        System.out.println("Amount of passports is " + passports.size());
        System.out.println("Amount of valid passports is " + amountOfValidPasswords);
    }

    public int containsFields(String line)
    {
        if(line.isEmpty())
            return 0;

        int amountOfFields = 0;

        String[] fields = line.split(" ");

        for (int i = 0; i < fields.length; i++)
        {
            String field = fields[i].substring(0, 3);
            String value = fields[i].substring(4);

            switch (field)
            {
                case "byr":
                    if (Integer.parseInt(value) < 1920 || Integer.parseInt(value) > 2002)
                    {
                        amountOfFields--;
                    }
                    break;
                case "iyr":
                    if (Integer.parseInt(value) < 2010 || Integer.parseInt(value) > 2020)
                    {
                        amountOfFields--;
                    }
                    break;
                case "eyr":
                    if (Integer.parseInt(value) < 2020 || Integer.parseInt(value) > 2030)
                    {
                        amountOfFields--;
                    }
                    break;
                case "hgt":
                    if (value.charAt(value.length() - 2) == 'c')
                    {
                        int j = Integer.parseInt(value.substring(0, value.length() - 2));
                        if (j < 150 || j > 193)
                        {
                            amountOfFields--;
                        }
                    } else if (value.charAt(value.length() - 2) == 'i')
                    {
                        int k = Integer.parseInt(value.substring(0, value.length() - 2));
                        if (k < 59 || k > 76)
                        {
                            amountOfFields--;
                        }
                    }
                    else
                        amountOfFields--;

                    break;
                case "hcl":
                    if (!value.matches("#[a-fA-F0-9]{6}"))
                    {
                        amountOfFields--;
                    }
                    break;
                case "ecl":
                    String[] variants = new String[]{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
                    if (!Arrays.asList(variants).contains(value))
                    {
                        amountOfFields--;
                    }
                    break;
                case "pid":
                    if (!value.matches("^\\d{9}$"))
                    {
                        amountOfFields--;
                    }
                    break;
                case "cid":
                    amountOfFields--;
            }
            amountOfFields++;
        }
        return amountOfFields;
    }


    public static void main(String[] args)
    {
        Puzzle4 puzzle4 = new Puzzle4();
        puzzle4.findValidPassports();
    }
}
