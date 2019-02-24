package ru.ifmo.cli;

import java.util.List;

public class CommandBody {
	private String body;
	private List<String> args;

	public CommandBody(String body, List<String> args) {
		this.body = body;
		this.args = args;
	}

	/**
	 * @return Command.
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @return Command's arguments.
	 */
	public List<String> getArgs() {
		return args;
	}
}
