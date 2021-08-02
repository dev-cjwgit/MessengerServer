package Packet;

/**********************
 * Little Endian Bytes *
 **********************/

import java.nio.charset.StandardCharsets;

public class MessengerReadPacket {
    private ByteArrayByteStream bs;

    public MessengerReadPacket clone() throws CloneNotSupportedException {
        return new MessengerReadPacket((ByteArrayByteStream) bs.clone());
    }

    public int getSize() {
        return bs.getSize();
    }

    public void seek(int size) {
        bs.seek(size);
    }

    public MessengerReadPacket(ByteArrayByteStream bs) {
        this.bs = bs;
    }

    public MessengerReadPacket(byte[] bytes) {
        bs = new ByteArrayByteStream(bytes);
    }

    public void append(byte[] bs) {
        this.bs.append(bs);
    }

    public final byte readByte() throws Exception {
        if (!bs.readAble(1))
            throw new Exception("don't read bytes");
        final int byte1 = bs.readByte();

        return (byte) byte1;
    }

    public final short readUByte() throws Exception {
        if (!bs.readAble(1))
            throw new Exception("don't read bytes");
        int val = readByte();
        if (val < 0)
            val += 257;
        return (short) val;
    }

    public final short readShort() throws Exception {
        if (!bs.readAble(2))
            throw new Exception("don't read bytes");
        final int byte1 = bs.readByte();
        final int byte2 = bs.readByte();

        return (short) ((byte2 << 8) + byte1);
    }

    public final int readUShort() throws Exception {
        if (!bs.readAble(2))
            throw new Exception("don't read bytes");
        int val = readShort();
        if (val < 0)
            val += 65537;
        return val;
    }

    public final int readInt() throws Exception {
        if (!bs.readAble(4))
            throw new Exception("don't read bytes");
        final int byte1 = bs.readByte();
        final int byte2 = bs.readByte();
        final int byte3 = bs.readByte();
        final int byte4 = bs.readByte();
        return (byte4 << 24) + (byte3 << 16) + (byte2 << 8) + byte1;
    }

    public final long readLong() throws Exception {
        if (!bs.readAble(8))
            throw new Exception("don't read bytes");
        final int byte1 = bs.readByte();
        final int byte2 = bs.readByte();
        final int byte3 = bs.readByte();
        final int byte4 = bs.readByte();
        final long byte5 = bs.readByte();
        final long byte6 = bs.readByte();
        final long byte7 = bs.readByte();
        final long byte8 = bs.readByte();
        return ((byte8 << 56) + (byte7 << 48) + (byte6 << 40) + (byte5 << 32) + (byte4 << 24) + (byte3 << 16) + (byte2 << 8) + byte1);
    }

    public final float readFloat() throws Exception {
        if (!bs.readAble(4))
            throw new Exception("don't read bytes");
        return Float.intBitsToFloat(readInt());
    }

    public final Double readDouble() throws Exception {
        if (!bs.readAble(8))
            throw new Exception("don't read bytes");
        return Double.longBitsToDouble(readLong());
    }

    // false : Short, true : Int
    public final String readString(boolean type) throws Exception {
        if (!bs.readAble((type ? 4 : 2)))
            throw new Exception("don't read bytes");
        int length = (type ? readInt() : readShort());
        final byte ret[] = new byte[length];
        for (int x = 0; x < length; x++)
            ret[x] = (byte) bs.readByte();
        return new String(ret, StandardCharsets.UTF_8);
    }

    public final String readString() throws Exception {
        if (!bs.readAble(4))
            throw new Exception("don't read bytes");
        return readString(true);
    }
}
