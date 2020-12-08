package Puzzles;

import java.util.ArrayList;
import java.util.List;

public class Puzzle7
{
    Utility utility;
    List<String> rules;
    List<String> checked;

    public Puzzle7()
    {
        utility = new Utility();
        rules = utility.readFile("#7 luggageRules.txt",true);
        checked = new ArrayList<>();
        checked.add("shiny gold");
    }

    public int findAmountOfBagsThatCanHoldShinyGold()
    {
        int checkedPointer = 0;

        while(checkedPointer!=checked.size())
        {
            int size = checked.size();
            for (int i = checkedPointer; i < size; i++)
            {
                for (String rule : rules)
                {
                    String color = containsChecked(rule, checked.get(i));
                    if (!color.isEmpty()&&!checked.contains(color))
                        checked.add(color);
                }
            }
            checkedPointer = size;
        }

        return checked.size()-1; //Shiny gold is in list, but shouldn't be included.
    }

    public String containsChecked(String rule, String checked)
    {
        String color = rule.substring(0, rule.indexOf("bags") - 1);
        String colorsInRuleString = rule.substring(rule.indexOf("contain")+8);
        String[] colors = colorsInRuleString.split(", ");

        for (String ruleColor:colors)
        {
            String col = ruleColor.substring(2, ruleColor.indexOf("bag")-1);
            if(col.equals(checked))
                return color;
        }

        return "";
    }

    public int containsBags(String bag)
    {
        int amountOfBags = 0;
        for(String rule:rules)
        {
            String color = rule.substring(0, rule.indexOf("bags") - 1);

            if(color.equals(bag))
            {
                String colorsInRuleString = rule.substring(rule.indexOf("contain")+8);
                String[] colors = colorsInRuleString.split(", ");

                for (String ruleColor:colors)
                {
                    if(ruleColor.equals("no other bags."))
                        return 0;

                    String col = ruleColor.substring(2, ruleColor.indexOf("bag")-1);
                    int amount = Integer.parseInt(ruleColor.substring(0,1));
                    amountOfBags += amount + containsBags(col) * amount;
                }
                return amountOfBags;
            }
        }

        return 0;
    }

    public static void main(String[] args)
    {
        Puzzle7 puzzle7 = new Puzzle7();
        //System.out.println("Amount of bags that can hold shiny gold is " + puzzle7.findAmountOfBagsThatCanHoldShinyGold());
        System.out.println("Amount of bags required in a shiny gold bag is " + puzzle7.containsBags("shiny gold"));
    }
}
