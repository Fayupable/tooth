package Db.Enum;

public enum EDbVarDayPart {
    MORNING(1),
    AFTERNOON(2),
    EVENING(3),
    NIGHT(4);

    private final int id;

    EDbVarDayPart(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    public static EDbVarDayPart getById(int id) {
        for (EDbVarDayPart e : values()) {
            if (e.id == id) return e;
        }
        return null;
    }
    public static void printAll() {
        for (EDbVarDayPart e : values()) {
            System.out.println(e);
        }
    }
}