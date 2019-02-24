package ru.ifmo.cli.commands;

import com.sun.istack.internal.Nullable;
import ru.ifmo.cli.Command;
import ru.ifmo.cli.exceptions.SyntaxException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProcessCommand implements Command {
	/**
	 * Command to be processed.
	 */
	private String command;

	public ProcessCommand(String command) {
		this.command = command;
	}

	/**
	 * Process command with arguments.
	 * @param args Arguments of the command.
	 * @param stringFromPrevCommand Not used.
	 * @return Command's return.
	 */
	@Override
	public String execute(List<String> args, @Nullable String stringFromPrevCommand) {
		List<String> commands = new ArrayList<>();
		commands.add(command);
		commands.addAll(args);
		ProcessBuilder processBuilder = new ProcessBuilder(commands);
		Process process;
		try {
			process = processBuilder.start();
		} catch (java.io.IOException e) {
			throw new SyntaxException("Command " + command + " not found.");
		}

		try {
			process.waitFor();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}

		InputStream inputStream = process.getInputStream();
		String ans = "";
		Scanner scanner = new Scanner(inputStream);
		while (scanner.hasNext()) {
			ans = scanner.next();
		}
		return ans + "\n";
	}
}
