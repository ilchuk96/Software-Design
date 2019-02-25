package ru.ifmo.cli.commands;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.cli.Command;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CatTest {
	Command command;

	@Before
	public void setCommand() {
		command = new Cat();
	}

	@Test
	public void executeSimpleArgTest() {
		List<String> lst = new ArrayList<>();
		lst.add("src/test/ru/ifmo/cli/files/test");
		assertEquals("2432r43rtferfe\n" +
						"egrtgr\n" +
						"gt\n" +
						"grt\n" +
						"grtg\n",
				command.execute(lst, null));
	}

	@Test
	public void executeSSeveralArgsTest() {
		List<String> lst = new ArrayList<>();
		lst.add("src/test/ru/ifmo/cli/files/test");
		lst.add("src/test/ru/ifmo/cli/files/test");
		assertEquals("2432r43rtferfe\n" +
						"egrtgr\n" +
						"gt\n" +
						"grt\n" +
						"grtg\n" +
						"2432r43rtferfe\n" +
						"egrtgr\n" +
						"gt\n" +
						"grt\n" +
						"grtg\n",
				command.execute(lst, null));
	}
}
