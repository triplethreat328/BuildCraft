package buildcraft.factory.container;

import buildcraft.factory.BCFactoryGuis;
import buildcraft.factory.tile.TileHopper;
import buildcraft.lib.gui.ContainerBCTile;
import buildcraft.lib.gui.slot.SlotBase;
import buildcraft.lib.tile.item.IItemHandlerAdv;
import buildcraft.lib.tile.item.ItemHandlerSimple;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;

/** Four-slot layout used by the classic BuildCraft hopper. */
public class ContainerHopper extends ContainerBCTile<TileHopper> {
    public ContainerHopper(int containerId, Inventory playerInventory, FriendlyByteBuf buffer) {
        this(containerId, playerInventory, new ItemHandlerSimple(4), createLevelAccess(playerInventory, buffer));
    }

    public ContainerHopper(int containerId, Inventory playerInventory, IItemHandlerAdv inventory,
        ContainerLevelAccess access) {
        super(BCFactoryGuis.MENU_HOPPER.get(), playerInventory, containerId, access);
        IItemHandlerAdv hopperInventory = tile != null ? tile.inv : inventory;

        addFullPlayerInventory(71);
        addSlot(new SlotBase(hopperInventory, 0, 62, 18));
        addSlot(new SlotBase(hopperInventory, 1, 80, 18));
        addSlot(new SlotBase(hopperInventory, 2, 98, 18));
        addSlot(new SlotBase(hopperInventory, 3, 80, 36));
    }
}
