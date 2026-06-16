# LIDmemory

**LID自制mod-by llroxy**

Minecraft 1.20.1 遗物模组，同时支持 Fabric 和 Forge。

本仓库构建出两个独立的模组文件：
- Fabric 版：基于 Fabric API + Trinkets
- Forge 版：基于 Forge + Curios API

共用逻辑放在 `common` 目录，各加载器的注册与饰品 API 对接分别放在 `fabric` 和 `forge` 目录。

## 当前遗物

| 名称 | 效果 | 装备栏位 |
|------|------|----------|
| **llroxy的遗物** | 夜视 | Trinkets: 项链 / Curios: 护符 |
| **IEggCute的遗物** | 水下呼吸 | Trinkets: 项链 / Curios: 护符 |
| **Mango的遗物** | 抗火 | Trinkets: 项链 / Curios: 护符 |
| **AX的遗物** | 允许飞行 | Trinkets: 项链 / Curios: 护符 |

## 遗物工作台

一个带有 5×5 输入格 + 1 个输出格的自定义合成 GUI。放入材料即可合成遗物。关闭界面时物品会返还到背包。

## 构建

双击 `Build-Mod.bat`，或在终端中运行：

```powershell
.\Build-Mod.ps1
```

构建产物位于：
- `fabric\build\libs\lidmemory-fabric-beta0.1.jar`
- `forge\build\libs\lidmemory-forge-beta0.1.jar`

### 依赖

- **Fabric**：Fabric API + Trinkets
- **Forge**：Curios API

## 项目结构

```
common/          - 共用遗物效果、语言文件、模型、纹理
fabric/          - Fabric 加载器注册、Trinkets 集成、Fabric GUI
forge/           - Forge 加载器注册、Curios 集成、Forge GUI
```