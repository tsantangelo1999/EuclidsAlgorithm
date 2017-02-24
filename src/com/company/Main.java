package com.company;

import java.util.Scanner;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
	    Scanner input = new Scanner(System.in);
        ArrayList<Integer> nums = new ArrayList<>();
        String in;
        while(true)
        {
            System.out.println("Input a number to add a number, or recursive, iterative, or prime factorization to find the greatest common divisor");
            in = input.nextLine();
            if(in.equalsIgnoreCase("recursive") || in.equalsIgnoreCase("iterative") || in.equalsIgnoreCase("prime factorization"))
                break;
            try
            {
                int n = Integer.parseInt(in);
                if(n < 0)
                    throw new Exception();
                nums.add(n);
            }
            catch(Exception e)
            {
                System.out.println("That is not a valid input.");
            }
        }
        int gcf;
        if(in.equalsIgnoreCase("recursive"))
            gcf = recursive(nums);
        else if(in.equalsIgnoreCase("iterative"))
            gcf = iteratively(nums);
        else
            gcf = primeFactorization(nums);
        System.out.println("The greatest common divisor is " + gcf + ".");
        /*
        OLD WAY WITH TWO NUMBERS (LAME)

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
        System.out.println("Recursive, Iterative, or Prime Factorization?");
        String in = "";
        while(!(in.equalsIgnoreCase("recursive") || in.equalsIgnoreCase("iterative") || in.equalsIgnoreCase("prime factorization")))
        {
            in = input2.nextLine();
            if(!(in.equalsIgnoreCase("recursive") || in.equalsIgnoreCase("iterative") || in.equalsIgnoreCase("prime factorization")))
                System.out.println("This is not a valid input.");
        }
        int gcf;
        if(in.equalsIgnoreCase("recursive"))
            gcf = recursive(n1, n2);
        else if(in.equalsIgnoreCase("iterative"))
            gcf = iteratively(n1, n2);
        else
            gcf = primeFactorization(n1, n2);
        System.out.println("The greatest common divisor is " + gcf + ".");
        */
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

    public static int primeFactorization(int n1, int n2)
    {
        ArrayList<Integer> n1List = getPrimeFactorization(n1);
        ArrayList<Integer> n2List = getPrimeFactorization(n2);
        ArrayList<Integer> common = new ArrayList<>();
        while(n1List.size() > 0 && n2List.size() > 0)
        {
            if(n1List.get(0) == n2List.get(0))
            {
                common.add(n1List.get(0));
                n1List.remove(0);
                n2List.remove(0);
            }
            else if(n1List.get(0) < n2List.get(0))
            {
                n1List.remove(0);
            }
            else
                n2List.remove(0);
        }
        int product = 1;
        for(int i : common)
            product *= i;
        return product;
    }

    public static ArrayList<Integer> getPrimeFactorization(int n)
    {
        int x = 2;
        ArrayList<Integer> list = new ArrayList<>();
        while(n != 1)
        {
            if((double)n / x == Math.floor(n / x))
            {
                list.add(x);
                n /= x;
            }
            else
            {
                x++;
            }
        }
        return list;
    }

    public static int recursive(ArrayList<Integer> n)
    {
        int gcf = 0;
        for(int i : n)
            gcf = recursive(gcf, i);
        return gcf;
    }

    public static int iteratively(ArrayList<Integer> n)
    {
        int gcf = 0;
        for(int i : n)
            gcf = iteratively(gcf, i);
        return gcf;
    }

    public static int primeFactorization(ArrayList<Integer> n)
    {
        ArrayList<Integer>[] num = new ArrayList[n.size()];
        for(int i = 0; i < num.length; i++)
            num[i] = getPrimeFactorization(n.get(i));
        ArrayList<Integer> primes = new ArrayList<>();
        top:
        while(true)
        {
            for(ArrayList<Integer> a : num)
                if(a.size() == 0)
                    break top;
            int number = num[0].get(0);
            boolean isPrime = true;
            for(int i = 1; i < num.length; i++)
            {
                if(num[i].get(0) < number)
                {
                    num[i].remove(0);
                    isPrime = false;
                }
                if(num[i].get(0) > number)
                {
                    num[0].remove(0);
                    continue top;
                }
            }
            if(isPrime)
            {
                primes.add(num[0].get(0));
                for(ArrayList<Integer> a : num)
                    a.remove(0);
            }
        }
        int product = 1;
        for(int i : primes)
            product *= i;
        return product;
    }
}
