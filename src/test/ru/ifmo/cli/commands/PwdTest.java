package ru.ifmo.cli.commands;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.cli.Command;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PwdTest {
	Command command;

	@Before
	public void setCommand() {
		command = new Pwd();
	}

	@Test
	public void executeSimpleArgTest() {
		List<String> lst = new ArrayList<>();
		lst.add("123");
		assertEquals(Paths.get(".").toAbsolutePath().toString() + "\n",
				command.execute(lst, null));
	}
}
