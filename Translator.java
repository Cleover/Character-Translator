import java.util.Scanner;

import java.io.*;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * @author Cleo, Github: Cleover, Discord: Cleo#1003
 **/

// Console Color Codes Taken From
// https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

public class Translator {

  public static String selectedFile = "";
  public static boolean allowContinue = false;

  public static boolean bottomToTop = true;
  public static boolean includeBottomLine = true;

  public static String defaultDir = "./lang/";
  public static String defaultExt = "lang";

  public static void main(String[] args) {
    flushScreen();
    menu();
  }

  public static void menu() {
    Scanner input = new Scanner(System.in);
    String selectedFileMessage = "None Selected";
    if (!selectedFile.equals("")) {
      selectedFileMessage = selectedFile;
    } 

    String continueMessage = " (\033[1;91mRequires File To Be Set\033[0m)";
    if (allowContinue)
      continueMessage = "";
    System.out.println("\033[1;37mWelcome to the \033[1;94mT\033[1;38;5;206mr\033[1;37ma\033[1;38;5;206mn\033[1;94ms\033[1;37mlator!\033[0m\n");
    System.out.println("\033[4;36mPlease select from the following options:\033[0m\n");
    System.out.println("\033[1;37m[1]\033[0m Select The Translator File (\033[0;33m" + selectedFileMessage + "\033[0m)");
    System.out.println("\033[1;37m[2]\033[0m Translate A String" + continueMessage);
    System.out.println("\033[1;37m[3]\033[0m Edit Translator Settings");
    System.out.println("\033[1;37m[4]\033[0m \033[1;91mExit\033[0m\n");
    System.out.print("\033[1;37mEnter your choice:\033[0m \033[1;32m");
    String choice = input.next();
    System.out.print("\033[0m");
    flushScreen();
    switch (choice) {
      case "1":
        loadFile(true);
        break;
      case "2":
        if (allowContinue) {
          loadFile(false);
        } else {

          System.out.println("\033[41m\033[1;97mYou must first select a file!\033[0m\n");
          menu();
        }
        break;
      case "3":
        flushScreen();
        settings();
        break;
      case "4":
        System.out.println("Thank you for using the Translator!");
        System.exit(0);
        break;
      default:
        System.out.println("\033[41m\033[1;97mInvalid choice!\033[0m\n");
        menu();
    }
  }

  public static void settings() {
    Scanner input = new Scanner(System.in);
    System.out.println("\033[1;37mWelcome to the\033[0m \033[1;94mT\033[1;38;5;206mr\033[1;37ma\033[1;38;5;206mn\033[1;94ms\033[1;37mlator\033[1;37m Settings!\033[0m\n");
    System.out.println("\033[4;36mPlease select from the following options:\033[0m\n");
    System.out.println("\033[1;37m[1]\033[0m Write from bottom to top (\033[0;33m" + bottomToTop + "\033[0m)");
    System.out
        .println("\033[1;37m[2]\033[0m Include line below final output (\033[0;33m" + includeBottomLine + "\033[0m)");
    System.out.println("\033[1;37m[3]\033[0m Change the default directory (\033[0;33m" + defaultDir + "\033[0m)");
    System.out.println("\033[1;37m[4]\033[0m Change the default file extension (\033[0;33m" + defaultExt + "\033[0m)");
    System.out.println("\033[1;37m[5]\033[0m Return To \033[1;31mMain Menu\033[0m\n");
    System.out.print("\033[1;37mEnter your choice:\033[0m \033[1;32m");
    String choice = input.next();
    System.out.print("\033[0m");
    flushScreen();
    switch (choice) {
      case "1":
        bottomToTop = !bottomToTop;
        settings();
        break;
      case "2":
        includeBottomLine = !includeBottomLine;
        settings();
        break;
      case "3":
        System.out.print("Enter the new default directory: \033[1;32m");
        defaultDir = input.next();
        System.out.print("\033[0m");
        flushScreen();
        settings();
        break;
      case "4":
        System.out.print("Enter the new default file extension: \033[1;32m");
        defaultExt = input.next();
        System.out.print("\033[0m");
        flushScreen();
        settings();
        break;
      case "5":
        menu();
        break;
      default:
        System.out.println("\033[41m\033[1;97mInvalid choice!\033[0m\n");
        settings();
    }
  }

  public static void loadFile(boolean isLoading) {
    Scanner scan = new Scanner(System.in);

    boolean foundFile = false;

    try {

      if (isLoading) {

        System.out.println(
            "Please make sure a \033[1;31mvalid\033[0m \"\033[1;97m."
                + defaultExt
                + "\033[0m\" file is within the \"\033[1;97m"
                + defaultDir
                + "\033[0m\" directory.\n\nIf you have verified a \033[1;31mvalid\033[0m \"\033[1;97m."
                + defaultExt
                + "\033[0m\" file exists, please enter its name minus the \"\033[1;97m."
                + defaultExt
                + "\033[0m\" extension.\n\n\033[0;97mExample Response:\033[0m \"\033[0;33mstars\033[0m\" | Attempts to load: \"\033[0;33m"
                + defaultDir
                + "stars."
                + defaultExt
                + "\033[0m\"\n\n> Type \"\033[1;96mmenu\033[0m\" to return to the menu\n\n> Type \"\033[1;96mcustom\033[0m\" to enter a custom file path\n");
        System.out.print("\033[1;37mInput:\033[0m \033[1;32m");
        String fileName = scan.nextLine();
        System.out.print("\033[0m");

        if (fileName.equals("menu")) {
          flushScreen();
          menu();
        }

        String path;

        if (fileName.equals("custom")) {
          System.out.print("\n\033[1;4;91mNote: Do not include quotation marks in paths\033[0m\n");
          System.out.print("\033[1;37mPlease enter the path to the file:\033[0m \033[1;32m");
          String customFile = scan.nextLine();
          System.out.print("\033[0m");
          String clearQuotes = customFile.replace("\'", "");
          path = clearQuotes;
        } else {
          path = defaultDir + fileName + "." + defaultExt;
        }

        // Attempt to load the file
        new Scanner(new File(path));

        selectedFile = path;
        allowContinue = true;

        flushScreen();
        menu();
      } else {
        Scanner fileRead = new Scanner(new File(selectedFile));
        foundFile = true;
        checkFileIntegrity(fileRead);
      }
    } catch (Throwable err) {
      System.out.println(err);
      if (!foundFile) {
        flushScreen();
        System.out.println("\033[41m\033[1;97mFile not found! Please enter a new file and try again\033[0m\n");
        loadFile(isLoading);
      } else {
        flushScreen();
        promptRestart(
            "The program has run into an error loading, or translating with the provided file.\nThis can occur when translating characters that are not defined in the language file.");
      }
    }
  }

  public static void checkFileIntegrity(Scanner fileRead) {

    HashMap<String, CharTranslateObject> charTranslate = new HashMap<String, CharTranslateObject>();

    ArrayList<String> tempCharList = new ArrayList<String>();

    String charIndexList[] = {};

    boolean insideCharGroup = false;

    int longestVerticalLength = 0;
    int tempLongestVerticalLength = 0;
    int longestHorizontalCharGroupLength = 0;

    while (fileRead.hasNext()) {
      String line = fileRead.nextLine();

      if (line.contains("{")) {
        insideCharGroup = true;

        String charSet = line.split("\\{")[0].trim();
        charIndexList = charSet.split("\\/");

      } else {
        if (insideCharGroup) {

          if (line.contains("}")) {

            for (String charIndex : charIndexList) {

              ArrayList<String> tempCharListClone = (ArrayList<String>) tempCharList.clone();

              CharTranslateObject charTranslateObject = new CharTranslateObject(tempCharListClone,
                  longestHorizontalCharGroupLength);

              charTranslate.put(charIndex, charTranslateObject);
            }

            tempCharList.clear();

            insideCharGroup = false;
            longestHorizontalCharGroupLength = 0;

            if (tempLongestVerticalLength > longestVerticalLength) {
              longestVerticalLength = tempLongestVerticalLength;
            }

            tempLongestVerticalLength = 0;

          } else {

            if (line.length() > longestHorizontalCharGroupLength) {
              longestHorizontalCharGroupLength = line.length();
            }

            tempCharList.add(line);
            tempLongestVerticalLength++;
          }
        }
      }
    }

    String userString = promptUserString();

    translate(charTranslate, userString, longestVerticalLength);
  }

  public static String promptUserString() {

    flushScreen();

    Scanner scan = new Scanner(System.in);

    System.out.println(
        "What is the string you would like to convert? (Example Response: \"Hello World\" -- Note: You do not need to include the \"\")");
    System.out.print("String: \033[1;32m");
    String userString = scan.nextLine();
    System.out.print("\033[0m");
    return userString;

  }

  public static void translate(HashMap<String, CharTranslateObject> charTranslate, String userString,
      int longestVerticalLength) {

    String[] finalStringArray = new String[longestVerticalLength];

    char userStringCharArray[] = userString.toCharArray();

    for (char userStringChar : userStringCharArray) {
      String currentChar = userStringChar + "";
      if (currentChar.equals(" ")) {
        currentChar = "_";
      }
      CharTranslateObject charObject = charTranslate.get(currentChar);

      if (charObject != null) {

        ArrayList<String> charList = charObject.charList;

        int longestCharLength = charObject.longestCharLength;

        for (int charPass = 0; charPass < longestVerticalLength; charPass++) {
          String charString;

          if (bottomToTop) {
            if (charPass < longestVerticalLength - charList.size()) {
              charString = "";
            } else {
              charString = charList.get(charPass - (longestVerticalLength - charList.size()));
            }
          } else {
            if (charPass < charList.size()) {
              charString = charList.get(charPass);
            } else {
              charString = "";
            }
          }

          if (charString.length() < longestCharLength) {
            charString = charString + " ".repeat(longestCharLength - charString.length());
          }

          if (finalStringArray[charPass] == null) {
            finalStringArray[charPass] = charString;
          } else {
            finalStringArray[charPass] = finalStringArray[charPass] + charString;
          }
        }
      }
    }

    flushScreen();

    System.out.println("\nOutput:\n");

    int lineLength = 0;

    for (String finalString : finalStringArray) {
      System.out.println(finalString);
      lineLength = finalString.length();
    }

    if (includeBottomLine) {
      System.out.println("-".repeat(lineLength));
    }

    System.out.println("\n");

    promptRestart("\033[1;91m--- Reached End of Program ---\033[0m");

  }

  public static void promptRestart(String message) {
    System.out.println(message + "\n\n\033[1;97mWould you like to return to the menu? (Y/N)\033[0m");
    System.out.print("Input: \033[1;92m");
    Scanner scan = new Scanner(System.in);
    String restart = scan.nextLine().toUpperCase();
    System.out.print("\033[0m");
    switch (restart) {
      case "Y":
        flushScreen();
        menu();
        break;
      case "N":
        System.out.println("Exiting...");
        break;
      default:
        flushScreen();
        System.out.println("\033[41m\033[1;97mInvalid choice!\033[0m\n");
        promptRestart(message);
        break;
    }
  }

  // Had to search on google how to flush the screen
  public static void flushScreen() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
} // Translator
