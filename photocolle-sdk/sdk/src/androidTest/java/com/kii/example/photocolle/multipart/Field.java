package com.kii.example.photocolle.multipart;

public class Field {

    public final String name;

    public final String value;

    public Field(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Field obj = (Field)o;
        return (this.name.equals(obj.name) && this.value.equals(obj.value));
    }

    @Override
    public String toString() {
        return "{ name=" + this.name + ", value=" + this.value + " }";
    }
}
