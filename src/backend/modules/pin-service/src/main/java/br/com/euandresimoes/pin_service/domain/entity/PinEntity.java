package br.com.euandresimoes.pin_service.domain.entity;

import br.com.euandresimoes.pin_service.domain.entity.enums.PinType;
import br.com.euandresimoes.pin_service.domain.entity.models.StyleModel;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Entity
@Table(name = "pins_tb")
public class PinEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "pin_type")
    @Enumerated(EnumType.STRING)
    private PinType type;
    @Column(name = "pin_title")
    private String pinTitle;
    @Column(name = "pin_link")
    private String pinLink;
    @Column(name = "pin_is_public")
    private boolean pinIsPublic;
    @Column(name = "pin_clicks")
    private Long pinClicks = 0L;
    @Column(name = "pin_created_date")
    @CreationTimestamp
    private Instant createdDate;
    @Column(name = "pin_creator_id")
    private Long pinCreatorId;
    @Column(name = "pin_creator_username")
    private String pinCreatorUsername;
    @Column(name = "pin_creator_display_name")
    private String pinCreatorDisplayName;
    @Embedded
    private StyleModel style;

    public PinEntity() {
    }

    public PinEntity(PinType type, String pinTitle, String pinLink, boolean pinIsPublic, Long pinCreatorId, String pinCreatorUsername, String pinCreatorDisplayName, StyleModel style) {
        this.type = type;
        this.pinTitle = pinTitle;
        this.pinLink = pinLink;
        this.pinIsPublic = pinIsPublic;
        this.pinCreatorId = pinCreatorId;
        this.pinCreatorUsername = pinCreatorUsername;
        this.pinCreatorDisplayName = pinCreatorDisplayName;
        this.style = style;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PinType getType() {
        return type;
    }

    public void setType(PinType type) {
        this.type = type;
    }

    public String getPinTitle() {
        return pinTitle;
    }

    public void setPinTitle(String pinTitle) {
        this.pinTitle = pinTitle;
    }

    public String getPinLink() {
        return pinLink;
    }

    public void setPinLink(String pinLink) {
        this.pinLink = pinLink;
    }

    public boolean isPinIsPublic() {
        return pinIsPublic;
    }

    public void setPinIsPublic(boolean pinIsPublic) {
        this.pinIsPublic = pinIsPublic;
    }

    public Long PinClicks() {
        return pinClicks;
    }

    public void increasePinClicks() {
        pinClicks += 1;
    }

    public Long getPinCreatorId() {
        return pinCreatorId;
    }

    public void setPinCreatorId(Long pinCreatorId) {
        this.pinCreatorId = pinCreatorId;
    }

    public String getPinCreatorUsername() {
        return pinCreatorUsername;
    }

    public void setPinCreatorUsername(String pinCreatorUsername) {
        this.pinCreatorUsername = pinCreatorUsername;
    }

    public String getPinCreatorDisplayName() {
        return pinCreatorDisplayName;
    }

    public void setPinCreatorDisplayName(String pinCreatorDisplayName) {
        this.pinCreatorDisplayName = pinCreatorDisplayName;
    }

    public StyleModel getStyle() {
        return style;
    }

    public void setStyle(StyleModel style) {
        this.style = style;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }
}
