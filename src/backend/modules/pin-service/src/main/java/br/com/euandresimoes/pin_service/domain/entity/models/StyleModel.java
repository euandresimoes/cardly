package br.com.euandresimoes.pin_service.domain.entity.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "pageBackground", column = @Column(name = "card_page_background")),
        @AttributeOverride(name = "cardBackground", column = @Column(name = "card_background")),
        @AttributeOverride(name = "cardBorderColor", column = @Column(name = "card_border_color")),
        @AttributeOverride(name = "cardTextColor", column = @Column(name = "card_text_color")),
        @AttributeOverride(name = "cardButtonColor", column = @Column(name = "card_button_color")),
        @AttributeOverride(name = "cardButtonTextColor", column = @Column(name = "card_button_text_color")),
        @AttributeOverride(name = "cardFontFamily", column = @Column(name = "card_font_family")),
})
public class StyleModel {
    private String pageBackground;
    private String cardBackground;
    private String cardBorderColor;
    private String cardTextColor;
    private String cardButtonColor;
    private String cardButtonTextColor;
    private String cardFontFamily;

    public StyleModel() {
    }

    public StyleModel(String pageBackground, String cardBackground, String cardBorderColor, String cardTextColor, String cardButtonColor, String cardButtonTextColor, String cardFontFamily) {
        this.pageBackground = pageBackground;
        this.cardBackground = cardBackground;
        this.cardBorderColor = cardBorderColor;
        this.cardTextColor = cardTextColor;
        this.cardButtonColor = cardButtonColor;
        this.cardButtonTextColor = cardButtonTextColor;
        this.cardFontFamily = cardFontFamily;
    }

    public String getPageBackground() {
        return pageBackground;
    }

    public void setPageBackground(String pageBackground) {
        this.pageBackground = pageBackground;
    }

    public String getCardBackground() {
        return cardBackground;
    }

    public void setCardBackground(String cardBackground) {
        this.cardBackground = cardBackground;
    }

    public String getCardBorderColor() {
        return cardBorderColor;
    }

    public void setCardBorderColor(String cardBorderColor) {
        this.cardBorderColor = cardBorderColor;
    }

    public String getCardTextColor() {
        return cardTextColor;
    }

    public void setCardTextColor(String cardTextColor) {
        this.cardTextColor = cardTextColor;
    }

    public String getCardButtonColor() {
        return cardButtonColor;
    }

    public void setCardButtonColor(String cardButtonColor) {
        this.cardButtonColor = cardButtonColor;
    }

    public String getCardButtonTextColor() {
        return cardButtonTextColor;
    }

    public void setCardButtonTextColor(String cardButtonTextColor) {
        this.cardButtonTextColor = cardButtonTextColor;
    }

    public String getCardFontFamily() {
        return cardFontFamily;
    }

    public void setCardFontFamily(String cardFontFamily) {
        this.cardFontFamily = cardFontFamily;
    }
}
