package ru.ifmo.cli;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CommandInterpreterTest {
	Environment env;
	CommandInterpreter ci;

	@Before
	public void createEnvironment() {
		env = new Environment();
		ci = new CommandInterpreter();
	}

	@Test
	public void EchoEchoTest() {
		assertEquals("123\n", ci.interpret("echo 0 | echo 123", env));
	}

	@Test
	public void AssignmentEchoTest() {
		ci.interpret("x=123", env);
		assertEquals("123", env.getVar("x"));
		assertEquals("123\n", ci.interpret("echo $x", env));
	}

	@Test
	public void ExitEchoTest() {
		assertEquals("", ci.interpret("exit | echo 5", env));
		assertFalse(env.toContinue());
	}
}
