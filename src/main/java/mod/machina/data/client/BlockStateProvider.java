package mod.machina.data.client;


import mod.machina.Machina;
import mod.machina.common.registrar.BlockRegistrar;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateProvider extends net.neoforged.neoforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Machina.ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        var projector = BlockRegistrar.RUNE_PROJECTOR.block();
        simpleBlockWithItem(projector, models().cubeAll(modLoc("rune_projector").getPath(), blockTexture(projector))
                .renderType(RenderType.CUTOUT.name));

        var runesmith = BlockRegistrar.RUNESMITH.block();
        var runesmith_base = blockTexture(runesmith);
        simpleBlockWithItem(
                runesmith, models().cubeTop(modLoc("runesmith").getPath(),
                        runesmith_base.withSuffix("_side"),
                        runesmith_base.withSuffix("_top"))
        );
    }
}
