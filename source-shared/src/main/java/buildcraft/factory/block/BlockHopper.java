package buildcraft.factory.block;

import buildcraft.factory.BCFactoryBlocks;
import buildcraft.lib.block.BlockBCTile_Neptune;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

/**
 * Classic BuildCraft 7.1 hopper.
 *
 * Kept separate from the BC8 chute: this is the four-slot downward-feeding machine.
 */
public class BlockHopper extends BlockBCTile_Neptune {
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return BCFactoryBlocks.ENTITYBLOCKHOPPER.get().create(pos, state);
    }
}
