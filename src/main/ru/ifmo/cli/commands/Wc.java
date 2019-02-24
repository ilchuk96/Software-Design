package ru.ifmo.cli.commands;

import com.sun.istack.internal.Nullable;
import ru.ifmo.cli.Command;

import java.util.List;

public class Wc implements Command {
	@Override
	public String execute(List<String> args, @Nullable String stringFromPrevCommand) {
		/*
		 * If it has arguments, use it.
		 */
		if (args.size() != 0) {
			return "1 1 1";
		}
		/*
		 * If it has argument from previous command, use it.
		 */
		if (stringFromPrevCommand != null) {
			return "2 2 2";
		}
		/*
		 * TODO: console input.
		 */
		return "\t0\t0\t0";
	}
}
