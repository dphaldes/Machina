package com.mystchonky.machina.common.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mystchonky.machina.api.armament.gear.AbstractGear;
import com.mystchonky.machina.common.armament.gear.UnlockedGears;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.List;
import java.util.function.Predicate;

public class CommandManager {
    private static final Predicate<CommandSourceStack> GameMaster = sender -> sender.hasPermission(2);
    private static final String PLAYER = "player";

    public static LiteralArgumentBuilder<CommandSourceStack> register() {
        return Commands.literal("machina")
                .then(Commands.literal("gears")
                        .then(Commands.literal("get_unlocked")
                                .then(Commands.argument(PLAYER, EntityArgument.player())
                                        .executes(CommandManager::getUnlockedGears)
                                        .requires(GameMaster)
                                )
                        ));
    }

    private static int getUnlockedGears(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(context, PLAYER);
        List<AbstractGear> gears = UnlockedGears.get(player).gears();
        context.getSource().sendSuccess(() -> Component.literal(gears.toString()), true);
        return 0;
    }

}
