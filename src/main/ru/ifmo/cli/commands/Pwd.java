package ru.ifmo.cli.commands;

import com.sun.istack.internal.Nullable;
import ru.ifmo.cli.Command;

import java.nio.file.Paths;
import java.util.List;

public class Pwd implements Command {
	/**
	 * Prints current path.
	 * @param args Not used.
	 * @param stringFromPrevCommand Not used.
	 * @return String-path.
	 */
	@Override
	public String execute(List<String> args, @Nullable String stringFromPrevCommand) {
		return Paths.get(".").toAbsolutePath().toString() + "\n";
	}
}
