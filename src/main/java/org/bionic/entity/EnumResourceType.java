package org.bionic.entity;

public enum EnumResourceType {
	
	TEXT(0),
	PHOTO(1),
	VIDEO(2);

private final int value;
	
    private EnumResourceType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
