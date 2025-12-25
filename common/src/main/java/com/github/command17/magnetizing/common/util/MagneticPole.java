package com.github.command17.magnetizing.common.util;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import org.jspecify.annotations.NonNull;

import java.util.function.IntFunction;

public enum MagneticPole implements StringRepresentable {
    POSITIVE(0, "positive") /* ATTRACTS */,
    NEGATIVE(1, "negative") /* REPELS */;

    private static final IntFunction<MagneticPole> BY_ID = ByIdMap.continuous(MagneticPole::getId, values(), ByIdMap.OutOfBoundsStrategy.ZERO);
    public static final StreamCodec<ByteBuf, MagneticPole> STREAM_CODEC = ByteBufCodecs.idMapper(BY_ID, MagneticPole::getId);

    @SuppressWarnings("deprecated")
    public static final StringRepresentable.EnumCodec<MagneticPole> CODEC = StringRepresentable.fromEnum(MagneticPole::values);

    private final String entryName;
    private final int entryId;

    MagneticPole(int id, String name) {
        this.entryName = name;
        this.entryId = id;
    }

    public int getId() {
        return entryId;
    }

    @NonNull
    @Override
    public String getSerializedName() {
        return this.entryName;
    }
}
