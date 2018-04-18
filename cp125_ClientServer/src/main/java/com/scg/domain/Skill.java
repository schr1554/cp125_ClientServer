package com.scg.domain;

import java.io.Serializable;

public enum Skill implements Serializable {

	/**
	 * Project manager skill.
	 */
	PROJECT_MANAGER("Project Manager", 250),
	/**
	 * Engineer skill.
	 */
	SOFTWARE_ENGINEER("Software Engineer", 150),
	/**
	 * Engineer skill.
	 */
	SOFTWARE_TESTER("Software Tester", 100),
	/**
	 * Tester skill.
	 */
	SYSTEM_ARCHITECT("System Architect", 200),
	/**
	 * Unknown skill.
	 */
	UNKNOWN_SKILL("Unknown Skill", 0);

	private final String friendlyName;
	private int rate;

	private Skill(final String name, final int rate) {
		this.friendlyName = name;
		this.rate = rate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return this.friendlyName;
	}

	/**
	 * Getter for property rate.
	 * 
	 * @return Value of property rate.
	 * 
	 */
	public int getRate() {
		return this.rate;

	}

}
