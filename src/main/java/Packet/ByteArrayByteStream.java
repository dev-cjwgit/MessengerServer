package Packet;

public class ByteArrayByteStream {
    private byte[] arr;
    private int pos;

    public ByteArrayByteStream(byte[] bytes) {
        arr = bytes;
        pos = 0;
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
