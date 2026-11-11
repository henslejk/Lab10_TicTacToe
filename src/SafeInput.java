import java.util.Scanner;

public class SafeInput {

    /**
     *
     * @param pipe   a Scanner opened to read from System.in
     * @param prompt prompt for the user
     * @return a String response that is not zero length
     */


    // PART A
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = ""; // Set this to zero length. Loop runs until it isn't
        do {
            IO.print("\n" + prompt + ": "); // show prompt add space
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;

    }

    // Part B
    public static int getInt(Scanner pipe, String prompt) {
        int retValue = 0;
        boolean valid = false;
        String trash = "";

        do {
            IO.print("\n" + prompt + ": ");

            if (pipe.hasNextInt()) {
                retValue = pipe.nextInt();
                pipe.nextLine();
                valid = true;
            } else {

                trash = pipe.nextLine();
                pipe.nextLine();
                IO.print("Enter a valid integer not " + trash + ". ");
            }
        } while (!valid);
        return retValue;
    }

    // Part C
    public static double getDouble(Scanner pipe, String prompt) {

        double retValue = 0.0;
        boolean valid = false;
        String trash = "";

        do {
            IO.print("\n" + prompt + ": ");
            if (pipe.hasNextDouble()) {
                retValue = pipe.nextDouble();
                pipe.nextLine();
                valid = true;
            } else {
                trash = pipe.nextLine();
                pipe.nextLine();
                IO.print("Enter a valid number not " + trash + ". ");
            }

        } while (!valid);
        return retValue;


    }

    // Part D
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {

        int retValue;
        boolean valid;


        do {
            retValue = getInt(pipe, prompt + "[" + low + "-" + high + "]");
            valid = retValue >= low && retValue <= high;

            if (!valid) {
                IO.print("Please enter a value from " + low + " to " + high + ".");
            }
        } while (!valid);
        return retValue;
    }

    // Part E

    public static double getRangedDouble(Scanner pipe, String prompt, double low, double high) {
        double retValue;
        boolean valid;

        do {
            retValue = getDouble(pipe, prompt + "[" + low + "-" + high + "]");

            valid = retValue >= low && retValue <= high;
            if (!valid) {
                IO.print("Please enter a value from " + low + " to " + high + ".");
            }
        } while (!valid);
        return retValue;

    }


    //Part F
    public static boolean getYNconfirm (Scanner pipe, String prompt) {
        String response;

        do{
            IO.print ("\n" + prompt + "n [Y/N]: ");
            response = pipe.nextLine().trim();

            if (response.equals("Y")) {
                return true;
            }
            else if(response.equals("N")) {
                return false;
            }
            else {
                IO.print("Please enter Y or N not");
            }
        } while ( true);
    }

    //Part G
    public static String getRegExString (Scanner pipe, String prompt, String regEx) {
        String response;
        boolean valid;
        do {
            IO.print("\n" + prompt + ": ");
            response = pipe.nextLine();

            valid = response.matches(regEx);

            if (!valid) {
                IO.println("Invalid input. Please try again.");

            }
        } while (!valid);
        return response;
    }
    public static void prettyHeader(String msg)
    {
        int width = 60;
        int innerWidth = width - 6;

        int leftSpaces = (innerWidth - msg.length()) / 2;
        int rightSpaces = innerWidth - msg.length() - leftSpaces;

        for (int i = 0; i < width; i++)
        {
            IO.print("*");
        }
        IO.println();

        IO.print("***");

        for (int i = 0; i < leftSpaces; i++)
        {
            IO.print(" ");
        }

        IO.print(msg);

        for (int i = 0; i < rightSpaces; i++)
        {
            IO.print(" ");
        }

        IO.println("***");

        for (int i = 0; i < width; i++)
        {
            IO.print("*");
        }
        IO.println();
    }
    }





