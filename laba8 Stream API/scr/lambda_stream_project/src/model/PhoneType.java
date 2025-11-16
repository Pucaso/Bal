package model;

public enum PhoneType {
    MOBILE("мобильный"),
    LANDLINE("стационарный");
    
    private final String description;
    
    PhoneType(String description) {
        this.description = description;
    }
    
    public String getDescription() {
        return description;
    }
}