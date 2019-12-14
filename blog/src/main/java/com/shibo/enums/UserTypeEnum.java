package com.shibo.enums;

/**
 * @author shibo
 * @date 19-12-11 下午9:13
 */
public enum UserTypeEnum {
    GUEST(1, "游客"),
    AUTHOR(2, "作者");

    private Integer typeCode;
    private String typeName;

    UserTypeEnum(Integer typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }
}
