package org.bionic.entity;

public enum EnumResourceType {
	
	TEXT (1L),
	IMAGE(2L),
	VIDEO(3L),
	OTHER(4L);

private final Long value;
	
    private EnumResourceType(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}
