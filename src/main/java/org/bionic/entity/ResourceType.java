package org.bionic.entity;

public enum ResourceType {
	
	TEXT(0),
	PHOTO(1),
	VIDEO(2);

private final int value;
	
    private ResourceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
