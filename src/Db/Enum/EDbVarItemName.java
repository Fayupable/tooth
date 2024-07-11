package Db.Enum;

public enum EDbVarItemName {
    ORAL_B_IO_5(1),
    ORAL_B_AQUACARE_AGIZ_DUSU(2);

    private final int id;

    EDbVarItemName(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public static EDbVarItemName getById(int id) {
        for (EDbVarItemName e : values()) {
            if (e.id == id) return e;
        }
        return null;
    }
    public static void printAll() {
        for (EDbVarItemName e : values()) {
            System.out.println(e);
        }
    }
}