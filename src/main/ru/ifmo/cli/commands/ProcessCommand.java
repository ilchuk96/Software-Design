package ru.ifmo.cli.commands;

import com.sun.istack.internal.Nullable;
import ru.ifmo.cli.Command;

import java.util.List;

public class ProcessCommand implements Command {
	/**
	 * Command to be processed.
	 */
	private String command;

	@Override
	public String execute(List<String> args, @Nullable String stringFromPrevCommand) {
		return null;
	}

	public ProcessCommand(String command) {
		this.command = command;
	}
}
