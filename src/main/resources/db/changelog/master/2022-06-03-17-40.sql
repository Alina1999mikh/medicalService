CREATE TABLE "NOTES"
(
    ID              bigserial PRIMARY KEY,
    USERNAME        VARCHAR(32),
    UUID            UUID,
    LAB             VARCHAR(512),
    TEST            VARCHAR(512),
    DATE            DATE,
    RESULT          VARCHAR(512),
    REFERENCE_RANGE VARCHAR(512),
    UNIT            VARCHAR(512),
    COMMENT         TEXT
);
DROP INDEX IF EXISTS IND;
CREATE UNIQUE INDEX ind
    ON "NOTES" (UUID);

CREATE TABLE "PROFILES"
(
    ID            bigserial PRIMARY KEY,
    USERNAME      VARCHAR(32),
    DATE DATE,
    F_NAME        VARCHAR(32),
    S_NAME        VARCHAR(32),
    GENDER        char,
    FOREIGN KEY (USERNAME) REFERENCES users (username)
);
DROP INDEX IF EXISTS user_ind;
CREATE UNIQUE INDEX user_ind
    ON "PROFILES" (USERNAME);