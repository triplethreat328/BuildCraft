package buildcraft.core.item;

import buildcraft.core.block.BlockDecoration;
import buildcraft.lib.internal.enums.EnumDecoratedBlock;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Modern replacement for the metadata-based decorated block item from 1.12.2.
 */
public class ItemBlockDecorated extends BlockItem {
    private final EnumDecoratedBlock type;

    public ItemBlockDecorated(Block block, Properties properties, EnumDecoratedBlock type) {
        super(block, properties);
        this.type = type;
    }

    @Override
    protected BlockState getPlacementState(BlockPlaceContext context) {
        BlockState state = super.getPlacementState(context);
        return state == null ? null : state.setValue(BlockDecoration.DECORATED_TYPE, type);
    }

    @Override
    public Component getName(ItemStack stack) {
        return Component.translatable("block.decorated." + type.getSerializedName());
    }

    public EnumDecoratedBlock getType() {
        return type;
    }
}
