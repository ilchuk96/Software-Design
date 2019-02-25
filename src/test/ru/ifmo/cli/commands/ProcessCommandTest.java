package ru.ifmo.cli.commands;

import org.junit.Test;
import ru.ifmo.cli.Command;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProcessCommandTest {
	Command command;

	@Test
	public void executeNullTest() {
		command = new ProcessCommand("python3");
		List<String> lst = new ArrayList<>();
		lst.add("src/test/ru/ifmo/cli/files/test.py");
		assertEquals("123\n", command.execute(lst, null));
	}
}
