package core;

public enum Property {
    BASE_URL,
    EMAIL,
    PASSWORD;

    public String readProperty() {
        return PropertyReader.getProperty(getPropertyName());
    }

    public String getPropertyName() {
        return name().toLowerCase().replaceAll("_", ".");
    }
}
