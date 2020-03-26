package ru.gb.jt.chat.library;

public class Library {
    /*
/auth_request±login±password
/auth_accept±nickname
/auth_error
/broadcast±msg
/msg_format_error±msg
* */
    public static final String DELIMITER = "±";
    public static final String AUTH_REQUEST = "/auth_request";
    public static final String AUTH_ACCEPT = "/auth_accept";
    public static final String AUTH_DENIED = "/auth_denied";
    public static final String MSG_FORMAT_ERROR = "/msg_format_error";
    // если мы вдруг не поняли, что за сообщение и не смогли разобрать
    public static final String TYPE_BROADCAST = "/bcast";
    // то есть сообщение, которое будет посылаться всем

    public static String getAuthRequest(String login, String password) {
        return AUTH_REQUEST + DELIMITER + login + DELIMITER + password;
    }

    public static String getAuthAccept(String nickname) {
        return AUTH_ACCEPT + DELIMITER + nickname;
    }

    public static String getAuthDenied() {
        return AUTH_DENIED;
    }

    public static String getMsgFormatError(String message) {
        return MSG_FORMAT_ERROR + DELIMITER + message;
    }

    public static String getTypeBroadcast(String src, String message) {
        return TYPE_BROADCAST + DELIMITER + System.currentTimeMillis() +
                DELIMITER + src + DELIMITER + message;
    }

    public static String parse(String msg) {
        String[] fields = msg.split(DELIMITER);
        switch (fields[0]) {
            case AUTH_REQUEST:
                return "Authentication request";
            case AUTH_ACCEPT:
                return String.format("You are authorized, %s, wellcome.\n", fields[1]);
            case AUTH_DENIED:
                return "Access denied";
            case TYPE_BROADCAST:
                String delimiter = "--------------------------------";
                return String.format("Broadcast message from %s (%s)\n%s\n%s\n%s",
                        fields[2].toString(), fields[1], delimiter, fields[3], delimiter);
            case MSG_FORMAT_ERROR:
                return String.format("Invalid message: %s", fields[1]);
            default:
                return msg;
        }
    }
}
