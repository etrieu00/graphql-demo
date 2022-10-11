import csv
import uuid

input = [
    "./input/DigiDB_digimonlist.csv",
    "./input/DigiDB_movelist.csv",
    "./input/DigiDB_supportlist.csv",
]

output = [
    "./output/V1_1_2__INSERT_DIGIMON.sql",
    "./output/V1_1_3__INSERT_MOVES.sql",
    "./output/V1_1_4__INSERT_ABILITIES.sql",
    "./output/V1_1_5__INSERT_STATUSES.sql",
]

digimonSQLScript = """INSERT INTO digidex.digimon_info (identifier, name, evolution_stage, digimon_type, attribute_type, memory, equip_slots)
VALUES ('{}', '{}', '{}', '{}', '{}', {}, {});
"""

digimonMoveSQLScript = """INSERT INTO digidex.digimon_move (identifier, name, sp_cost, move_type, power, attribute_type, inheritable, description)
VALUES('{}', '{}', {}, '{}', {}, '{}', {}, '{}');
"""

digimonAbilitySQLScript = """INSERT INTO digidex.digimon_ability (identifier, name, description)
VALUES('{}', '{}', '{}');
"""

digimonStatusesSQLScript = """INSERT INTO digidex.digimon_status (identifier, level, stat_hp, stat_sp, stat_atk, stat_def, stat_int, stat_spd)
VALUES ('{}', {}, {}, {}, {}, {}, {}, {});
"""

with open(input[0], "r") as digimonCSV, open(input[1], "r") as movesCSV, open(
    input[2], "r"
) as abilityCSV, open(output[0], "w") as digimonSQL, open(
    output[1], "w"
) as movesSQL, open(
    output[2], "w"
) as abilitiesSQL, open(
    output[3], "w"
) as statusesSQL:

    # Read files
    rawDigimon = csv.reader(digimonCSV)
    rawMoves = csv.reader(movesCSV)
    rawAbilities = csv.reader(abilityCSV)

    # Skip headers
    next(rawDigimon)
    for row in rawDigimon:
        identifier = uuid.uuid4()
        (
            name,
            evolution_stage,
            digimon_type,
            attribute_type,
            memory,
            equip_slots,
            *_,
        ) = row[1:]
        (stat_hp, stat_sp, stat_atk, stat_def, stat_int, stat_spd) = row[7:]
        sqlDigimon = digimonSQLScript.format(
            identifier,
            name.replace("'", "''"),
            evolution_stage.upper().replace('-','_'),
            digimon_type.upper().replace('-','_'),
            attribute_type.upper().replace('-','_'),
            memory,
            equip_slots,
        )
        sqlStatuses = digimonStatusesSQLScript.format(
            identifier, 100, stat_hp, stat_sp, stat_atk, stat_def, stat_int, stat_spd
        )
        digimonSQL.write(sqlDigimon)
        statusesSQL.write(sqlStatuses)

    # Skip headers
    next(rawMoves)
    for row in rawMoves:
        (
            name,
            sp_cost,
            move_type,
            power,
            attribute_type,
            inheritable,
            description,
        ) = row
        sqlMove = digimonMoveSQLScript.format(
            uuid.uuid4(),
            name.replace("'", "''"),
            sp_cost,
            move_type.upper().replace('-','_'),
            power,
            attribute_type.upper(),
            "true" if inheritable == "Yes" else "false",
            description.replace("'", "''"),
        )
        movesSQL.write(sqlMove)

    # Skip headers
    next(rawAbilities)
    for row in rawAbilities:
        (name, description) = row
        sqlAbilities = digimonAbilitySQLScript.format(
            uuid.uuid4(), name.replace("'", "''"), description.replace("'", "''")
        )
        abilitiesSQL.write(sqlAbilities)
