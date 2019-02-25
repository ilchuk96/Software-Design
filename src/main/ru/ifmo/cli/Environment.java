package ru.ifmo.cli;

import java.util.HashMap;
import java.util.Map;

public class Environment {
	private boolean toContinue = true;
	private Map<String, String> dict = new HashMap<>();

	/**
	 * @return False if it is time to exit. True if continue.
	 */
	public boolean toContinue() {
		return toContinue;
	}

	/**
	 * Sets toContinue=false to exit the program.
	 */
	public void stop() {
		toContinue = false;
	}

	/**
	 * Sets variable.
	 * @param name Variable name.
	 * @param val Variable value.
	 */
	public void setVar(String name, String val) {
		dict.put(name, val);
	}

	/**
	 * Get variable value.
	 * @param name Variable name.
	 * @return Variable value or empty string if no such variable name.
	 */
	public String getVar(String name) {
		return dict.getOrDefault(name, "");
	}
}
