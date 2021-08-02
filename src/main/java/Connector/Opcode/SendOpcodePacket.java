package Connector.Opcode;

public enum SendOpcodePacket {
    LOGIN_MESSENGER(255), // 로그인
    LOGOUT_MESSENGER(256), // 로그아웃
    INSERT_ACCOUNT(300), // 회원가입
    DELETE_ACCOUNT_UID(301), // uid로 회원탈퇴
    DELETE_ACCOUNT_EMAIL(302), // email로 회원탈퇴

    INSERT_FIREND(310), // 친구등록
    DELETE_FRIEND(311), // 친구삭제
    INSERT_CHATTING(320), // 채팅방 생성
    INSERT_CHATTING_JOIN(321), // 채팅방 가입
    INSERT_CHATTING_LOG(322), // 채팅방에 메세지 보내기

    DELETE_CHATTING(325), // 채팅방 삭제
    DELETE_CHATTING_JOIN(326), // 채팅방 나가기
    DELETE_CHATTING_LOG(327), // 채팅방 메세지 삭제

    ;

    private int value = -1;

    SendOpcodePacket(int value) {
        this.value = value;
    }

    public final int getValue() {
        return value;
    }
}