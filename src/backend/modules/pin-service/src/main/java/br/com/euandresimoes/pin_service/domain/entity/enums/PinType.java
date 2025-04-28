package br.com.euandresimoes.pin_service.domain.entity.enums;

public enum PinType {
    PRODUCT("Product"),
    LINK("Link");

    private final String type;

    PinType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
