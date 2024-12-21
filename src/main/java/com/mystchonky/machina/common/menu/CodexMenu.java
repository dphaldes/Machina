package com.mystchonky.machina.common.menu;

import com.mystchonky.machina.common.registrar.BlockRegistrar;
import com.mystchonky.machina.common.registrar.MenuRegistrar;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class CodexMenu extends AbstractContainerMenu {
    protected final ContainerLevelAccess access;

    // server
    public CodexMenu(int containerId, Inventory playerInventory, ContainerLevelAccess access) {
        super(MenuRegistrar.CODEX.get(), containerId);
        this.access = access;


        // player hotbar
        for (int k = 0; k < 9; k++) {
            this.addSlot(new Slot(playerInventory, k, 28 + k * 18, 195));
        }

        //player inventory
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 28 + j * 18, 139 + i * 18));
            }
        }


    }

    // client
    public CodexMenu(int containerId, Inventory playerInventory) {
        this(containerId, playerInventory, ContainerLevelAccess.NULL);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        // TODO
        return ItemStack.EMPTY;
    }

    @Override
    public boolean stillValid(Player player) {
        return AbstractContainerMenu.stillValid(this.access, player, BlockRegistrar.CODEX.block());
    }
}
