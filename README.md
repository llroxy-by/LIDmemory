# LIDmemory

Minecraft 1.20.1 relic mod for Fabric and Forge.

This repository builds two separate mod files:
- Fabric jar: uses Fabric API and Trinkets.
- Forge jar: uses Forge and Curios API.

The shared relic behavior lives in `common`, while loader-specific registration and accessory API integration lives in `fabric` and `forge`.

## Current Relics

| Name | Effect | Equip Slot |
|------|--------|------------|
| **llroxy鐨勯仐鐗?* | 澶滆 (Night Vision) | Trinkets: Necklace / Curios: Charm |
| **IEggCute鐨勯仐鐗?* | 姘翠笅鍛煎惛 (Water Breathing) | Trinkets: Necklace / Curios: Charm |
| **Mango鐨勯仐鐗?* | 鎶楃伀 (Fire Resistance) | Trinkets: Necklace / Curios: Charm |

## Relic Workbench

A custom crafting GUI with 4 input slots (2脳2 grid) for assembling relics.  
Place materials in the slots and combine them to craft the relic items.

## Build

Double-click `Build-Mod.bat`, or run from terminal:

```powershell
.\Build-Mod.ps1
```

The outputs will be in:
- `fabric\build\libs\lidmemory-fabric-0.1.0.jar`
- `forge\build\libs\lidmemory-forge-0.1.0.jar`

### Dependencies

- **Fabric version**: Fabric API + Trinkets
- **Forge version**: Curios API

## Project Structure

```
common/          - Shared relic effects, language files, models, textures
fabric/          - Fabric loader registration, Trinkets integration, Fabric GUI classes
forge/           - Forge loader registration, Curios integration, Forge GUI classes
```
