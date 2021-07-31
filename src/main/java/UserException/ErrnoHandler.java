package UserException;


import java.util.ArrayList;
import java.util.Map;

public enum ErrnoHandler {
    Fail(0),
    Success(1),
    Unknown_Err(-1),

    SQLSyntax_Err(5),

    //region SELECT
    UnkownColumn_Err(10),
    TableDoesntExist_Err(11),
    //end region

    // region INSERT
    DuplicateKey_Err(100),

    //endregion
    ;

    private int value = -2;

    ErrnoHandler(int value) {
        this.value = value;
    }


    public final int getValue() {
        return value;
    }


}
