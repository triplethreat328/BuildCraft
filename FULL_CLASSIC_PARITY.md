# Full Classic BuildCraft Parity Plan

Target: **Minecraft 1.20.1 + Forge 47.4.10 + Java 17**

Working branch: `full-classic-parity`

Reference implementation: BuildCraft 8.x for Minecraft 1.12.2, with older 7.1.27 behaviour restored where Community Edition intentionally carries it forward.

## Definition of done

This project is not considered complete just because it compiles or launches. A feature is complete only when it is usable in normal survival gameplay, persists correctly through save/load and chunk unloads, works in multiplayer/dedicated server use, and has the expected recipes, models/textures, GUI behaviour, automation behaviour, sounds/animations where applicable, and interoperability expected from classic BuildCraft.

The primary compatibility rule is: **different implementation, indistinguishable BuildCraft**.

## Core / world systems

- [ ] Core registration and module lifecycle verified
- [ ] Wrenches and tools verified
- [ ] Volume markers / landmarks verified
- [ ] Path markers verified
- [ ] Construction markers verified
- [ ] Blueprint/template serialization verified
- [ ] Chunk loading/tickets verified
- [ ] Permissions/protection interactions verified
- [ ] Oil world generation verified against classic BuildCraft behaviour
- [ ] Oil springs verified
- [ ] Water spring behaviour verified where applicable
- [ ] All BuildCraft fluids and temperature states verified
- [ ] World save/load migration and persistence verified

> Note: classic BuildCraft does not add the usual copper/tin/lead ore set. BuildCraft's own natural-resource generation is primarily oil deposits/spouts/springs. Do not invent non-BuildCraft ores for parity.

## Transport

- [ ] Wooden item pipe
- [ ] Cobblestone item pipe
- [ ] Stone item pipe
- [ ] Quartz item pipe
- [ ] Iron item pipe
- [ ] Gold item pipe
- [ ] Diamond item pipe
- [ ] Emerald item pipe
- [ ] Lapis / Daizuli routing behaviour where present in the reference
- [ ] Obsidian / extraction-style special pipes where present in the reference
- [ ] Void / insertion / clay-style special routing where present
- [ ] Fluid pipe family
- [ ] Kinesis / MJ power pipe family
- [ ] Pipe item movement, acceleration and routing
- [ ] Pipe fluid movement and capacity
- [ ] MJ power transport and loss/limits
- [ ] Pipe connections and disconnections
- [ ] Pipe plugs
- [ ] Facades
- [ ] Pipe wires
- [ ] Gates
- [ ] Gate statements/triggers/actions
- [ ] Gate expansion/modifiers
- [ ] Pulsar / timer plugs
- [ ] Filters / lenses where applicable
- [ ] Cross-chunk pipe networks
- [ ] Save/load of in-flight items and network state
- [ ] Forge inventory/fluid interoperability without changing classic behaviour

## Energy

- [ ] Redstone Engine
- [ ] Stirling Engine
- [ ] Combustion Engine
- [ ] Engine heat states
- [ ] Engine overheat/explosion behaviour
- [ ] MJ generation
- [ ] MJ storage/buffering
- [ ] MJ consumer behaviour
- [ ] Fuel recipes
- [ ] Oil/fuel refining chain
- [ ] Coolants
- [ ] FE bridge remains optional compatibility, not a replacement for MJ

## Factory

- [ ] Mining Well
- [ ] Pump
- [ ] Tank
- [ ] Flood Gate
- [ ] Auto Workbench
- [ ] Distiller
- [ ] Heat Exchange / Heat Exchanger system
- [ ] Refining/fluid processing behaviour
- [ ] Machine sidedness
- [ ] Machine inventories/tanks
- [ ] Machine redstone behaviour
- [ ] Machine persistence
- [ ] Machine chunk-unload behaviour

## Builders

- [ ] Quarry
- [ ] Quarry frame construction
- [ ] Quarry laser/drill animation
- [ ] Quarry item output
- [ ] Quarry liquids/obstruction behaviour
- [ ] Quarry landmarks/working area
- [ ] Builder
- [ ] Filler
- [ ] All classic Filler patterns
- [ ] Replacer
- [ ] Architect Table
- [ ] Electronic Library
- [ ] Blueprint creation
- [ ] Template creation
- [ ] Blueprint placement/building
- [ ] Construction marker behaviour
- [ ] Builder resource accounting/refunds
- [ ] Builder fake-player/protection handling

## Silicon

- [ ] Laser
- [ ] Assembly Table
- [ ] Integration Table
- [ ] Programming Table
- [ ] Charging Table
- [ ] Chipsets
- [ ] Gate assembly recipes
- [ ] Gate programming
- [ ] Lenses
- [ ] Filters
- [ ] Laser power targeting/animation
- [ ] Table GUIs and recipe selection
- [ ] JEI integration where supported

## Robotics

- [ ] Robot entity
- [ ] Robot item
- [ ] Robot Station
- [ ] Redstone Boards
- [ ] Board programming
- [ ] Robot pathfinding
- [ ] Robot charging
- [ ] Robot item fetching/delivery
- [ ] Robot harvesting
- [ ] Robot planting
- [ ] Robot block breaking
- [ ] Robot fluid pumping
- [ ] Robot loading/unloading
- [ ] Requester
- [ ] Zone Planner
- [ ] Zone map persistence
- [ ] Robot ownership/fake-player identity
- [ ] Cross-chunk robot behaviour
- [ ] Robot save/load and recovery

## Client / presentation

- [ ] Every classic block/item has a model
- [ ] Every classic machine GUI is functional
- [ ] Pipe rendering verified
- [ ] Fluid-in-pipe rendering verified
- [ ] Engine animation verified
- [ ] Quarry animation verified
- [ ] Laser rendering verified
- [ ] Robot rendering verified
- [ ] Marker/area rendering verified
- [ ] Sounds verified
- [ ] Tooltips/localization verified

## Server / compatibility acceptance

- [ ] Single-player smoke test
- [ ] Dedicated Forge server smoke test
- [ ] Two-player multiplayer interaction test
- [ ] Save/restart persistence test
- [ ] Chunk unload/reload test
- [ ] Nether/End dimension test
- [ ] JEI compatibility
- [ ] Jade compatibility
- [ ] Create compatibility where declared
- [ ] Forestry compatibility where declared
- [ ] Forge Energy compatibility where declared
- [ ] No client-only classes loaded on dedicated server

## Current source audit notes

The Community Edition repository already contains substantial modern implementations for Quarry, Builder/Filler/Replacer, Architect Table, Electronic Library, Mining Well, Pump, Flood Gate, Distiller, Heat Exchange, oil/springs, robots, Requester, Zone Planner, Silicon tables, gates, pipes and compatibility layers.

Presence in the source tree does **not** mean parity is assumed. Every subsystem above still requires behavioural validation against the classic reference before its checkbox is closed.

## First implementation/testing order

1. Make the 1.20.1 Forge target build reproducibly on the parity branch.
2. Run existing source/parity/regression checks.
3. Verify oil/spring world generation.
4. Verify MJ + engines + kinesis transport.
5. Verify item/fluid pipes and gates.
6. Verify Quarry/Mining Well/Pump.
7. Verify Builder/Filler/Replacer/blueprints.
8. Verify Silicon/lasers/tables.
9. Verify Robotics/Requester/Zone Planner.
10. Finish visual, persistence, dedicated-server and compatibility acceptance.

## Classic 7.1.27 restoration baseline

The final 1.20.1 Forge target is not limited to the active BC8/1.12.2 registration set. For the user's
"full classic BuildCraft" target, parity is the union of:

- active BuildCraft 8.x / 1.12.2 content and behaviour, and
- player-facing BuildCraft 7.1.27 content that was retired or replaced during BC8, where it can coexist without
  breaking the BC8 implementation.

Verified 7.1.x player-facing content that is not currently registered in the 1.20.1 target includes:

- Factory: classic Refinery and BuildCraft Hopper.
- Silicon: Packager and Package item.
- Transport: Emerald item, fluid and power pipe families.
- Core/utilities: classic Build Tool block, Tablet and Debugger require a separate player-facing audit before
  deciding their modern placement/recipes.
- Legacy factory "plain pipe" requires behaviour mapping against the BC8 Tube before deciding whether it is a
  distinct restoration or a renamed/reworked equivalent.

Do not remove BC8 replacements such as Distiller and Heat Exchange when restoring older machines; classic machines
should coexist so the finished port contains the broader classic BuildCraft experience.

