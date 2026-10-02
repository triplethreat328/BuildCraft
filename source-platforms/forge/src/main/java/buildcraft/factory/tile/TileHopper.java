package buildcraft.factory.tile;

import buildcraft.factory.BCFactoryBlocks;
import buildcraft.factory.container.ContainerHopper;
import buildcraft.lib.internal.core.EnumPipePart;
import buildcraft.lib.inventory.ItemTransactorHelper;
import buildcraft.lib.inventory.NoSpaceTransactor;
import buildcraft.lib.tile.TileBC_Neptune;
import buildcraft.lib.tile.item.ItemHandlerManager.EnumAccess;
import buildcraft.lib.tile.item.ItemHandlerSimple;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;

/** Runtime for the classic four-slot BuildCraft hopper. */
public class TileHopper extends TileBC_Neptune implements MenuProvider {
    public final ItemHandlerSimple inv = itemManager.addInvHandler(
        "inv", 4, EnumAccess.BOTH, EnumPipePart.VALUES
    );

    public TileHopper(BlockPos pos, BlockState state) {
        super(BCFactoryBlocks.ENTITYBLOCKHOPPER.get(), pos, state);
    }

    @Override
    public void update() {
        if (level == null || level.isClientSide || inv.isEmpty() || (level.getGameTime() & 1L) != 0L) {
            return;
        }
        BlockEntity below = level.getBlockEntity(worldPosition.below());
        var destination = ItemTransactorHelper.getTransactor(below, Direction.UP);
        if (destination != NoSpaceTransactor.INSTANCE) {
            ItemTransactorHelper.move(inv, destination, 1);
        }
    }

    @Override
    public InteractionResult onActivated(Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide && player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, this, worldPosition);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new ContainerHopper(id, inventory, inv, ContainerLevelAccess.create(level, worldPosition));
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(getBlockState().getBlock().getDescriptionId());
    }
}
