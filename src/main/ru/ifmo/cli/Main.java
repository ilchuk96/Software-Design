package ru.ifmo.cli;

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
			System.out.print(ci.interpret(command, env));
		}
	}
}
