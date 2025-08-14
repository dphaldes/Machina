package mod.machina.data.client;


import mod.machina.Machina;
import mod.machina.common.block.RiftBlock;
import mod.machina.common.registrar.BlockRegistrar;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateProvider extends net.neoforged.neoforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Machina.ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Rift portal
        var rift = models().getExistingFile(modLoc("block/rift"));
        getVariantBuilder(BlockRegistrar.RIFT_PORTAL.block())
                .partialState().with(RiftBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(rift).addModel()
                .partialState().with(RiftBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(rift).rotationY(90).addModel();

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
