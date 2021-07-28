package Packet;

public class ByteArrayByteStream implements Cloneable{
    private byte[] arr;
    private int pos;

    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public ByteArrayByteStream(byte[] bytes) {
        arr = bytes;
        pos = 0;
    }

    public void append(byte[] bs) {
        byte[] append_bytes = new byte[arr.length + bs.length];
        System.arraycopy(arr, 0, append_bytes, 0, arr.length);
        System.arraycopy(bs, 0, append_bytes, 0, bs.length);
        this.arr = append_bytes;
    }

    public int getSize() {
        return arr.length;
    }

    public boolean readAble(int size) {
        if (pos + size > arr.length)
            return false;
        return true;
    }

    public void seek(int offset) {
        pos = offset;
    }

    public int readByte() {
        return ((int) arr[pos++]) & 0xFF;
    }
}
