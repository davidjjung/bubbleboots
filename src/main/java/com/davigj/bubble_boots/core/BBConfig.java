package com.davigj.bubble_boots.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class BBConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Integer> maxSoapiness;
        public final ForgeConfigSpec.ConfigValue<Integer> soapRestoreAmt;
        public final ForgeConfigSpec.ConfigValue<Integer> soapWarning;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("items");
            builder.push("bubble_boots");
            maxSoapiness = builder.comment("# of bubble blocks that boots may produce before needing a recharge").define("Maximum soapiness", 100);
            soapRestoreAmt = builder.comment("Amount of soapiness restored for each bar of soap used on boots").define("Restoration amount", 50);
            soapWarning = builder.comment("Boots with soapiness below this number start emitting more suds particles. Set to 0 for no warning threshold").define("Soapiness warning threshold", 15);
            builder.pop();
            builder.pop();
        }
    }

    static final ForgeConfigSpec COMMON_SPEC;
    public static final BBConfig.Common COMMON;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(BBConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }
}
