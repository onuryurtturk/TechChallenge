package com.onuryurtturk.marketim.constants;

public enum enumOrderStateColor {

    ONAYBEKLIYOR{
        public String toString() {
            return "#ff0000";
        }
    },
    HAZIRLANIYOR{
        public String toString() {
            return "#ff9900";
        }
    },
    YOLDA{
        public String toString() {
            return "#009900";
        }
    };

    enumOrderStateColor() {

    }
}
