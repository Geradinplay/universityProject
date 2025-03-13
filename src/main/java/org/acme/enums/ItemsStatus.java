package org.acme.enums;

public enum ItemsStatus {
        EXISTS("Item already exists!");

        private String label;

        private ItemsStatus(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
}
