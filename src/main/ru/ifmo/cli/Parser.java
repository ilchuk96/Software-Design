package ru.ifmo.cli;

import ru.ifmo.cli.exceptions.SyntaxException;

import java.util.ArrayList;
import java.util.List;

public class Parser {
	/**
	 * Parse the string.
	 * @param str String to parse.
	 * @return List of string-commands.
	 */
	public List<String> getCommands(String str) {
		/*
		Show if ' or " was opened.
		 */
		boolean openOne = false;
		boolean openTwo = false;

		List<String> ans = new ArrayList<>();
		StringBuilder curCommand = new StringBuilder();

		/*
		Check if '|' if pipeline or just part of a string.
		 */
		for (char ch : str.toCharArray()) {
			if (ch == '\'' && !openTwo) {
				openOne = !openOne;
			}
			if (ch == '\"' && !openOne) {
				openTwo = !openTwo;
			}
			if (!openOne && !openTwo && ch == '|') {
				ans.add(curCommand.toString());
				curCommand = new StringBuilder();
				continue;
			}
			curCommand.append(ch);
		}
		if (openOne || openTwo) {
			throw new SyntaxException("Some quote wasn't closed.");
		}
		ans.add(curCommand.toString());
		return ans;
	}

	/**
	 * All ' ans " must be closed before this step.
	 * @param command 1 single command.
	 * @param env Current environment.
	 * @return CommandBody to be executed.
	 */
	public CommandBody getCommandBody(String command, Environment env) {
		StringBuilder arg = new StringBuilder();
		List<String> args = new ArrayList<>();
		/*
		Show if ' or " was opened.
		 */
		boolean openOne = false;
		boolean openTwo = false;
		/*
		Show if it must be variable.
		 */
		boolean openDollar = false;

		for (char ch : command.toCharArray()) {
			if (ch == ' ') {
				if (!openOne && !openTwo) {
					String curArg = arg.toString();
					if (!curArg.equals("")) {
						if (!openDollar) {
							args.add(curArg);
						} else {
							args.add(env.getVar(curArg));
							openDollar = false;
						}
						arg = new StringBuilder();
					} else {
						if (openDollar) {
							args.add("$");
						}
					}
					continue;
				}
				if (openDollar) {
					openDollar = false;
					String val = arg.toString();
					args.add(env.getVar(val));
					arg = new StringBuilder();
					continue;
				}
			}
			if (ch == '\'' && !openTwo) {
				openOne = !openOne;
				continue;
			}
			if (ch == '\"' && !openOne) {
				openTwo = !openTwo;
				continue;
			}
			if (ch == '$') {
				if (!openDollar) {
					openDollar = true;
					continue;
				} else {
					String val = arg.toString();
					if (val.equals("")) {
						args.add("$");
					} else {
						args.add(env.getVar(val));
					}
					arg = new StringBuilder();
					continue;
				}
			}
			arg.append(ch);
		}

		if (openOne || openTwo) {
			throw new SyntaxException("Some quote wasn't closed.");
		}

		String curArg = arg.toString();
		if (!openDollar) {
			if (!curArg.equals("")) {
				args.add(curArg);
			}
		} else {
			if (!curArg.equals("")) {
				args.add(env.getVar(curArg));
			} else {
				args.add("$");
			}
		}

		return new CommandBody(args.get(0), args.subList(1, args.size()));
	}
}
