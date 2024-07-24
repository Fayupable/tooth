package Item.Enum;

public enum ItemType {
    TOOTHBRUSHER("Toothbrusher"),
    TOOTHWASHER("Toothwasher"),
    DENTALFLOSS("Dental Floss"),
    TOOTHPASTE("Toothpaste"),
    MOUTHWASH("Mouthwash"),
    TONGUECLEANER("Tongue Cleaner");


    private final String description;

    ItemType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}