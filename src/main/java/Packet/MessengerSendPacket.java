package Packet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

public class MessengerSendPacket {
    private ByteArrayOutputStream bs;

    public MessengerSendPacket() {
        bs = new ByteArrayOutputStream();
    }

    public final void writeShort(final int i) {
        bs.write((byte) (i & 0xFF));
        bs.write((byte) ((i >>> 8) & 0xFF));
    }

    public final void writeInt(final int i) {
        bs.write((byte) (i & 0xFF));
        bs.write((byte) ((i >>> 8) & 0xFF));
        bs.write((byte) ((i >>> 16) & 0xFF));
        bs.write((byte) ((i >>> 24) & 0xFF));
    }

    public final void writeLong(final long l) {
        bs.write((byte) (l & 0xFF));
        bs.write((byte) ((l >>> 8) & 0xFF));
        bs.write((byte) ((l >>> 16) & 0xFF));
        bs.write((byte) ((l >>> 24) & 0xFF));
        bs.write((byte) ((l >>> 32) & 0xFF));
        bs.write((byte) ((l >>> 40) & 0xFF));
        bs.write((byte) ((l >>> 48) & 0xFF));
        bs.write((byte) ((l >>> 56) & 0xFF));
    }

    public final void writeString(String body, boolean type) {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        if (type)
            writeInt(bytes.length);
        else
            writeShort(bytes.length);
        for (byte item : bytes) {
            bs.write(item);
        }
    }

    public final void writeString(String body) {
        writeString(body, true);
    }

    public final ByteBuf getByteBuf() {
        return Unpooled.wrappedBuffer(bs.toByteArray());
    }
}