package ru.ifmo.cli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

public class ParserTest {
	Parser parser;

	@Before
	public void createParser() {
		parser = new Parser();
	}

	@Test
	public void getCommandsTest() {
		String str = "str1 | \"str|\" | str'3'";
		List<String> ans = new ArrayList<>();
		ans.add("str1 ");
		ans.add(" \"str|\" ");
		ans.add(" str'3'");
		assertEquals(ans, parser.getCommands(str));
	}

	@Test
	public void getCommandBodyWithoutVariablesTest() {
		String str = "command 'arg1'as arg2' '";
		List<String> lst = new ArrayList<>();
		lst.add("arg1as");
		lst.add("arg2 ");
		CommandBody ans = new CommandBody("command", lst);
		CommandBody test = parser.getCommandBody(str, new Environment());
		assertEquals(ans.getBody(), test.getBody());
		assertEquals(ans.getArgs(), test.getArgs());
	}

	@Test
	public void getCommandBodyVariableSetTest() {
		Environment env = new Environment();
		env.setVar("x", "5");
		env.setVar("y", "6");
		String str = "command $'x' $x$\"y\" $x";
		List<String> lst = new ArrayList<>();
		lst.add("5");
		lst.add("5");
		lst.add("6");
		lst.add("5");
		CommandBody ans = new CommandBody("command", lst);
		CommandBody test = parser.getCommandBody(str, env);
		assertEquals(ans.getBody(), test.getBody());
		assertEquals(ans.getArgs(), test.getArgs());
	}
}
