package ru.ifmo.cli.commands;

import org.junit.Before;
import org.junit.Test;
import ru.ifmo.cli.Command;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GrepTest {
	Command command;

	@Before
	public void setCommand() {
		command = new Grep();
	}

	@Test
	public void singleFileTest() {
		List<String> lst = new ArrayList<>();
		lst.add("2432r43rtferfe");
		lst.add("/home/ivan/Software-Design/src/test/ru/ifmo/cli/files/test");
		assertEquals("2432r43rtferfe\n", command.execute(lst, null));
	}

	@Test
	public void singleFileWordTest() {
		List<String> lst = new ArrayList<>();
		lst.add("-w");
		lst.add("grt");
		lst.add("/home/ivan/Software-Design/src/test/ru/ifmo/cli/files/test");
		assertEquals("grt\n", command.execute(lst, null));
	}

	@Test
	public void singleFileCaseTest() {
		List<String> lst = new ArrayList<>();
		lst.add("-i");
		lst.add("eGrtGr");
		lst.add("/home/ivan/Software-Design/src/test/ru/ifmo/cli/files/test");
		assertEquals("egrtgr\n", command.execute(lst, null));
	}

	@Test
	public void singleFileWordCaseTest() {
		List<String> lst = new ArrayList<>();
		lst.add("-wi");
		lst.add("GRT");
		lst.add("/home/ivan/Software-Design/src/test/ru/ifmo/cli/files/test");
		assertEquals("grt\n", command.execute(lst, null));
	}

	@Test
	public void singleNullTest() {
		List<String> lst = new ArrayList<>();
		lst.add("-wi");
		lst.add("GRT6");
		lst.add("/home/ivan/Software-Design/src/test/ru/ifmo/cli/files/test");
		assertNull(command.execute(lst, null));
	}

	@Test
	public void twoFilesTest() {
		List<String> lst = new ArrayList<>();
		lst.add("2432r43r");
		lst.add("/home/ivan/Software-Design/src/test/ru/ifmo/cli/files/test");
		lst.add("/home/ivan/Software-Design/src/test/ru/ifmo/cli/files/test");
		assertEquals("/home/ivan/Software-Design/src/test/ru/ifmo/cli/files/test:2432r43rtferfe\n" +
						"/home/ivan/Software-Design/src/test/ru/ifmo/cli/files/test:2432r43rtferfe\n"
				, command.execute(lst, null));
	}

	@Test
	public void fromPrevTest() {
		List<String> lst = new ArrayList<>();
		lst.add("123");
		assertEquals("123\n", command.execute(lst, "123"));
	}

	@Test
	public void multiLinesTest(){
		List<String> lst = new ArrayList<>();
		lst.add("-wA");
		lst.add("1");
		lst.add("2432r43rtferfe");
		lst.add("/home/ivan/Software-Design/src/test/ru/ifmo/cli/files/test");
		assertEquals("2432r43rtferfe\negrtgr\n", command.execute(lst, null));
	}
}


