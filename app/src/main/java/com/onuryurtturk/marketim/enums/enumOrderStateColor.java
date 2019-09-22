package com.onuryurtturk.marketim.enums;

public enum enumOrderStateColor {

    /**
     * enum class represents order state colors
     * Converts order states (string to color codes)
     */
    ONAYBEKLIYOR {
        public String toString() {
            return "#ff0000";
        }
    },
    HAZIRLANIYOR {
        public String toString() {
            return "#ff9900";
        }
    },
    YOLDA {
        public String toString() {
            return "#009900";
        }
    };

    enumOrderStateColor() {
    }
}
