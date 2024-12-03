package core.properties;

public enum Property {
    BASE_URL,
    EMAIL,
    PASSWORD,
    AUTHORISE_USER_NAME,
    SEARCH_PARAMETERS;

    public String readProperty() {
        return PropertyReader.getProperty(getPropertyName());
    }

    public String getPropertyName() {
        return name().toLowerCase().replaceAll("_", ".");
    }
}
