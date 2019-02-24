package ru.ifmo.cli.commands;

import com.sun.istack.internal.Nullable;
import ru.ifmo.cli.Command;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Wc implements Command {
	/*
	* Info abot file or string.
	 */
	private class WcNumbers {
		private int lines;
		private int words;
		private int bytes;

		public WcNumbers(int l, int w, int b) {
			lines = l;
			words = w;
			bytes = b;
		}

		public int getLines() {
			return lines;
		}

		public int getWords() {
			return words;
		}

		public int getBytes() {
			return bytes;
		}

		/*
		* TODO: format out.
		 */
		@Override
		public String toString() {
			return getLines() + "\t" + getWords() + "\t" + getBytes();
		}
	}

	/**
	 * Get info about the string.
	 * @param str String we need info about.
	 * @return WcNumbers info about the string.
	 */
	private WcNumbers getStats(String str) {
		int lines = str.split("\r\n|\r|\n").length;
		int words = str.split("(\r|\n|\t| )+").length;
		int bytes = str.length();
		return new WcNumbers(lines, words, bytes);
	}

	/**
	 * Prints info about files/string.
	 * @param args List of files.
	 * @param stringFromPrevCommand String.
	 * @return String - info about args.
	 */
	@Override
	public String execute(List<String> args, @Nullable String stringFromPrevCommand) {
		/*
		 * If it has arguments, use it.
		 */
		if (args.size() != 0) {
			if (args.size() == 1) {
				String ans;
				String file = args.get(0);
				try {
					ans = new String(Files.readAllBytes(Paths.get(file)));
				} catch (java.io.IOException e) {
					throw new RuntimeException("No such file: " + file);
				}
				return getStats(ans).toString() + "\t" + file + "\n";
			}
			StringBuilder ans = new StringBuilder();
			int totalLines = 0;
			int totalWords = 0;
			int totalBytes = 0;
			for (String file : args) {
				try {
					String str = new String(Files.readAllBytes(Paths.get(file)));
					WcNumbers stats = getStats(str);
					ans.append(stats.toString() + "\t" + file + "\n");
					totalLines += stats.getLines();
					totalWords += stats.getWords();
					totalBytes += stats.getBytes();
				} catch (java.io.IOException e) {
					throw new RuntimeException("No such file: " + file);
				}
			}
			ans.append(totalLines + "\t" + totalWords + "\t" + totalBytes + "\ttotal");
			return ans.toString() + "\n";
		}
		/*
		 * If it has argument from previous command, use it.
		 */
		if (stringFromPrevCommand != null) {
			return getStats(stringFromPrevCommand).toString() + "\n";
		}
		/*
		 * TODO: console input.
		 */
		return "\t0\t0\t0\n";
	}
}
