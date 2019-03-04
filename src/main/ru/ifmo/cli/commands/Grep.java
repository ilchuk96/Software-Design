package ru.ifmo.cli.commands;

import com.sun.istack.internal.Nullable;
import org.apache.commons.cli.*;
import ru.ifmo.cli.Command;
import ru.ifmo.cli.exceptions.SyntaxException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Grep implements Command {
	@Override
	public String execute(List<String> args, @Nullable String stringFromPrevCommand) {
		Options options = new Options();
		options.addOption("i", false, "case-insensitivity");
		options.addOption("w", false, "only full words");
		options.addOption( Option.builder("A")
				.argName("n")
				.hasArg()
				.numberOfArgs(1)
				.desc("number of lines after the regexp")
				.build() );

		CommandLineParser parser = new DefaultParser();
		String[] strArr = new String[args.size()];
		CommandLine cmd;
		try {
			cmd = parser.parse(options, args.toArray(strArr));
		} catch (ParseException e) {
			throw new SyntaxException(e.getMessage());
		}

		int len = args.size();
		int filesStartFrom = -1;
		String regexp = null;
		boolean prevIsA = false;
		for (int i = 0; i < len; i++) {
			if (prevIsA) {
				prevIsA = false;
				continue;
			}
			if (args.get(i).contains("A")) {
				prevIsA = true;
			}
			if (!args.get(i).startsWith("-")) {
				regexp = args.get(i);
				filesStartFrom = i + 1;
				break;
			}
		}

		if (regexp == null) {
			throw new SyntaxException("Using: grep [PARAM]… REGEXP [FILE]…");
		}


		Pattern pattern;
		if (cmd.hasOption("w")) {
			regexp = "( |^)" + regexp + "( |$)";
		}
		if (cmd.hasOption("i")) {
			pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
		} else {
			pattern = Pattern.compile(regexp);
		}

		int toPrint = 0;
		if (cmd.hasOption("A")) {
			toPrint = Integer.parseInt(cmd.getOptionValue("A"));
		}
		int stillToPrint = -1;

		if (filesStartFrom == len) {
			if (stringFromPrevCommand == null) {
				/*
				 * TODO: console input.
				 */
				return null;
			}
			StringBuilder ans = new StringBuilder();
			for (String line : stringFromPrevCommand.split("\n")) {
				Matcher matcher = pattern.matcher(line);
				boolean found = matcher.find();
				if (found) {
					stillToPrint = toPrint;
				}
				if (stillToPrint >= 0) {
					stillToPrint--;
					ans.append(line);
					ans.append("\n");
				}
			}
			String str = ans.toString();
			if (str.isEmpty()) {
				return null;
			}
			return str;
		}

		boolean addFileName = filesStartFrom < len-1;
		StringBuilder ans = new StringBuilder();
		for (String fileName : args.subList(filesStartFrom, len)) {
			String file;
			try {
				file = new String(Files.readAllBytes(Paths.get(fileName)));
			} catch (IOException e) {
				throw new SyntaxException(e.getMessage());
			}
			for (String line : file.split("\n")) {
				Matcher matcher = pattern.matcher(line);
				boolean found = matcher.find();
				if (found) {
					stillToPrint = toPrint;
				}
				if (stillToPrint >= 0) {
					stillToPrint--;
					if (addFileName) {
						ans.append(fileName);
						ans.append(":");
					}
					ans.append(line);
					ans.append("\n");
				}
			}
		}

		String str = ans.toString();
		if (str.isEmpty()) {
			return null;
		}
		return str;
	}
}
