package ru.ifmo.cli.commands;

import com.sun.istack.internal.Nullable;
import ru.ifmo.cli.Command;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Cat implements Command {
	@Override
	public String execute(List<String> args, @Nullable String stringFromPrevCommand) {
		if (args.isEmpty()) {
			return stringFromPrevCommand;
		}
		StringBuilder ans = new StringBuilder();
		for (String file : args) {
			try {
				ans.append(new String(Files.readAllBytes(Paths.get(file))));
			} catch (java.io.IOException e) {
				throw new RuntimeException("No such file: " + file);
			}
		}
		return ans.toString();
	}
}
