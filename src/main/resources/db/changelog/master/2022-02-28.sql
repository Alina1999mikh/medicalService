CREATE TABLE NOTES
(
    ID              bigserial PRIMARY KEY,
    UUID            UUID,
    LAB             VARCHAR(512),
    FIRST_NAME      VARCHAR(512),
    SECOND_NAME     VARCHAR(512),
    TEST            VARCHAR(512),
    DATE            DATE,
    RESULT          VARCHAR(512),
    REFERENCE_RANGE VARCHAR(512),
    UNIT            VARCHAR(512),
    COMMENT         TEXT
)