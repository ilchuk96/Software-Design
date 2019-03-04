package ru.ifmo.cli;

import ru.ifmo.cli.commands.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInterpreter {
	/**
	 * Map of commands' names.
	 */
	private static final Map<String, Command> COMMANDS = new HashMap<String, Command>() {
		{
			put("cat", new Cat());
			put("echo", new Echo());
			put("wc", new Wc());
			put("pwd", new Pwd());
			put("grep", new Grep());
		}
	};

	/**
	 * @param command Command name.
	 * @param env Current variables' environment.
	 * @return Answer for the latest command.
	 */
	public String interpret(String command, Environment env) {
		Parser parser = new Parser();
		String prevArg = null;
		List<String> commands = parser.getCommands(command);
		for (String simpleCommand : commands) {
			CommandBody commandBody = parser.getCommandBody(simpleCommand, env);
			String commandName = commandBody.getBody();
			Command commandToExecute = COMMANDS.getOrDefault(commandName, new ProcessCommand(commandName));
			if (commandBody.getBody().contains("=")) {
				if (commands.size() != 1) {
					continue;
				}
				String body = commandBody.getBody();
				int position = body.indexOf("=");
				env.setVar(body.substring(0, position), body.substring(position+1));
				return "";
			}
			if (commandBody.getBody().equals("exit")) {
				env.stop();
				return "";
			}
			prevArg = commandToExecute.execute(commandBody.getArgs(), prevArg);
		}
		if (prevArg == null) {
			return "\n";
		}
		return prevArg;
	}
}
