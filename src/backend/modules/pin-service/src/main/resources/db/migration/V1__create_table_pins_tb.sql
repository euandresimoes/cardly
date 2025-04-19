CREATE TABLE pins_tb (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    pin_type VARCHAR(30) NOT NULL,
    pin_title VARCHAR(100) NOT NULL,
    pin_link VARCHAR(500) NOT NULL,
    pin_is_public BOOLEAN NOT NULL,
    pin_clicks BIGINT NOT NULL,
    pin_created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    pin_creator_id BIGINT NOT NULL,
    pin_creator_username VARCHAR(20) NOT NULL,
    pin_creator_display_name VARCHAR(30) NOT NULL
);