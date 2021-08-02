package UserException;


public enum ResultHandler {
    Fail(0),
    Success(1),
    Unknown_Err(5),
    Unknown_Exception(-1),
    SQLSyntax_Err(5),

    //region SELECT
    UnkownColumn_Err(10),
    TableDoesntExist_Err(11),
    ColumnBeNULL_Err(12),
    //end region

    // region INSERT
    DuplicateKey_Err(100),

    //endregion
    ;

    private int value = -2;

    ResultHandler(int value) {
        this.value = value;
    }

    public final int getValue() {
        return value;
    }


}
