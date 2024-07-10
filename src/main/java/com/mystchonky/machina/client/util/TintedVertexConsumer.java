package com.mystchonky.machina.client.util;

import com.mojang.blaze3d.vertex.VertexConsumer;

public final class TintedVertexConsumer implements VertexConsumer {
    private final VertexConsumer wrapped;
    private final float red;
    private final float green;
    private final float blue;
    private final float alpha;

    public TintedVertexConsumer(VertexConsumer wrapped, float red, float green, float blue, float alpha) {
        this.wrapped = wrapped;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
    }

    @Override
    public VertexConsumer addVertex(float x, float y, float z) {
        return wrapped.addVertex(x, y, z);
    }

    @Override
    public VertexConsumer setColor(int red, int green, int blue, int alpha) {
        return wrapped.setColor((int) (red * this.red), (int) (green * this.green), (int) (blue * this.blue), (int) (alpha * this.alpha));
    }

    @Override
    public VertexConsumer setUv(float u, float v) {
        return wrapped.setUv(u, v);
    }

    @Override
    public VertexConsumer setUv1(int u, int v) {
        return wrapped.setUv1(u, v);
    }

    @Override
    public VertexConsumer setUv2(int u, int v) {
        return wrapped.setUv2(u, v);
    }

    @Override
    public VertexConsumer setNormal(float x, float y, float z) {
        return wrapped.setNormal(x, y, z);
    }

}
