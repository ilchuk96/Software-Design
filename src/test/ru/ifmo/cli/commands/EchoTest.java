package ru.ifmo.cli.commands;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.cli.Command;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class EchoTest {
	Command command;

	@Before
	public void setCommand() {
		command = new Echo();
	}

	@Test
	public void executeArgsTest() {
		List<String> lst = new ArrayList<>();
		lst.add("123");
		lst.add("456");
		assertEquals("123 456\n", command.execute(lst, null));
	}

	@Test
	public void executeNullTest() {
		List<String> lst = new ArrayList<>();
		assertNull(command.execute(lst, null));
	}
}
