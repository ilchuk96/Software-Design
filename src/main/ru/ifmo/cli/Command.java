package ru.ifmo.cli;

import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.util.List;

public interface Command {
	/**
	 * @param args Arguments of the command.
	 * @param stringFromPrevCommand Answer for previous command.
	 * @return String-answer.
	 */
	String execute(List<String> args, @Nullable String stringFromPrevCommand);
}
