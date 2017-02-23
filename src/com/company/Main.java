package com.company;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
	    Scanner input = new Scanner(System.in);
        Scanner input2 = new Scanner(System.in);
        System.out.println("Insert first number.");
        int n1;
        while(true)
        {
            try
            {
                n1 = input.nextInt();
                if(n1 < 0)
                    throw new Exception();
                break;
            }
            catch(Exception e)
            {
                System.out.println("That is not a valid input.");
            }
        }
        System.out.println("Insert second number.");
        int n2;
        while(true)
        {
            try
            {
                n2 = input.nextInt();
                if(n2 < 0)
                    throw new Exception();
                break;
            }
            catch(Exception e)
            {
                System.out.println("That is not a valid input.");
            }
        }
        System.out.println("Recursive or Iterative?");
        String in = "";
        while(!(in.equalsIgnoreCase("recursive") || in.equalsIgnoreCase("iterative")))
        {
            in = input2.nextLine();
            if(!(in.equalsIgnoreCase("recursive") || in.equalsIgnoreCase("iterative")))
                System.out.println("This is not a valid input.");
        }
        int gcf;
        if(in.equalsIgnoreCase("recursive"))
            gcf = recursive(n1, n2);
        else
            gcf = iteratively(n1, n2);
        System.out.println("The greatest common divisor is " + gcf + ".");
    }

    public static int recursive(int n1, int n2)
    {
        if(n1 < n2)
            return recursive(n2, n1);
        if(n1 == 0)
            return n2;
        if(n2 == 0)
            return n1;
        return recursive(n2, n1 % n2);
    }

    public static int iteratively(int n1, int n2)
    {
        if(n1 < n2)
        {
            int temp = n1;
            n1 = n2;
            n2 = temp;
        }
        while(n1 != 0 && n2 != 0)
        {
            int temp = n2;
            n2 = n1 % n2;
            n1 = temp;
        }
        if(n1 == 0)
            return n2;
        else
            return n1;
    }
}
