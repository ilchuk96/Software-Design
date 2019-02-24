package ru.ifmo.cli;

import ru.ifmo.cli.exceptions.SyntaxException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		Environment env = new Environment();
		CommandInterpreter ci = new CommandInterpreter();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		while (env.toContinue()) {
			System.out.print("> ");
			String command = bf.readLine();
			try {
				System.out.print(ci.interpret(command, env));
			} catch (SyntaxException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
