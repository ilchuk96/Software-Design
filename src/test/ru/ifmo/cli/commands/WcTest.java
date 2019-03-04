package ru.ifmo.cli.commands;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.cli.Command;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WcTest {
	Command command;

	@Before
	public void setCommand() {
		command = new Wc();
	}

	@Test
	public void executeNullTest() {
		List<String> lst = new ArrayList<>();
		assertEquals("2\t3\t16\n", command.execute(lst, "123    456\n 123\t"));
	}

	@Test
	public void executeSimpleArgTest() {
		List<String> lst = new ArrayList<>();
		lst.add("src/test/ru/ifmo/cli/files/test");
		assertEquals("5\t5\t34\tsrc/test/ru/ifmo/cli/files/test\n",
				command.execute(lst, null));
	}

	@Test
	public void executeSeveralArgsTest() {
		List<String> lst = new ArrayList<>();
		lst.add("src/test/ru/ifmo/cli/files/test");
		lst.add("src/test/ru/ifmo/cli/files/test");
		assertEquals("5\t5\t34\tsrc/test/ru/ifmo/cli/files/test\n" +
						"5\t5\t34\tsrc/test/ru/ifmo/cli/files/test\n" +
						"10\t10\t68\ttotal\n",
				command.execute(lst, null));
	}
}
