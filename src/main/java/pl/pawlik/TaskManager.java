package pl.pawlik;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class TaskManager {
    public static void displayoptions() {
        System.out.println(ConsoleColors.BLUE + "Please select an option:");
        System.out.println(ConsoleColors.RESET + "add");
        System.out.println("remove");
        System.out.println("list");
        System.out.println("exit");
    }


    public static void removetask() {
        try {
            Path taskspath = Paths.get("tasks.csv");
            List<String> lines = Files.readAllLines(taskspath);
            while (true) {
                System.out.println("Please select number to remove");
                Scanner scan = new Scanner(System.in);
                try {
                    String input = scan.nextLine();
                    int taskindex = Integer.parseInt(input);
                    if (taskindex >= 0 && taskindex < lines.size()) {
                        lines.remove(taskindex);
                        Files.write(taskspath, lines);
                        break;
                    } else {
                        System.out.println("Invalid input");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input");
                }
            }
        } catch (IOException e) {
            System.out.println("File not found");
        }

    }


    public static void addtask() {
        Path taskspath = Paths.get("tasks.csv");
        Scanner scan = new Scanner(System.in);

        try {

            StringBuilder sb = new StringBuilder();
            System.out.println("Please add task description: ");
            String description = scan.nextLine();
            sb.append(description + ", ");
            System.out.println("Please add task due date: ");
            String date = scan.nextLine();
            sb.append(date + ", ");
            System.out.println("Is your task important? true/false: ");
            String truefalse = scan.nextLine();
            sb.append(truefalse);
            List<String> outlist = new ArrayList<>();
            outlist.add(sb.toString());
            Files.write(taskspath, outlist, StandardOpenOption.APPEND);
        } catch (IOException e) {

            System.out.println("Nie można zapisać");
        }
    }


    public static void list() {
        try {
            File tasks = new File("tasks.csv");
            Scanner scan = new Scanner(tasks);
            int rowcount = 0;
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                rowcount++;
            }
            scan.close();
            String tasksArray[][] = new String[rowcount][3];
            Scanner scan2 = new Scanner(tasks);
            int rowindex = 0;
            while (scan2.hasNextLine()) {
                String line2 = scan2.nextLine();
                tasksArray[rowindex] = line2.split(",");
                rowindex++;
            }
            scan2.close();
            for (int i = 0; i < tasksArray.length; i++) {
                System.out.print(i + ": ");
                for (int j = 0; j < tasksArray[i].length; j++) {
                    System.out.print(tasksArray[i][j] + " | ");
                }
                System.out.println();
            }


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }


    }


    public static void main(String[] args) {

        while (true) {
            displayoptions();
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            switch (input) {
                case "add":
                    addtask();
                    break;
                case "remove":
                    removetask();
                    break;
                case "list":
                    list();
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Invalid command. Please try again.");
                    break;
            }
        }


    }


}
