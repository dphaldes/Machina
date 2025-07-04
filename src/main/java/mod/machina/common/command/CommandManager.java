package mod.machina.common.command;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import mod.machina.api.augment.Perk;
import mod.machina.common.armor.ArsenalManager;
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
                .then(Commands.literal("equipped")
                        .then(Commands.literal("get")
                                .then(Commands.argument(PLAYER, EntityArgument.player())
                                        .executes(CommandManager::getGears)
                                        .requires(GameMaster)
                                )
                        )
                ).then(Commands.literal("perks")
                        .then(Commands.literal("get")
                                .then(Commands.argument(PLAYER, EntityArgument.player())
                                        .executes(CommandManager::getPerks)
                                        .requires(GameMaster)
                                )
                        )
                );
    }

    private static int getGears(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(context, PLAYER);
        var augments = ArsenalManager.getArsenal(player).unlocked();
        context.getSource().sendSuccess(() -> Component.literal(augments.toString()), true);
        return 0;
    }

    private static int getPerks(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        ServerPlayer player = EntityArgument.getPlayer(context, PLAYER);
        var perks = ArsenalManager.getArsenal(player).perks();
        context.getSource().sendSuccess(() -> Component.literal(perks.toString()), true);
        return 0;
    }

}
