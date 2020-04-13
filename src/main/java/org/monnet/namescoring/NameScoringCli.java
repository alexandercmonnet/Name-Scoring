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

import org.monnet.namescoring.entity.LinearUpperCaseLetterScoreMap;
import org.monnet.namescoring.entity.Name;
import org.monnet.namescoring.exception.UnsupportedCharacterException;
import org.monnet.namescoring.service.NameScoringService;
import org.monnet.namescoring.service.implmentation.AscFirstNameSortingServiceImpl;
import org.monnet.namescoring.service.implmentation.FirstNameScoringServiceImpl;

/**
 * This main class is the CLI entry point for the name scoring application.
 */
public class NameScoringCli {

    private static final String FILE_FLAG = "--file";
    private static final String HELP_FLAG = "--help";

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

                String filePath = argsList.get(argsList.indexOf(FILE_FLAG) + 1);
                Path namesListFilePath = Paths.get(filePath);
                
                if(Files.exists(namesListFilePath)) {
                    List<Name> nameList = getNamesFromFile(namesListFilePath.toFile());
                    outputString = runScoringWithFirstNameAlphabeticOrder(nameList);
                } else {
                    outputString = String.format("Error: %s does not exist.",  namesListFilePath.toString()); 
                }
            break;

            case PRINT_HELP:

                outputString = String.format("The valid commands for this program are as follows:\n%s | prints help output.\n%s | points to a csv list of names to score.", HELP_FLAG, FILE_FLAG);
            break;

            //BAD ARGS and undefined have the same output
            case REPORT_BAD_ARGS:
            default:

                outputString = String.format("The arguments you submitted are invalid. Run the program again with %s to see valid options. Invalid arguments: %s", HELP_FLAG, argsList);
        }

        System.out.println(outputString);
    }

    /**
     * Run the scoring application with the following attributes:
     * 1. names are sorted alphabetically by first name in ascending order
     * 2. letters in names are scored linearly starting with A = 1 
     * @param nameList the list of names to sort and score
     * @return The output string containing the score
     */
    private static String runScoringWithFirstNameAlphabeticOrder(List<Name> nameList) {

        NameScoringService scoringService = new FirstNameScoringServiceImpl(new LinearUpperCaseLetterScoreMap());
        NameScoring nameScoring = new NameScoring(scoringService, new AscFirstNameSortingServiceImpl());
        
        
        String scoreString = "";
        try {
            Integer score = nameScoring.calculateScore(nameList);
            scoreString = "List score is: " + score;
        } catch (UnsupportedCharacterException e) {
            scoreString = "Error: You entered an unsupported character.\n" + e.getMessage();
        }
        return scoreString;
    }

    /**
     * Read and understand the CLI arguments and direct the application's actions 
     *  based on the arguments.
     */
    protected static Action digestArgs(List<String> argsList) {
        
        Action actionToTake = Action.REPORT_BAD_ARGS;

        if(argsList.contains(HELP_FLAG)) {
            actionToTake = Action.PRINT_HELP;
        } else if(argsList.contains(FILE_FLAG)) {

            Integer indexOfFileArg = argsList.indexOf(FILE_FLAG);
            if(indexOfFileArg < argsList.size() - 1){
                actionToTake = Action.PARSE_NAMES_FROM_FILE;
            } 
        } 

        return actionToTake;
    }

    /**
     * A small function to extract names from a comma-delimited file and to remove extraneous "" from
     *  around the names.
     * @param file
     * @return
     */
    private static List<Name> getNamesFromFile(File file) {
        List<Name> names = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line = "";    
            while((line = reader.readLine()) != null) {
			    for(String name : line.split(",")) {
                    String nameWithoutSurroundingQuotes = name.substring(1, name.length() - 1);
                    names.add(new Name(nameWithoutSurroundingQuotes));
                } 
            }

            reader.close();
		} catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to read from file. ");
            names = new ArrayList<>();
        }

        return names;
    }
}

