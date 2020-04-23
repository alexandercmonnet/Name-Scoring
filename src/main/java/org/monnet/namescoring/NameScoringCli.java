package org.monnet.namescoring;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.exception.UnsupportedCharacterException;
import org.monnet.namescoring.pointmap.CharacterScoreMap;
import org.monnet.namescoring.pointmap.LinearUpperCaseLetterScoreMap;
import org.monnet.namescoring.service.ScoringService;
import org.monnet.namescoring.service.SortingService;
import org.monnet.namescoring.service.implementation.AlphabeticFirstNameSortingServiceImpl;
import org.monnet.namescoring.service.implementation.FirstNameScoringServiceImpl;

/**
 * This main class is the CLI entry point for the name scoring application.
 */
public class NameScoringCli {

    private static final String ARG_FLAG_FILE = "--file";
    private static final String ARG_FLAG_HELP = "--help";

    /**
     * Enum list of actions to take based
     *  on the CLI arguments
     */
    protected enum Action {
        PARSE_NAMES_FROM_FILE,
        PRINT_HELP,
        REPORT_BAD_ARGS
    }

    public static void main(String[] args) {

        List<String> argsList = Arrays.asList(args);
        Action actionToTake = digestArgs(argsList);
        final String outputString;

        switch(actionToTake) {
            case PARSE_NAMES_FROM_FILE:
                String filePath = argsList.get(argsList.indexOf(ARG_FLAG_FILE) + 1);
                Path namesListFilePath = Paths.get(filePath);

                boolean wasFileSuccessfullyRead = false;
                String scoreString = "";
                
                if(Files.exists(namesListFilePath)) {

                    List<Name> nameList = new ArrayList<>();

                    try {
                        nameList = getFirstNamesFromFile(namesListFilePath.toFile());
                        wasFileSuccessfullyRead = true;
                    } catch (IOException ioException) {
                        scoreString = String.format("Error: Unable to read the file: %s",  namesListFilePath.toString()); 
                    }
                    
                    if(wasFileSuccessfullyRead) {
                        scoreString = calculateScoreWithFirstNamesAndLinearAlphabeticScore(nameList);
                    }
                } else {
                    scoreString = String.format("Error: The file %s does not exist.",  namesListFilePath.toString()); 
                }

                outputString = scoreString; 
            break;

            case PRINT_HELP:
                outputString = String.format("The valid commands for this program are as follows:\n%s | prints help output.\n%s | followed by a file path to a list of names to score.", ARG_FLAG_HELP, ARG_FLAG_FILE);
            break;

            //BAD ARGS and undefined have the same output
            case REPORT_BAD_ARGS:
            default:
                outputString = String.format("The arguments you submitted are invalid. Run the program again with %s to see valid options. Invalid arguments: %s", ARG_FLAG_HELP, argsList);
            break;
        }

        System.out.println(outputString);
    }

    /**
     * Run the scoring application with the following attributes:
     * 1. names are sorted alphabetically by first name in ascending order
     * 2. letters in names are scored linearly starting with A = 1 
     * @param namesListFilePath the path to the list of names to sort and score
     * @return The output string containing the score
     */
    private static String calculateScoreWithFirstNamesAndLinearAlphabeticScore(List<Name> nameList) {
        CharacterScoreMap linearUpperCaseLetterScoreMap = new LinearUpperCaseLetterScoreMap();
        ScoringService firstNameScoringImpl = new FirstNameScoringServiceImpl(linearUpperCaseLetterScoreMap);
        SortingService alphabeticFirstNameSortingImpl = new AlphabeticFirstNameSortingServiceImpl();
        NameScoring nameScoring = new NameScoring(firstNameScoringImpl, alphabeticFirstNameSortingImpl);

        String scoreString;
        try {
            Integer score = nameScoring.calculateScore(nameList);
            scoreString = score.toString();
        } catch (UnsupportedCharacterException unsupportedCharacterException) {
            scoreString = "Error: You entered an unsupported character.\n" + unsupportedCharacterException.getMessage();
        }

        return scoreString;
    }

    /**
     * Read and understand the CLI arguments and direct the application's actions 
     *  based on the arguments.
     */
    protected static Action digestArgs(List<String> argsList) {
        
        Action actionToTake = Action.REPORT_BAD_ARGS;

        if(argsList.contains(ARG_FLAG_HELP)) {
            actionToTake = Action.PRINT_HELP;
        } else if(argsList.contains(ARG_FLAG_FILE)) {

            Integer indexOfFileArg = argsList.indexOf(ARG_FLAG_FILE);
            if(indexOfFileArg < argsList.size() - 1) {
                actionToTake = Action.PARSE_NAMES_FROM_FILE;
            } 
        } 

        return actionToTake;
    }

    /**
     * A small function to extract first names from a comma-delimited file and to remove extraneous "" from
     *  around the names.
     * @param file
     * @return
     * @throws IOException
     */
    private static List<Name> getFirstNamesFromFile(File file) throws IOException {
        List<Name> names = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = "";    
        while((line = reader.readLine()) != null) {
            for(String nameString : line.split(",")) {
                String nameWithoutSurroundingQuotes = nameString.substring(1, nameString.length() - 1);
                Name name = Name.builder().firstName(nameWithoutSurroundingQuotes).build();
                names.add(name);
            } 
        }

        reader.close();

        return names;
    }

    /**
     * A small function to extract first names from a comma-delimited file and to remove extraneous "" from
     *  around the names.
     * @param file
     * @return
     * @throws IOException
     */
    private static List<Name> getFirstAndLastNamesFromFile(File file) throws IOException {
        List<Name> names = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line = "";    
        while((line = reader.readLine()) != null) {
            for(String nameString : line.split(",")) {
                String nameWithoutSurroundingQuotes = nameString.substring(1, nameString.length() - 1);
                String[] nameSplitBySpace = nameWithoutSurroundingQuotes.split(" ");

                Name name = Name.builder().firstName(nameSplitBySpace[0]).lastName(nameSplitBySpace[0]).build();
                names.add(name);
            } 
        }

        reader.close();

        return names;
    }
}

