package com.lucidplugins.lucidhotkeys;

public enum Precondition
{
    NONE(0, 1),

    // INVENTORY PRECONDITIONS
    INVENTORY_COUNT_EQUALS(1, 2),
    INVENTORY_COUNT_NOT_EQUALS(2, 2),
    INVENTORY_COUNT_GREATER_THAN(3, 2),
    INVENTORY_COUNT_GREATER_THAN_EQUAL_TO(4, 2),
    INVENTORY_COUNT_LESS_THAN(5, 2),
    INVENTORY_COUNT_LESS_THAN_EQUAL_TO(6, 2),

    INVENTORY_NAMED_COUNT_EQUALS(7, 3),
    INVENTORY_NAMED_COUNT_NOT_EQUALS(8, 3),
    INVENTORY_NAMED_COUNT_GREATER_THAN(9, 3),
    INVENTORY_NAMED_COUNT_GREATER_THAN_EQUAL_TO(10, 3),
    INVENTORY_NAMED_COUNT_LESS_THAN(11, 3),
    INVENTORY_NAMED_COUNT_LESS_THAN_EQUAL_TO(12, 3),

    INVENTORY_ID_COUNT_EQUALS(13, 3),
    INVENTORY_ID_COUNT_NOT_EQUALS(14, 3),
    INVENTORY_ID_COUNT_GREATER_THAN(15, 3),
    INVENTORY_ID_COUNT_GREATER_THAN_EQUAL_TO(16, 3),
    INVENTORY_ID_COUNT_LESS_THAN(17, 3),
    INVENTORY_ID_COUNT_LESS_THAN_EQUAL_TO(18, 3),

    INVENTORY_NAMED_CONTAINS(19, 2),
    INVENTORY_NAMED_NOT_CONTAINS(20, 2),

    INVENTORY_ID_CONTAINS(21, 2),
    INVENTORY_ID_NOT_CONTAINS(22, 2),

    // BANK
    BANK_OPEN(23, 1),
    BANK_NOT_OPEN(24, 1),

    // ACTIONS
    LAST_ACTION_SUCCEEDED(25, 1),
    LAST_ACTION_FAILED(26, 1),

    // LOCAL PLAYER WORLD LOCATION
    LOCAL_PLAYER_WORLD_LOCATION_EQUALS(27, 3),
    LOCAL_PLAYER_WORLD_LOCATION_NOT_EQUALS(28, 3),
    LOCAL_PLAYER_WORLD_LOCATION_X_EQUALS(29, 2),
    LOCAL_PLAYER_WORLD_LOCATION_X_NOT_EQUALS(30, 2),
    LOCAL_PLAYER_WORLD_LOCATION_X_GREATER_THAN(31, 2),
    LOCAL_PLAYER_WORLD_LOCATION_X_GREATER_THAN_EQUAL_TO(32, 2),
    LOCAL_PLAYER_WORLD_LOCATION_X_LESS_THAN(33, 2),
    LOCAL_PLAYER_WORLD_LOCATION_X_LESS_THAN_EQUAL_TO(34, 2),
    LOCAL_PLAYER_WORLD_LOCATION_Y_EQUALS(35, 2),
    LOCAL_PLAYER_WORLD_LOCATION_Y_NOT_EQUALS(36, 2),
    LOCAL_PLAYER_WORLD_LOCATION_Y_GREATER_THAN(37, 2),
    LOCAL_PLAYER_WORLD_LOCATION_Y_GREATER_THAN_EQUAL_TO(38, 2),
    LOCAL_PLAYER_WORLD_LOCATION_Y_LESS_THAN(39, 2),
    LOCAL_PLAYER_WORLD_LOCATION_Y_LESS_THAN_EQUAL_TO(40, 2),

    // PLAYER ANIMATION
    TICKS_SINCE_LAST_PLAYER_ANIMATION_EQUALS(41, 2),
    TICKS_SINCE_LAST_PLAYER_ANIMATION_NOT_EQUALS(42, 2),
    TICKS_SINCE_LAST_PLAYER_ANIMATION_GREATER_THAN(43, 2),
    TICKS_SINCE_LAST_PLAYER_ANIMATION_GREATER_THAN_EQUAL_TO(44, 2),
    TICKS_SINCE_LAST_PLAYER_ANIMATION_LESS_THAN(45, 2),
    TICKS_SINCE_LAST_PLAYER_ANIMATION_LESS_THAN_EQUAL_TO(46, 2),

    TICKS_SINCE_SPECIFIC_PLAYER_ANIMATION_EQUALS(47, 3),
    TICKS_SINCE_SPECIFIC_PLAYER_ANIMATION_NOT_EQUALS(48, 3),
    TICKS_SINCE_SPECIFIC_PLAYER_ANIMATION_GREATER_THAN(49, 3),
    TICKS_SINCE_SPECIFIC_PLAYER_ANIMATION_GREATER_THAN_EQUAL_TO(50, 3),
    TICKS_SINCE_SPECIFIC_PLAYER_ANIMATION_LESS_THAN(51, 3),
    TICKS_SINCE_SPECIFIC_PLAYER_ANIMATION_LESS_THAN_EQUAL_TO(52, 3),

    DISTANCE_TO_NAMED_NPC_MIDDLE_EQUALS(53, 3),
    DISTANCE_TO_NAMED_NPC_MIDDLE_NOT_EQUALS(54, 3),
    DISTANCE_TO_NAMED_NPC_MIDDLE_GREATER_THAN(55, 3),
    DISTANCE_TO_NAMED_NPC_MIDDLE_LESS_THAN(56, 3),

    DISTANCE_TO_ID_NPC_MIDDLE_EQUALS(57, 3),
    DISTANCE_TO_ID_NPC_MIDDLE_NOT_EQUALS(58, 3),
    DISTANCE_TO_ID_NPC_MIDDLE_GREATER_THAN(59, 3),
    DISTANCE_TO_ID_NPC_MIDDLE_LESS_THAN(60, 3),

    DISTANCE_TO_NAMED_OBJECT_EQUALS(61, 3),
    DISTANCE_TO_NAMED_OBJECT_NOT_EQUALS(62, 3),
    DISTANCE_TO_NAMED_OBJECT_GREATER_THAN(63, 3),
    DISTANCE_TO_NAMED_OBJECT_LESS_THAN(64, 3),

    DISTANCE_TO_ID_OBJECT_EQUALS(65, 3),
    DISTANCE_TO_ID_OBJECT_NOT_EQUALS(66, 3),
    DISTANCE_TO_ID_OBJECT_GREATER_THAN(67, 3),
    DISTANCE_TO_ID_OBJECT_LESS_THAN(68, 3),

    DISTANCE_TO_WORLD_LOCATION_EQUALS(69, 4),
    DISTANCE_TO_WORLD_LOCATION_NOT_EQUALS(70, 4),
    DISTANCE_TO_WORLD_LOCATION_GREATER_THAN(71, 4),
    DISTANCE_TO_WORLD_LOCATION_LESS_THAN(72, 4),

    DISTANCE_TO_NAMED_PLAYER_EQUALS(73, 3),
    DISTANCE_TO_NAMED_PLAYER_NOT_EQUALS(74, 3),
    DISTANCE_TO_NAMED_PLAYER_GREATER_THAN(75, 3),
    DISTANCE_TO_NAMED_PLAYER_LESS_THAN(76, 3),

    HAS_NAMED_ITEM_EQUIPPED(77, 2),
    DOESNT_HAVE_NAMED_ITEM_EQUIPPED(78, 2),
    HAS_ID_ITEM_EQUIPPED(79, 2),
    DOESNT_HAVE_ID_ITEM_EQUIPPED(80, 2),

    TICK_METRONOME_VALUE_EQUALS(81, 2),
    TICK_METRONOME_VALUE_NOT_EQUALS(82, 2),
    TICK_METRONOME_VALUE_GREATER_THAN(83, 2),
    TICK_METRONOME_VALUE_LESS_THAN(84, 2),

    NAMED_NPC_TARGETING_YOU(85, 2),
    ID_NPC_TARGETING_YOU(86, 2),
    ANY_NPC_TARGETING_YOU(87, 1),

    TARGETING_NAMED_NPC(88, 1),
    TARGETING_ANY_NPC(89, 1),

    NAMED_PLAYER_TARGETING_YOU(90, 2),
    ANY_PLAYER_TARGETING_YOU(91, 1),
    TARGETING_NAMED_PLAYER(92, 2),
    TARGETING_ANY_PLAYER(93, 1),

    NAMED_NPC_NOT_TARGETING_YOU(94, 2),
    ID_NPC_NOT_TARGETING_YOU(95, 2),
    NO_NPC_TARGETING_YOU(96, 1),

    NAMED_PLAYER_NOT_TARGETING_YOU(97, 2),
    NO_PLAYER_TARGETING_YOU(98, 1),

    VAR_VALUE_EQUALS(99, 3),
    VAR_VALUE_NOT_EQUAL(100, 3),
    VAR_VALUE_LESS_THAN(101, 3),
    VAR_VALUE_GREATER_THAN(102, 3),

    LOCAL_PLAYER_SCENE_LOCATION_EQUALS(103, 3),
    LOCAL_PLAYER_SCENE_LOCATION_NOT_EQUALS(104, 3),
    LOCAL_PLAYER_SCENE_LOCATION_X_EQUALS(105, 2),
    LOCAL_PLAYER_SCENE_LOCATION_X_NOT_EQUALS(106, 2),
    LOCAL_PLAYER_SCENE_LOCATION_X_GREATER_THAN(107, 2),
    LOCAL_PLAYER_SCENE_LOCATION_X_LESS_THAN(108, 2),
    LOCAL_PLAYER_SCENE_LOCATION_Y_EQUALS(109, 2),
    LOCAL_PLAYER_SCENE_LOCATION_Y_NOT_EQUALS(110, 2),
    LOCAL_PLAYER_SCENE_LOCATION_Y_GREATER_THAN(111, 2),
    LOCAL_PLAYER_SCENE_LOCATION_Y_LESS_THAN(112, 2),

    CURRENT_ANIMATION_EQUALS(113, 2),
    CURRENT_ANIMATION_NOT_EQUALS(114, 2),

    SPEC_ENERGY_EQUALS(115, 2),
    SPEC_ENERGY_NOT_EQUALS(116, 2),
    SPEC_ENERGY_GREATER_THAN(117, 2),
    SPEC_ENERGY_LESS_THAN(118, 2),

    PLAYER_HP_PERCENT_EQUALS(119, 2),
    PLAYER_HP_PERCENT_NOT_EQUALS(120, 2),
    PLAYER_HP_PERCENT_GREATER_THAN(121, 2),
    PLAYER_HP_PERCENT_LESS_THAN(122, 2),

    TARGET_HP_PERCENT_EQUALS(123, 2),
    TARGET_HP_PERCENT_NOT_EQUALS(124, 2),
    TARGET_HP_PERCENT_GREATER_THAN(125, 2),
    TARGET_HP_PERCENT_LESS_THAN(126, 2),

    TARGET_ANIMATION_EQUALS(127, 2),
    TARGET_ANIMATION_NOT_EQUALS(128, 2),

    IS_PROJECTILE_ID_TARGETING_PLAYER(129, 2),
    IS_PROJECTILE_ID_NOT_TARGETING_PLAYER(130, 2),
    IS_PROJECTILE_ID_TARGETING_LOCAL_TILE(131, 4),
    IS_PROJECTILE_ID_NOT_TARGETING_LOCAL_TILE(132, 4),
    IS_PROJECTILE_ID_TARGETING_PLAYERS_TILE(133, 2),
    IS_PROJECTILE_ID_NOT_TARGETING_PLAYERS_TILE(134, 2),

    ID_TILE_ITEM_EXISTS_WITHIN_DISTANCE(135, 3),
    ID_TILE_ITEM_NOT_EXISTS_WITHIN_DISTANCE(136, 3),
    NAMED_TILE_ITEM_EXISTS_WITHIN_DISTANCE(137, 3),
    NAMED_TILE_ITEM_NOT_EXISTS_WITHIN_DISTANCE(138, 3),

    PRAYER_POINTS_EQUALS(139, 2),
    PRAYER_POINTS_NOT_EQUALS(140, 2),
    PRAYER_POINTS_GREATER_THAN(141, 2),
    PRAYER_POINTS_LESS_THAN(142, 2),

    LOCAL_PLAYER_REGION_LOCATION_EQUALS(143, 3),
    LOCAL_PLAYER_REGION_LOCATION_NOT_EQUALS(144, 3),
    LOCAL_PLAYER_REGION_LOCATION_X_EQUALS(145, 2),
    LOCAL_PLAYER_REGION_LOCATION_X_NOT_EQUALS(146, 2),
    LOCAL_PLAYER_REGION_LOCATION_X_GREATER_THAN(147, 2),
    LOCAL_PLAYER_REGION_LOCATION_X_LESS_THAN(148, 2),
    LOCAL_PLAYER_REGION_LOCATION_Y_EQUALS(149, 2),
    LOCAL_PLAYER_REGION_LOCATION_Y_NOT_EQUALS(150, 2),
    LOCAL_PLAYER_REGION_LOCATION_Y_GREATER_THAN(151, 2),
    LOCAL_PLAYER_REGION_LOCATION_Y_LESS_THAN(152, 2),
    LOCAL_PLAYER_REGION_ID_EQUALS(153, 2),
    LOCAL_PLAYER_REGION_ID_NOT_EQUALS(154, 2),

    LOCAL_PLAYER_IS_MOVING(155, 1),
    LOCAL_PLAYER_IS_NOT_MOVING(156, 1),

    WIDGET_IS_HIDDEN(157, 3),
    WIDGET_IS_SHOWING(158, 3),
    WIDGET_SUB_IS_HIDDEN(159, 4),
    WIDGET_SUB_IS_SHOWING(160, 4),

    ;
    int id;

    int paramsNeeded;

    Precondition(int id, int paramsNeeded)
    {
        this.id = id;
        this.paramsNeeded = paramsNeeded;
    }

    public static Precondition forId(int id)
    {
        for (Precondition precondition : values())
        {
            if (precondition.getId() == id)
            {
                return precondition;
            }
        }
        return NONE;
    }

    public int getId()
    {
        return id;
    }

    public int getParamsNeeded()
    {
        return paramsNeeded;
    }

}
