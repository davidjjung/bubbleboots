package com.davigj.bubble_boots.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class BBConfig {
    public static class Common {
        public final ForgeConfigSpec.ConfigValue<Integer> maxSoapiness;
        public final ForgeConfigSpec.ConfigValue<Integer> soapRestoreAmt;
        public final ForgeConfigSpec.ConfigValue<Boolean> soapBlockRestoration;
        public final ForgeConfigSpec.ConfigValue<Boolean> bootCleaning;
        public final ForgeConfigSpec.ConfigValue<Boolean> armorerTrade;
        public final ForgeConfigSpec.ConfigValue<Boolean> slipAndSlide;

        Common(ForgeConfigSpec.Builder builder) {
            builder.push("items");
            builder.push("bubble_boots");
            maxSoapiness = builder.comment("# of bubble blocks that boots may produce before needing a recharge").define("Maximum soapiness", 75);
            soapRestoreAmt = builder.comment("Amount of soapiness restored for each bar of soap used on boots").define("Restoration amount", 25);
            soapBlockRestoration = builder.comment("Stepping on soap blocks restores soapiness to full.").define("Soap block full restore", true);
            bootCleaning = builder.comment("Standing in water or rain causes the boots to un-soap").define("Bubble boots wash off", false);
            slipAndSlide = builder.comment("Walking with soapy boats causes the player to slip, as if stepping on soap blocks").define("Slip and slide", false);
            builder.pop();
            builder.push("trading");
            armorerTrade = builder.comment("Armorers trade bubble boots in exchange for soap blocks, as an expert-level trade")
                    .define("Armorers sell bubble boots", true);
            builder.pop();
            builder.pop();
        }
    }

    public static class Client {
        public final ForgeConfigSpec.ConfigValue<Boolean> sudsyBoots;
        public final ForgeConfigSpec.ConfigValue<Integer> soapWarning;

        public Client(ForgeConfigSpec.Builder builder) {
            builder.push("particles");
            sudsyBoots = builder.comment("Do bubble boots emit sudsy particles while soapy").define("Sudsy boots", true);
            soapWarning = builder.comment("Boots with soapiness below this number start emitting more suds particles. Set to 0 for no warning threshold")
                    .define("Soapiness warning threshold", 15);
            builder.pop();
        }
    }

    static final ForgeConfigSpec COMMON_SPEC;
    public static final BBConfig.Common COMMON;

    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Client CLIENT;

    static {
        final Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(BBConfig.Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();

        Pair<Client, ForgeConfigSpec> clientSpecPair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = clientSpecPair.getRight();
        CLIENT = clientSpecPair.getLeft();
    }
}
