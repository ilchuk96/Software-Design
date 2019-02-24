package ru.ifmo.cli.commands;

import com.sun.istack.internal.Nullable;
import ru.ifmo.cli.Command;

import java.util.List;

public class Echo implements Command {
	@Override
	public String execute(List<String> args, @Nullable String stringFromPrevCommand) {
		StringBuilder ans = new StringBuilder();
		for (String arg : args) {
			ans.append(arg);
			ans.append(" ");
		}
		return ans.toString();
	}
}
