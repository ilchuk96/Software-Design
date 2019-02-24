package ru.ifmo.cli.commands;

import com.sun.istack.internal.Nullable;
import ru.ifmo.cli.Command;

import java.util.List;

public class Echo implements Command {
	/**
	 * Print args.
	 * @param args Arguments of the command.
	 * @param stringFromPrevCommand Answer for previous command.
	 * @return String-args.
	 */
	@Override
	public String execute(List<String> args, @Nullable String stringFromPrevCommand) {
		StringBuilder ans = new StringBuilder();
		int size = args.size();
		for (int i = 0; i < size; i++) {
			ans.append(args.get(i));
			if (i != size - 1) {
				ans.append(" ");
			}
		}
		String answer = ans.toString();
		if (answer.equals("")) {
			return null;
		}
		return answer + "\n";
	}
}
