package com.unicorn;
import java.util.*;
/**@description
 *A program to calculate the GPA of university students whose uses a GPA system of 4.0 or 5.0.
 * @author alexander
 * @date 18/07/2021
 */
public class Main {
    public static void main(String[] args) {
        //This part of the code displays the menu panel where the user enters the the GPA system he\she school runs
        //and also the number of courses taken in the semester for the GPA is to be calculated from.
        Scanner input = new Scanner(System.in);
        //receives input for GPA system
        System.out.println("================= GPA CALCULATOR ===============================");
        System.out.println(" Enter the GPA system your school runs:\n For 4.0 GPA system enter: 4 \t\tFor 5.0 GPA system enter: 5 ");
        System.out.println("================================================================");
        int gpa = input.nextInt();
        //this part works on code in parenthesis and throws and exceptional error when GPA system entered is not 4 or 5.
        try{
        if(gpa==4 || gpa==5)
        //displays input entered
        System.out.println(" GPA system: " + gpa);
        }catch (InputMismatchException ex)
        { //this stops the code and displays error message for inputs not 4 or 5'
            System.out.println("Wrong input! this system calculates GPA for only the 4 and 5 GPA system. ");

        }
        //this part receives the number of courses registered by user
        System.out.println("================================================================");
        System.out.println(" Enter number of Courses: ");
        int cos = input.nextInt();
        //displays number of courses entered by user.
        System.out.println(" Number of Courses: " + cos);
        System.out.println("================================================================");
        enquiryAndGPACalculation(cos,gpa);

    }
    //this method performs all the necessary inputs:grades,credit hours,course codes. and calculations involved in calculating GPA.
    //It also receives two parameters of integer type containing information about number of courses and GPA system used.
    public static void enquiryAndGPACalculation(int c,int g){
        ArrayList<String> grades= new ArrayList<>();
        ArrayList<String> names= new ArrayList<>();
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Double> points = new ArrayList<>();
        ArrayList<Double> gradePoints = new ArrayList<>();
        double a;
        Scanner in = new Scanner(System.in);
        String name,ch;
        double qp = 0.0;
        //performs commutation based on only the GPA 4.0 system.
        if(g==4) {
            for (int j = 0;j<c;j++) {
                //this part accepts inputs from the user about grading system and calls an exception error if a wrong grading system is used.
                try {
                    System.out.println(" Enter the course code of course. ");
                    name = courseName(in);
                    System.out.println(name);
                    System.out.println(" Enter grade ");
                    ch = in.next();
                    //checks grading input entered and assigns appropriate grade point to it.
                    switch (ch) {
                        case "A" -> qp = 4.0;
                        case "B+" -> qp = 3.5;
                        case "B" -> qp = 3.0;
                        case "C+" -> qp = 2.5;
                        case "C" -> qp = 2.0;
                        case "D+" -> qp = 1.5;
                        case "D" -> qp = 1.0;
                        case "E" -> qp = 0.5;
                        case "F" -> qp = 0.0;
                        default -> System.out.println("Error wrong Grade Parameter");
                    }
                    //an arraylist of course codes entered
                    names.add(name);
                    //an arraylist of course grades entered
                    grades.add(ch);
                    //an arraylist of grade points of grades entered
                    points.add(qp);
                } catch (InputMismatchException ex) {
                    System.out.println("Try again. (" + "Incorrect input: an uppercase alphabet is required)");
                }
            }
            creditHours(c, list, in,names);
        }
        //performs commutation based on only the GPA 5.0 system.

        if(g==5) {
            for (int j = 0;j<c;j++) {
                //this part accepts inputs from the user about grading system and calls an exception error if a wrong grading system is used.
                try {
                    System.out.println(" Enter the course code of course. ");
                    name =courseName(in);
                    System.out.println(name);
                    System.out.println(" Enter grade ");
                    ch = in.next();
                    //checks grading input entered and assigns appropriate grade point to it.
                    switch (ch) {
                        case "A" -> qp = 5.0;
                        case "B+" -> qp = 4.5;
                        case "B" -> qp = 4.0;
                        case "C+" -> qp = 3.5;
                        case "C" -> qp = 3.0;
                        case "D+" -> qp = 2.5;
                        case "D" -> qp = 2.0;
                        case "E" -> qp = 1.0;
                        case "F" -> qp = 0.0;
                        default -> System.out.println("Error wrong Grade Parameter");
                    }
                    //an arraylist of course codes entered
                    names.add(name);
                    //an arraylist of course grades entered
                    grades.add(ch);
                    //an arraylist of grade points of grades entered
                    points.add(qp);
                } catch (InputMismatchException ex) {
                    System.out.println("Try again. (" + "Incorrect input: an uppercase alphabet is required)");
                }
            }
            creditHours(c, list, in,names);
        }
        System.out.println("================================================================");
        System.out.println("Courses === Grades == Credit Hours == Grade Points ========================");
        //displays the course code, grades,credit hours and grade points
        int i = 0;
        while (i<grades.size()) {
            String get,courseNames;
            int hours;
            double grade_Points;
            //prints out the course code of all courses entered one after the other
            courseNames = names.get(i);
            System.out.print(courseNames);
            //prints out the grades of all courses entered one after the other
            System.out.print("\t\t\t");
            get = grades.get(i);
            System.out.print("  " + get + "   ");
            //prints out the credit hours of all courses entered one after the other
             hours = list.get(i);
            System.out.print("       " + hours + "\t\t");
            //prints out the grade points of all courses entered one after the other
            grade_Points = points.get(i);
            System.out.print("       " + grade_Points);
            System.out.println();
            //
            a = list.get(i) * points.get(i);
            gradePoints.add(a);
            i++;
        }
        int sumOfHour = sumOfCreditHours(list);
        double sumOfPoints =sumOfGradePoints(gradePoints);
        double GPA = calculateGPA(sumOfHour,sumOfPoints);
        System.out.println("================================================================");
        System.out.print("Total credit hours: "+ sumOfHour + "\t\t");
        System.out.println("GPA: " + GPA );
    }
    //method to receive credit hours of courses entered from user.
    private static void creditHours(int c, ArrayList<Integer> list, Scanner in,ArrayList<String> NAME) {
        System.out.println(" Enter the credit hours for each course ");
        int hour;
        for (int k = 0; k < c; k++) {
            String course_Name = NAME.get(k);
            System.out.print(course_Name + "\t");
            hour = in.nextInt();
            System.out.println();
            list.add(hour);
        }
    }
    //method to calculate total credit hours.
    public static int sumOfCreditHours(ArrayList<Integer> sum){
        int l,o;
        o = sum.get(0) + sum.get(1);
        for ( l = 2;l<sum.size();l++) {
         o += sum.get(l);
        }
        return o;
    }
    //method to calculate total grade points.
    public static double sumOfGradePoints(ArrayList<Double> Sum){
        double m;
        m =  Sum.get(0) + Sum.get(1);
        for ( int k = 2;k<Sum.size();k++) {
            m += Sum.get(k);
        }
        return m;
    }
    //method to calculate GPA.
    public static double calculateGPA(int credit,double points) {
        return points/credit;
    }
    //methode to receive course code.
    public static String courseName(Scanner input) {
        return input.next();
    }

}

