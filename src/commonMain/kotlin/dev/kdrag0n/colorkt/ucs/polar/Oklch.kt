package dev.kdrag0n.colorkt.ucs.polar

import dev.kdrag0n.colorkt.ucs.lab.Oklab
import dev.kdrag0n.colorkt.ucs.polar.Lch.Companion.calcLabA
import dev.kdrag0n.colorkt.ucs.polar.Lch.Companion.calcLabB
import dev.kdrag0n.colorkt.ucs.polar.Lch.Companion.calcLchC
import dev.kdrag0n.colorkt.ucs.polar.Lch.Companion.calcLchH
import dev.kdrag0n.colorkt.util.conversion.ConversionGraph
import dev.kdrag0n.colorkt.util.conversion.ConversionProvider
import kotlin.jvm.JvmName
import kotlin.jvm.JvmStatic

/**
 * Polar (LCh) representation of [dev.kdrag0n.colorkt.ucs.lab.Oklab].
 *
 * @see dev.kdrag0n.colorkt.ucs.polar.Lch
 */
public data class Oklch(
    override val L: Double,
    override val C: Double,
    override val h: Double,
) : Lch {
    /**
     * Convert this color to the Cartesian (Lab) representation of Oklab.
     *
     * @see dev.kdrag0n.colorkt.ucs.lab.Lab
     * @return Color represented as Oklab
     */
    public fun toOklab(): Oklab = Oklab(
        L = L,
        a = calcLabA(),
        b = calcLabB(),
    )

    public companion object : ConversionProvider {
        override fun register() {
            ConversionGraph.add<Oklab, Oklch> { it.toOklch() }
            ConversionGraph.add<Oklch, Oklab> { it.toOklab() }
        }

        /**
         * Convert this color to the polar (LCh) representation of Oklab.
         *
         * @see dev.kdrag0n.colorkt.ucs.polar.Lch
         * @return Color represented as OkLCh
         */
        @JvmStatic
        @JvmName("fromOklab")
        public fun Oklab.toOklch(): Oklch = Oklch(
            L = L,
            C = calcLchC(),
            h = calcLchH(),
        )
    }
}
