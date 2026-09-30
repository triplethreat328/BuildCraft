package buildcraft.core.block;

import java.util.List;

import buildcraft.core.BCCoreItems;
import buildcraft.core.item.ItemBlockDecorated;
import buildcraft.lib.block.BlockBCBase_Neptune;
import buildcraft.lib.internal.enums.EnumDecoratedBlock;
import buildcraft.lib.internal.properties.BuildCraftProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext.Builder;

/**
 * Classic BuildCraft decorated block restored from the 1.12.2 implementation.
 */
public class BlockDecoration extends BlockBCBase_Neptune {
    public static final EnumProperty<EnumDecoratedBlock> DECORATED_TYPE = BuildCraftProperties.DECORATED_BLOCK;

    public BlockDecoration() {
        super(BlockBehaviour.Properties.of(Material.METAL)
            .strength(5.0F, 10.0F)
            .sound(SoundType.METAL)
            .requiresCorrectToolForDrops()
            .lightLevel(state -> state.getValue(DECORATED_TYPE).lightValue));
        registerDefaultState(stateDefinition.any().setValue(DECORATED_TYPE, EnumDecoratedBlock.DESTROY));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DECORATED_TYPE);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, Builder builder) {
        ItemBlockDecorated item = BCCoreItems.DECORATED_ITEM_MAP.get(state.getValue(DECORATED_TYPE));
        return item == null ? List.of() : List.of(new ItemStack(item));
    }
}
