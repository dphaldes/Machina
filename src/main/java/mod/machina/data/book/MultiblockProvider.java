package mod.machina.data.book;

import mod.machina.Machina;
import mod.machina.common.registrar.BlockRegistrar;
import mod.machina.common.registrar.TagRegistrar;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Blocks;

public class MultiblockProvider extends com.klikli_dev.modonomicon.api.datagen.MultiblockProvider {

    public MultiblockProvider(PackOutput packOutput) {
        super(packOutput, Machina.ID);
    }

    @Override
    public void buildMultiblocks() {
        add(modLoc("preview/rift"), new DenseMultiblockBuilder()
                .layer("D D")
                .layer("drd")
                .layer("drd")
                .layer("d0d")
                .block('D', () -> Blocks.POLISHED_DEEPSLATE)
                .tag('d', TagRegistrar.RIFT_PORTAL_FRAME, () -> Blocks.DEEPSLATE)
                .tag('0', TagRegistrar.RIFT_PORTAL_FRAME, () -> Blocks.DEEPSLATE)
                .blockstate('r', BlockRegistrar.RIFT_PORTAL.deferredBlock(), "[axis=z]")
        );
    }
}
