package org.monnet.namescoring;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class NameScoringCliTest {

    @Test
    public void testParsingCliArgs_emptyArgs() throws Exception {
        final List<String> args = new ArrayList<>();
        final NameScoringCli.Action expectedAction = NameScoringCli.Action.REPORT_BAD_ARGS;
        final NameScoringCli.Action actualAction = NameScoringCli.digestArgs(args);

        assertEquals(actualAction, expectedAction);
    }

    @Test
    public void testParsingCliArgs_badArgsWithNoFile() throws Exception {
        final List<String> args = new ArrayList<>();
        args.add("--file");
        final NameScoringCli.Action expectedAction = NameScoringCli.Action.REPORT_BAD_ARGS;
        final NameScoringCli.Action actualAction = NameScoringCli.digestArgs(args);

        assertEquals(actualAction, expectedAction);
    }

    @Test
    public void testParsingCliArgs_parseNamesListFromFile() throws Exception {
        final List<String> args = new ArrayList<>();
        args.add("--file");
        args.add("file.txt");
        final NameScoringCli.Action expectedAction = NameScoringCli.Action.PARSE_NAMES_FROM_FILE;
        final NameScoringCli.Action actualAction = NameScoringCli.digestArgs(args);

        assertEquals(actualAction, expectedAction);
    }

    @Test
    public void testParsingCliArgs_printHelp() throws Exception {
        final List<String> args = new ArrayList<>();
        args.add("--help");
        final NameScoringCli.Action expectedAction = NameScoringCli.Action.PRINT_HELP;
        final NameScoringCli.Action actualAction = NameScoringCli.digestArgs(args);

        assertEquals(actualAction, expectedAction);
    }

    @Test
    public void testParsingCliArgs_printHelpWithOtherArgs() throws Exception {
        final List<String> args = new ArrayList<>();
        args.add("--file");
        args.add("file.txt");
        args.add("--help");
        final NameScoringCli.Action expectedAction = NameScoringCli.Action.PRINT_HELP;
        final NameScoringCli.Action actualAction = NameScoringCli.digestArgs(args);

        assertEquals(actualAction, expectedAction);
    }
}

