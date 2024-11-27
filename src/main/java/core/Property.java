package core;

public enum Property {
    BASE_URL,
    EMAIL,
    PASSWORD,
    AUTHORISE_USER_NAME;

    public String readProperty() {
        return PropertyReader.getProperty(getPropertyName());
    }

    public String getPropertyName() {
        return name().toLowerCase().replaceAll("_", ".");
    }
}
