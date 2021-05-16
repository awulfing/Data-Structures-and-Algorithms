package com.company;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class Main {
    public static void main(String[] args){
        System.out.print("");
        String name = "input.txt";
        String dict = "words.txt";
        Main out = new Main();
        ArrayList<String> temp = out.readInput(name);
        String text[] = temp.toArray(new String[temp.size()]);
        String newArray[] = out.spellCheck(dict,text);
        String output = "";
        for(int i = 0; i < newArray.length; i++) {
            output = output + " " + newArray[i];
        }
        System.out.println(output);
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("input_correction.txt"), "utf-8"))) {
            writer.write(output);
        }
        catch (IOException ex) {
            // idk
        }


    }
    ///////////////////////////////////////////////////////////////////////////////////////
    public String replace(String bad, String dict){
        String replace = "";
        int size = bad.length();
        int j = 1;
        ArrayList<String> options = new ArrayList<String>();
        Scanner scanner=null;
        try {
            scanner = new Scanner(new File(dict));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            String nextToken = scanner.next();
            if (nextToken.charAt(0) == bad.charAt(0)) {
                if (nextToken.length() == size ||nextToken.length() == size+1 ||nextToken.length() == size-1){
                    if (nextToken.charAt(nextToken.length()-1) == bad.charAt(size-1)) {
                        if (nextToken.charAt(nextToken.length() - 2) == bad.charAt(size - 2) || nextToken.charAt(1) == bad.charAt(1)) {
                            System.out.println(j + ". " + nextToken);
                            options.add(nextToken);
                            j++;
                            if(j > 5){
                                break;
                            }
                        }
                        else if (size < 3) {
                            System.out.println(j + ". " + nextToken);
                            options.add(nextToken);
                            j++;
                        }
                    }
                }
            }
        }
        System.out.println(j + ". Differnt Option");
        Scanner myObj = new Scanner(System.in);
        String user = myObj.nextLine();
        if(Integer.parseInt(user) == j){
            System.out.println("please input new option");
            Scanner newS = new Scanner(System.in);
            replace = newS.nextLine();
        }
        else{
            replace = options.get(Integer.parseInt(user)-1);
        }
        System.out.println(replace);
        return replace;
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    public String[] spellCheck(String dict, String [] array){
        Scanner scanner=null;
        boolean found = false;

        for(int i = 0; i < array.length; i++) {
            try {
                scanner = new Scanner(new File(dict));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            while (scanner.hasNextLine()) {
                String nextToken = scanner.next();
                if (nextToken.equalsIgnoreCase(array[i])) {
                    found = true;
                    break;
                }
            }
            if(found == false) {//SET MISPELLED WORD EQUAL TO RESULT OF REPLACE METHOD

                System.out.println("Please input the number for the word you meant for " + array[i]);
                array[i] = replace(array[i],dict);
                System.out.println("");
            }
            found = false;
        }
        return array;
    }
    ///////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<String> readInput(String name){
        Scanner s = null;
        try {
            s = new Scanner(new File(name));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();
        return list;
    }
}
