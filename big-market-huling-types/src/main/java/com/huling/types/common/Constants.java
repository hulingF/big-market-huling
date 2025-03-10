package com.huling.types.common;

public class Constants {

    public final static String SPLIT = ",";
    public final static String COLON = ":";
    public final static String SPACE = " ";
    public final static String UNDERLINE = "_";

    public static class RedisKey {
        public static String ACTIVITY_KEY = "big_market_activity_key_";
        public static String ACTIVITY_SKU_KEY = "big_market_activity_sku_key_";
        public static String ACTIVITY_COUNT_KEY = "big_market_activity_count_key_";
        public static String STRATEGY_KEY = "big_market_strategy_key_";
        public static String STRATEGY_AWARD_KEY = "big_market_strategy_award_key_";
        public static String STRATEGY_AWARD_LIST_KEY = "big_market_strategy_award_list_key_";
        public static String STRATEGY_RATE_TABLE_KEY = "big_market_strategy_rate_table_key_";
        public static String STRATEGY_RATE_RANGE_KEY = "big_market_strategy_rate_range_key_";
        public static String RULE_TREE_VO_KEY = "big_market_rule_tree_vo_key_";
        public static String STRATEGY_AWARD_COUNT_KEY = "big_market_strategy_award_count_key_";
        public static String STRATEGY_AWARD_COUNT_QUEUE_KEY = "big_market_strategy_award_count_queue_key";
        public static String ACTIVITY_SKU_COUNT_QUERY_KEY = "big_market_activity_sku_count_query_key_";
        public static String ACTIVITY_SKU_STOCK_COUNT_KEY = "big_market_activity_sku_stock_count_key_";
    }

    // Decimal(4, 2)万分精度
    public final static int AWARD_RATE_RANGE = 10000;
    public final static int AWARD_RATE_FACTOR = 100;

}
