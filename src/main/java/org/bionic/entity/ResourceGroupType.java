package org.bionic.entity;

public enum ResourceGroupType {
	
	LESSON(0),
	EVENT(1),
	COMPETITION(2),
	LEVEL_1(3),
	LEVEL_2(4),
	LEVEL_3(5);
	
	private final int value;

	private ResourceGroupType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}