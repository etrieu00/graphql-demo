CREATE TABLE ${flyway:defaultSchema}.digimon_info
(
    id              BIGSERIAL PRIMARY KEY,
    identifier      UUID         NOT NULL,
    name            VARCHAR(255) NOT NULL,
    evolution_stage VARCHAR(255) NOT NULL,
    digimon_type    VARCHAR(255) NOT NULL,
    attribute_type  VARCHAR(255) NOT NULL,
    memory          INTEGER      NOT NULL,
    equip_slots     INTEGER      NOT NULL
);

CREATE TABLE ${flyway:defaultSchema}.digimon_move
(
    id             BIGSERIAL PRIMARY KEY,
    identifier     UUID          NOT NULL,
    name           VARCHAR(255)  NOT NULL,
    sp_cost        INTEGER       NOT NULL,
    move_type      VARCHAR(255)  NOT NULL,
    power          INTEGER       NOT NULL,
    attribute_type VARCHAR(255)  NOT NULL,
    inheritable    BOOLEAN       NOT NULL,
    description    VARCHAR(2550) NOT NULL
);

CREATE TABLE ${flyway:defaultSchema}.digimon_ability
(
    id          BIGSERIAL PRIMARY KEY,
    identifier  UUID          NOT NULL,
    name        VARCHAR(255)  NOT NULL,
    description VARCHAR(2550) NOT NULL
);

CREATE TABLE ${flyway:defaultSchema}.digimon_status
(
    id         BIGSERIAL PRIMARY KEY,
    identifier UUID    NOT NULL,
    level      INTEGER NOT NULL,
    stat_hp    INTEGER NOT NULL,
    stat_sp    INTEGER NOT NULL,
    stat_atk   INTEGER NOT NULL,
    stat_def   INTEGER NOT NULL,
    stat_int   INTEGER NOT NULL,
    stat_spd   INTEGER NOT NULL
)