package Connector.Opcode;


public enum RecvOpcodePacket {
    LOGIN_MESSENGER(255),
    LOGOUT_MESSENGER(256)
    ;

    private int value = -1;

    RecvOpcodePacket(int value) {
        this.value = value;
    }

    public final int getValue() {
        return value;
    }
}