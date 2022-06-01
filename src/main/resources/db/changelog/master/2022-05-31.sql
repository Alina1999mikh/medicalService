DROP TABLE "NOTES";

CREATE TABLE "NOTES"
(
    ID              bigserial PRIMARY KEY,
    USER_ID         BIGINT,
    UUID            UUID,
    LAB             VARCHAR(512),
    TEST            VARCHAR(512),
    DATE            DATE,
    RESULT          VARCHAR(512),
    REFERENCE_RANGE VARCHAR(512),
    UNIT            VARCHAR(512),
    COMMENT         TEXT
);
CREATE UNIQUE INDEX ind
ON "NOTES" (uuid);