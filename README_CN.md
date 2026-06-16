# LIDmemory — LID自制mod

Minecraft 1.20.1 遗物模组 · 同时支持 Fabric 和 Forge（两个独立 jar 文件）

> by llroxy

## 遗物列表

| 遗物 | 效果 | 饰品槽位 |
|------|------|----------|
| **llroxy的遗物** | 夜视 | Fabric: 项链 / Forge: 护符 |
| **IEggCute的遗物** | 水下呼吸 | Fabric: 项链 / Forge: 护符 |
| **Mango的遗物** | 抗火 | Fabric: 项链 / Forge: 护符 |
| **AX的遗物** | 允许飞行 | Fabric: 项链 / Forge: 护符 |

## 遗物工作台

一个带 GUI 的工作台方块，拥有 5×5 输入格子和 1 个输出格子，用于合成遗物。

- 在工作台里面放入材料
- 退出时物品会自动回到背包，不会丢失
- 创造模式物品栏中可以找到

## 构建

双击 `Build-Mod.bat` 或者在终端执行：

```powershell
.\Build-Mod.ps1
```

输出文件：
- `fabric\build\libs\lidmemory-fabric-beta0.1.jar`
- `forge\build\libs\lidmemory-forge-beta0.1.jar`

## 依赖

- **Fabric 版**：Fabric API + Trinkets
- **Forge 版**：Curios API

## 项目结构

```
common/   — 共享的遗物效果、语言文件、模型、贴图
fabric/   — Fabric 注册、Trinkets 集成、Fabric GUI
forge/    — Forge 注册、Curios 集成、Forge GUI
```