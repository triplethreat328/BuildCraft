#!/usr/bin/env python3
"""Guard the 1.20.1 Forge target against losing classic BuildCraft content.

This is intentionally a source-level presence check, not a substitute for
GameTests or runtime smoke tests. It encodes the player-visible systems that
must continue to exist while the full classic-parity branch is developed.
"""

from pathlib import Path
import sys

ROOT = Path(__file__).resolve().parents[1]

REQUIRED = {
    "source-families/legacy/src/main/java/buildcraft/core/BCCoreBlocks.java": [
        'register("engine"',
        "EnumEngineType.WOOD",
        "EnumEngineType.STONE",
        "EnumEngineType.IRON",
        'register("spring"',
        'register("decorated"',
        'register("marker_path"',
        'register("marker_volume"',
    ],
    "version-src/1.20.1-forge/src/main/java/buildcraft/core/BCCoreItems.java": [
        'register("engine_redstone"',
        'register("decorated/"',
        "EnumDecoratedBlock.values()",
        'register("spring_water"',
        'register("spring_oil"',
        'register("marker_path"',
        'register("marker_volume"',
    ],
    "source-shared/src/main/java/buildcraft/builders/BCBuildersBlocks.java": [
        'register("filler"',
        'register("builder"',
        'register("architect"',
        'register("library"',
        'register("replacer"',
        'register("marker_construction"',
        'register("frame"',
        'register("quarry"',
    ],
    "source-families/legacy/src/main/java/buildcraft/factory/BCFactoryBlocks.java": [
        'register("pump"',
        'register("tank"',
        'register("chute"',
        'register("flood_gate"',
        'register("tube"',
        'register("mining_well"',
        'register("distiller"',
        'register("heat_exchange"',
        'register("water_gel"',
        'register("autoworkbench_item"',
    ],
    "source-shared/src/main/java/buildcraft/silicon/BCSiliconBlocks.java": [
        'register("laser"',
        'register("assembly_table"',
        'register("advanced_crafting_table"',
        'register("integration_table"',
        'register("charging_table"',
        'register("programming_table"',
    ],
    "source-shared/src/main/java/buildcraft/robotics/BCRoboticsBlocks.java": [
        'register("zone_planner"',
        'register("requester"',
    ],
    "version-src/1.20.1-forge/src/main/java/buildcraft/robotics/BCRoboticsItems.java": [
        'register("robot"',
        'register("robot_station"',
        'register("redstone_board"',
    ],
    "source-families/legacy/src/main/java/buildcraft/robotics/BCRoboticsBoards.java": [
        '"picker"',
        '"carrier"',
        '"fluid_carrier"',
        '"lumberjack"',
        '"harvester"',
        '"miner"',
        '"planter"',
        '"farmer"',
        '"leave_cutter"',
        '"butcher"',
        '"shovelman"',
        '"pump"',
        '"delivery"',
        '"knight"',
        '"bomber"',
        '"stripes"',
        '"builder"',
    ],
    "source-families/legacy/src/main/java/buildcraft/transport/BCTransportPipes.java": [
        'idTexPrefix("wood_item")',
        'idTex("stone_item")',
        'idTex("cobblestone_item")',
        'idTex("quartz_item")',
        'idTexPrefix("gold_item")',
        'idTex("sandstone_item")',
        'idTexPrefix("iron_item")',
        'idTexPrefix("diamond_item")',
        'idTexPrefix("diamond_wood_item")',
        'idTex("clay_item")',
        'idTex("void_item")',
        'idTex("obsidian_item")',
        'idTexPrefix("lapis_item")',
        'idTexPrefix("daizuli_item")',
        'idTexPrefix("emzuli_item")',
        'idTex("stripes_item")',
        ".flowFluid()",
        ".flowPower()",
    ],
    "version-src/1.20.1-forge/src/main/java/buildcraft/energy/generation/features/OilGenerator.java": [
        "class OilGenerator",
        "smallOilGenProb",
        "mediumOilGenProb",
        "largeOilGenProb",
    ],
    "version-src/1.20.1-forge/src/main/java/buildcraft/energy/generation/features/OilStructure.java": [
        "class OilStructure",
        "TileSpringOil",
    ],
    "source-shared/src/main/resources/assets/buildcraftcore/blockstates/decorated.json": [
        "decoration_type=destroy",
        "decoration_type=blueprint",
        "decoration_type=template",
        "decoration_type=paper",
        "decoration_type=leather",
        "decoration_type=laser_back",
    ],
}

REQUIRED_PATHS = [
    "source-families/legacy/src/main/resources/data/buildcraftenergy/worldgen/configured_feature/oil_configured_feature.json",
    "source-families/legacy/src/main/resources/data/buildcraftenergy/worldgen/placed_feature/oil_placed_feature.json",
    "source-shared/src/main/resources/assets/buildcraftcore/models/item/decorated/destroy.json",
    "source-shared/src/main/resources/assets/buildcraftcore/models/item/decorated/blueprint.json",
    "source-shared/src/main/resources/assets/buildcraftcore/models/item/decorated/template.json",
    "source-shared/src/main/resources/assets/buildcraftcore/models/item/decorated/paper.json",
    "source-shared/src/main/resources/assets/buildcraftcore/models/item/decorated/leather.json",
    "source-shared/src/main/resources/assets/buildcraftcore/models/item/decorated/laser_back.json",
]

errors = []

for relative, needles in REQUIRED.items():
    path = ROOT / relative
    if not path.is_file():
        errors.append(f"missing required parity file: {relative}")
        continue
    text = path.read_text(encoding="utf-8")
    for needle in needles:
        if needle not in text:
            errors.append(f"{relative}: missing classic parity marker {needle!r}")

for relative in REQUIRED_PATHS:
    if not (ROOT / relative).is_file():
        errors.append(f"missing required classic resource: {relative}")

if errors:
    print("Classic BuildCraft 1.12 content parity guard FAILED:", file=sys.stderr)
    for error in errors:
        print(f" - {error}", file=sys.stderr)
    raise SystemExit(1)

print("Classic BuildCraft 1.12 content parity guard passed.")
