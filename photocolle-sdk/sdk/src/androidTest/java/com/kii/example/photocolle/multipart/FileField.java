package com.kii.example.photocolle.multipart;

public class FileField extends Field {

    public final String filename;

    public FileField(String filename, String hash) {
        super("file", hash);
        this.filename = filename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileField obj = (FileField)o;
        return (super.equals(o) && this.filename.equals(obj.filename));
    }

    @Override
    public String toString() {
        return "{ name=" + this.name + ", filename=" + this.filename
                + ", hash=" + this.value + " }";
    }
}
